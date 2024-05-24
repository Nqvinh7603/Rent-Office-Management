package site.rentofficevn.service.impl;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.rentofficevn.converter.TransactionConverter;
import site.rentofficevn.dto.TransactionDTO;
import site.rentofficevn.entity.TransactionEntity;
import site.rentofficevn.enums.TransactionsEnum;
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

    @Autowired
    public TransactionService(TransactionConverter transactionConverter, TransactionRepository transactionRepository) {
        this.transactionConverter = transactionConverter;
        this.transactionRepository = transactionRepository;
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

    private List<TransactionDTO> getTransactionList(Long customerId) {
        List<TransactionEntity> listTransaction = transactionRepository.findByCustomerId(customerId);
        return listTransaction.stream()
                .map(transactionConverter::toDTO)
                .collect(Collectors.toList());
    }

    private Map<String, String> getTransactionMap() {
        return Arrays.stream(TransactionsEnum.values()).collect(Collectors.toMap(Enum::toString, TransactionsEnum::getTransactionTypeValue));
    }

    @Override
    public TransactionDTO getTransactionData(Long customerId) {
        Map<String, String> transactionMap = getTransactionMap();
        List<TransactionDTO> transactionList = getTransactionList(customerId);
        return new TransactionDTO(transactionMap, transactionList);
    }

}
