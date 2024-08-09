package KruchkovTask5;

import org.junit.jupiter.api.Assertions;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@ComponentScan
public class Main {
    public static void main(String[] args) throws SQLException {
        UserCustProduct userCustProduct;
        AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(Main.class);
        CustProductService CustProductService =context.getBean(CustProductService.class);


        CustProduct CustProduct =new CustProduct(0,"442105810238100142001", 2000000,"DEPN");
        // getter используется, поскольку в сервсе нет такого метода findAll()
        CustProductService.getCustProductDao().insert(CustProduct);
        CustProduct =new CustProduct(0,"40702810238100142001", 2000,"RKO");
        CustProductService.getCustProductDao().insert(CustProduct);


        List<CustProduct> custProductList = CustProductService.getCustProductDao().findAll();

        for(CustProduct custProductD : custProductList) {
            CustProduct = CustProductService.findById(custProductD.getId());
            Assertions.assertEquals(CustProduct.getAccountNumber(), custProductD.getAccountNumber(),
                    "Не выбирается продукт по id");
        }

        custProductList = CustProductService.getCustProductDao().findAll();
        UserCustProductDAO userCustProductDAO = new UserCustProductDAO(CustProductService
                .getCustProductDao()
                .getDataSource());
        long userId = 1;
        for(CustProduct custProductD : custProductList) {
            userCustProduct = new UserCustProduct(0, userId++, custProductD.getId());
            userCustProductDAO.insert(userCustProduct);
        }
        List<UserCustProduct> userCustProductList =userCustProductDAO.findAll();
        for(UserCustProduct userCustProductD : userCustProductList) {
            custProductList = CustProductService.findByUserId(userCustProductD.getUserId());
        }
        boolean found = false;
        for(CustProduct custProductE : custProductList) {
            for(CustProduct custProductD : custProductList) {
                if (custProductE.getAccountNumber().equals(custProductD.getAccountNumber())) {
                    found = true;
                }
            }
            Assertions.assertTrue(found,"Не выбираются продукты по userId");
        }
    }
}
