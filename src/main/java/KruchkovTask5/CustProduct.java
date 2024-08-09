package KruchkovTask5;

public class CustProduct {
    private long id;
    private String accountNumber;
    private double balance;
    private String productType;

    public CustProduct(long id, String accountNumber, double balance, String productType) {
        if (id != 0)
            this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.productType = productType;
    }
    public long getId() {
        return this.id;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getProductType() {
        return this.productType;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance(double balance) {
        return this.balance = balance;
    }

    public String getProductType(String productType) {
        return this.productType = productType;
    }
}
