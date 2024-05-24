package site.rentofficevn.enums;

public enum TransactionEnum {
    QUA_TRINH_CSKH("QUÁ TRÌNH CSKH"),
    DAN_DI_XEM("DẪN KHÁCH ĐI XEM");

    private final String transactionTypeValue;

    public String getTransactionTypeValue() {
        return transactionTypeValue;
    }

    TransactionEnum(String transactionTypeValue) {
        this.transactionTypeValue = transactionTypeValue;
    }
}
