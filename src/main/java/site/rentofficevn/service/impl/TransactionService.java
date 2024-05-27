package site.rentofficevn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.rentofficevn.converter.TransactionConverter;
import site.rentofficevn.dto.TransactionDTO;
import site.rentofficevn.dto.TransactionTypeDTO;
import site.rentofficevn.entity.TransactionEntity;
import site.rentofficevn.enums.TransactionsEnum;
import site.rentofficevn.repository.CustomerRepository;
import site.rentofficevn.repository.TransactionRepository;
import site.rentofficevn.service.ITransactionService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService {

    private final TransactionConverter transactionConverter;
    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public TransactionService(TransactionConverter transactionConverter, TransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.transactionConverter = transactionConverter;
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public TransactionDTO save(TransactionDTO transaction) throws NotFoundException {
        TransactionEntity transactionEntity = transactionConverter.toEntity(transaction);
        if (transaction != null) {
            transactionRepository.save(transactionEntity);
        }
        return transactionConverter.toDTO(transactionEntity);
    }

    @Override
    public List<TransactionTypeDTO> getTransactionData(Long customerId) {
        List<TransactionTypeDTO> transactionTypeList = Arrays.stream(TransactionsEnum.values())
                .map(transactionEnum -> {
                    TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
                    transactionTypeDTO.setCode(transactionEnum.toString());
                    transactionTypeDTO.setName(transactionEnum.getTransactionTypeValue());
                    return transactionTypeDTO;
                })
                .collect(Collectors.toList());
        List<TransactionEntity> transactionList = customerRepository.findTransactionByCustomerId(customerId);
        List<TransactionDTO> transactionDTOList = transactionList.stream()
                .map(transactionConverter::toDTO)
                .collect(Collectors.toList());

        Map<String, List<TransactionDTO>> transactionsByType = transactionDTOList.stream()
                .collect(Collectors.groupingBy(TransactionDTO::getCode));

        transactionTypeList.forEach(transactionTypeDTO -> {
            List<TransactionDTO> transactions = transactionsByType.get(transactionTypeDTO.getCode());
            transactionTypeDTO.setTransactions(transactions != null ? transactions : Collections.emptyList());
        });
        return transactionTypeList;
    }

}
