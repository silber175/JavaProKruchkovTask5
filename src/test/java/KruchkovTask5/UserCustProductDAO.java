package KruchkovTask5;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserCustProductDAO {
    private DataSource dataSource;
    private Connection con;
    private String userCustProdTable;
    private Statement statement ;

    public UserCustProductDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        this.userCustProdTable = "student.user_cust_product";
        try {
            this.con = this.dataSource.getConnection();
            statement = con.createStatement();
        }
        catch(SQLException ex) {
            throw new RuntimeException((ex));
        }
    }

    public List<UserCustProduct> findAll() throws SQLException {
        List<UserCustProduct> userCustProducts = new ArrayList<>();
        String query = "SELECT id AS id , user_id AS user_id, cust_product_id AS cust_product_id" +
                " FROM "+this.userCustProdTable;
        try
        {
            ResultSet res =  statement.executeQuery(query);
            while (true) {
                res.next();
                long id = res.getLong("id");
                long userId = res.getLong("user_id");
                long custProductId = res.getLong("cust_product_id");
                 userCustProducts.add(new UserCustProduct(id, userId, custProductId));
                if (res.isLast()) break;
            }
            return userCustProducts;
        }
        catch(SQLException e){ throw e; }
    }

    public void insert(UserCustProduct userCustProduct) throws SQLException {
        String query = "INSERT INTO "+this.userCustProdTable +
                " (user_id, cust_product_id) VALUES ("+
                '\''+userCustProduct.getUserId()+'\'' + "," +
                '\''+userCustProduct.getCustProductId()+'\'' +
                ")";
        try
        {
            statement.execute(query);
        }
        catch(SQLException e){ throw e; }
    }
}
