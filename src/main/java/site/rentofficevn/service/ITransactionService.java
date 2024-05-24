package site.rentofficevn.service;

import javassist.NotFoundException;
import site.rentofficevn.dto.TransactionDTO;

public interface ITransactionService {
    TransactionDTO save(TransactionDTO transaction) throws NotFoundException;
    TransactionDTO getTransactionData(Long customerId);
}
