package site.rentofficevn.service.impl;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.rentofficevn.converter.TransactionConverter;
import site.rentofficevn.dto.TransactionDTO;
import site.rentofficevn.entity.TransactionEntity;
import site.rentofficevn.enums.TransactionsEnum;
import site.rentofficevn.repository.CustomerRepository;
import site.rentofficevn.repository.TransactionRepository;
import site.rentofficevn.service.ITransactionService;

import java.util.Arrays;
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
    public TransactionDTO getTransactionData(Long customerId) {
        Map<String, String> transactionMap = Arrays.stream(TransactionsEnum.values())
                .collect(Collectors.toMap(Enum::toString, TransactionsEnum::getTransactionTypeValue));

        List<TransactionEntity> transactionList = customerRepository.findTransactionByCustomerId(customerId);
        List<TransactionDTO> transactionDTOList = transactionList.stream()
                .map(transactionConverter::toDTO)
                .collect(Collectors.toList());

        return new TransactionDTO(transactionMap, transactionDTOList);
    }

}
