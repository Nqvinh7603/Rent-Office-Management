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

import java.util.*;
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
        List<TransactionTypeDTO> transactionResponseDTOS = new ArrayList<>();

        Map<String, List<TransactionDTO>> transactions = transactionRepository.findByCustomerId(customerId).stream()
                .map(transactionConverter::toDTO)
                .collect(Collectors.groupingBy(TransactionDTO::getCode));

        Arrays.stream(TransactionsEnum.values()).forEach(transactionType -> {
            TransactionTypeDTO transactionResponseDTO = new TransactionTypeDTO();

            transactionResponseDTO.setCode(transactionType.name());
            transactionResponseDTO.setName(transactionType.getTransactionTypeValue());
            transactionResponseDTO.setTransactions(transactions.get(transactionType.name()));

            transactionResponseDTOS.add(transactionResponseDTO);
        });

        return transactionResponseDTOS;
    }
}
