package KruchkovTask5;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustProductDao {

    private DataSource dataSource;
    private Connection con;
    private String      tableName;
    private String userCustProdTable;
    private Statement statement ;

    @Autowired
    public CustProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.tableName = "student.cust_product";
        this.userCustProdTable = "student.user_cust_product";
        try {
            this.con = this.dataSource.getConnection();
            statement = con.createStatement();
        }
        catch(SQLException ex) {
            throw new RuntimeException((ex));
        }
    }
    public void commit() throws SQLException {
        String query = "COMMIT";
        try
        {
            statement.execute(query);
        }
        catch(SQLException e){ throw e; }
    }

    public CustProduct findById(long id) throws SQLException {
        List<CustProduct> CustProducts = new ArrayList<>();
        String query = "SELECT id AS id, account_number AS account_number," +
                " balance AS balance, product_type AS product_type" +
                " FROM "+this.tableName +
                " WHERE id = "+String.valueOf(id);
        try
        {
            ResultSet res =  statement.executeQuery(query);
            res.next();
            id = res.getLong("id");
            String accountNumber = res.getString("account_number");
            double balance = res.getDouble("balance");
            balance = balance;
            String productType = res.getString("product_type");
            return new CustProduct(id, accountNumber, balance, productType);
        }
        catch(SQLException e){ throw e; }
    }

    // Далее нужный комментарий
// Select по userId выполняется в связке со следующей таблицей:
    /*
public class UserCustProduct {
    private long id;
    private long userId;
    private long custProductId;
}
     */
    public List<CustProduct> findByUserId(long userId) throws SQLException {
        List<CustProduct> CustProducts = new ArrayList<>();
        String query = "SELECT id AS id, account_number AS account_number," +
        " balance AS balance, product_type AS product_type" +
                " FROM "+this.tableName +
                " WHERE id IN (SELECT cust_product_id FROM "+this.userCustProdTable+
                " WHERE user_id = " + String.valueOf(userId) + " )";
        try
        {
            ResultSet res =  statement.executeQuery(query);
            while (true) {
                res.next();
                long id = res.getLong("id");
                String accountNumber = res.getString("account_number");
                double balance = res.getDouble("balance");
                balance = balance;
                String productType = res.getString("product_type");

                CustProducts.add(new CustProduct(id, accountNumber, balance, productType));
                if (res.isLast()) break;
            }
            return CustProducts;
        }
        catch(SQLException e){ throw e; }
    }

    public List<CustProduct> findAll() throws SQLException {
        List<CustProduct> CustProducts = new ArrayList<>();
        String query = "SELECT id AS id, account_number AS account_number," +
                " balance AS balance, product_type AS product_type" +
                " FROM "+this.tableName;
        try
        {
            ResultSet res =  statement.executeQuery(query);
            while (true) {
                res.next();
                long id = res.getLong("id");
                String accountNumber = res.getString("account_number");
                double balance = res.getDouble("balance");
                balance = balance;
                String productType = res.getString("product_type");
                CustProducts.add(new CustProduct(id, accountNumber, balance, productType));
                if (res.isLast()) break;
            }
            return CustProducts;
        }
        catch(SQLException e){ throw e; }
    }

    public void insert(CustProduct custProduct) throws SQLException {
        String query = "INSERT INTO "+this.tableName +
                " (account_number, balance, product_type) VALUES ("+
                '\''+custProduct.getAccountNumber()+'\'' + "," +
                '\''+custProduct.getBalance()+'\'' + "," +
                '\''+custProduct.getProductType()+'\'' +
                ")";
        try
        {
            statement.execute(query);
        }
        catch(SQLException e){ throw e; }
    }
    // Для целей тестирования
    public DataSource getDataSource() {
        return this.dataSource;
    }
}
