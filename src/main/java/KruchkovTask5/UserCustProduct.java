package KruchkovTask5;

public class UserCustProduct {
    private long id;
    private long userId;        // user_id
    private long custProductId;     // cust_product_id

    public UserCustProduct(long id, long userId, long custProductId) {
        if (id != 0)
            this.id = id;
        this.userId = userId;
        this.custProductId = custProductId;
    }
    public long getUserId() {
        return this.userId;
    }
    public long getCustProductId() {
        return this.custProductId;
    }
}
