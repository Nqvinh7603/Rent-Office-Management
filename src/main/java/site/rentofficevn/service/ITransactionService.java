package site.rentofficevn.service;

import javassist.NotFoundException;
import site.rentofficevn.dto.TransactionDTO;
import java.util.List;
public interface ITransactionService {
    TransactionDTO save(TransactionDTO transaction) throws NotFoundException;
    List<TransactionDTO> getTransactionList(Long customerId);
}
