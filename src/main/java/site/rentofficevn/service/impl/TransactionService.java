package site.rentofficevn.service.impl;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public TransactionDTO save(TransactionDTO transaction) throws NotFoundException {
        TransactionEntity transactionEntity = transactionConverter.toEntity(transaction);
        if(transaction != null){
            transactionRepository.save(transactionEntity);
        }
        return transactionConverter.toDTO(transactionEntity);
    }

    @Override
    public List<TransactionDTO> getTransactionList(Long customerId) {
        List<TransactionEntity> listTransaction = transactionRepository.findByCustomerId(customerId);
        return listTransaction.stream()
                .map(transactionConverter::toDTO)
                .collect(Collectors.toList());
    }
}
