package ex.soft.domain.dao;


import ex.soft.domain.dao.jdbc.JdbcOrderDao;
import ex.soft.domain.dao.jdbc.JdbcPhoneDao;
import ex.soft.domain.datasource.DataSource;

/**
 * Created by Alex108 on 12.10.2016.
 */
public class DaoFactory {

    private final DataSource dataSource;
    private final OrderDao orderDao;
//    private final PhoneDao phoneDao;

    private DaoFactory() {
        dataSource = DataSource.getInstance();
        orderDao = new JdbcOrderDao(dataSource.getConnection());
//        phoneDao = new JdbcPhoneDao(dataSource.getConnection());
    }

    public static DaoFactory getInstance(){
        return DaoFactoryHolder.INSTANCE;
    }

    /**
     *  I-ый Вариант
     *
     */
    public OrderDao getOrderDao(){
        return orderDao;
    }
    public DataSource getDataSource(){
        return dataSource;
    }
    /**
     *  II-ый Вариант
     *
     */
    public static PhoneDao getPhoneDao(){
        return JdbcPhoneDao.getInstance();
//        return ConnectionPoolOrderDao.getInstance();
//        return FileOrderDao.getInstance();
    }

    private static class DaoFactoryHolder{
        private static final DaoFactory INSTANCE = new DaoFactory();
        private DaoFactoryHolder() {}
    }

}
