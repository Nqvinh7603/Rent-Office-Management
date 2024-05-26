package site.rentofficevn.dto;

import java.util.List;
public class TransactionTypeDTO extends AbstractDTO<TransactionTypeDTO>{
    private String code;
    private String name;
    private List<TransactionDTO> transactions;

    public TransactionTypeDTO() {
    }

    public TransactionTypeDTO(String code, String name, List<TransactionDTO> transactions) {
        this.code = code;
        this.name = name;
        this.transactions = transactions;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }
}
