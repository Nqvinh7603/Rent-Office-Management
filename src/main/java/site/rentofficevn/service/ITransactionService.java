package site.rentofficevn.service;


import org.springframework.security.acls.model.NotFoundException;
import site.rentofficevn.dto.TransactionDTO;
import site.rentofficevn.dto.TransactionTypeDTO;

import java.util.*;

public interface ITransactionService {
    TransactionDTO save(TransactionDTO transaction) throws NotFoundException;
    List<TransactionTypeDTO> getTransactionData(Long customerId);
}
