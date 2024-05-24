package site.rentofficevn.service;

import site.rentofficevn.dto.TransactionDTO;
import java.util.List;
public interface ITransactionService {
    TransactionDTO save(TransactionDTO transaction);
    List<TransactionDTO> getTransactionList(Long customerId);
}
