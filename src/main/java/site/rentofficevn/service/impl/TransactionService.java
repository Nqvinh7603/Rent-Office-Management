package site.rentofficevn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.rentofficevn.converter.TransactionConverter;
import site.rentofficevn.dto.TransactionDTO;
import site.rentofficevn.entity.TransactionEntity;
import site.rentofficevn.repository.TransactionRepository;
import site.rentofficevn.service.ITransactionService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    TransactionConverter transactionConverter;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public TransactionDTO save(TransactionDTO transaction) {
        TransactionEntity transactionEntity = transactionConverter.toEntity(transaction);
        return transactionConverter.toDTO(transactionRepository.save(transactionEntity));
    }

    @Override
    public List<TransactionDTO> getTransactionList(Long customerId) {
        List<TransactionEntity> listTransaction = transactionRepository.findByCustomerId(customerId);
        return listTransaction.stream()
                .map(transactionConverter::toDTO)
                .collect(Collectors.toList());
    }
}
