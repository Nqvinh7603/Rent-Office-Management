package site.rentofficevn.dto;

import java.util.*;

public class TransactionDTO extends AbstractDTO{
    private String code;
    private String note;
    private Long customerId;

    private Map<String, String> transactionMap;
    private List<TransactionDTO> transactionList;

    public TransactionDTO() {
    }

    public TransactionDTO(Map<String, String> transactionMap, List<TransactionDTO> transactionList) {
        this.transactionMap = transactionMap;
        this.transactionList = transactionList;
    }

    public Map<String, String> getTransactionMap() {
        return transactionMap;
    }

    public void setTransactionMap(Map<String, String> transactionMap) {
        this.transactionMap = transactionMap;
    }

    public List<TransactionDTO> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<TransactionDTO> transactionList) {
        this.transactionList = transactionList;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
