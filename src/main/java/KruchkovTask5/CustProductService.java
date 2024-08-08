package KruchkovTask5;

import java.sql.SQLException;
import java.util.List;

public class CustProductService {
    private CustProductDao custProductDao;

    public CustProductService(CustProductDao custProductDao) {
        this.custProductDao = custProductDao;
    }

// Пользователь сервиса должен ловить exception  при вызове метотодов, означающих отрицательный результат поиска
    public CustProduct findById(long id) throws SQLException {
        return this.custProductDao.findById(id);
    }

    public List<CustProduct> findByUserId(long userId) throws SQLException {
        return this.custProductDao.findByUserId(userId);
    }
    public CustProductDao getCustProductDao() {
        return  this.custProductDao;
    }
}
