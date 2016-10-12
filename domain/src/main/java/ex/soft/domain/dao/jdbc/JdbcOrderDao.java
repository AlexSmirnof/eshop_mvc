package ex.soft.domain.dao.jdbc;

import ex.soft.domain.dao.DaoFactory;
import ex.soft.domain.dao.OrderDao;
import ex.soft.domain.datasource.DataSource;
import ex.soft.domain.model.Order;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Alex108 on 11.10.2016.
 */
public class JdbcOrderDao implements OrderDao {

    private static final String TABLE_NAME = "Orders";
    private static final String KEY_COL = "id";
    private static final String F_NAME_COL = "firstName";
    private static final String L_NAME_COL = "lastName";
    private static final String DELIVERY_ADDRESS_COL = "deliveryAddress";
    private static final String CONTACT_PHONE_No_COL = "contactPhoneNo";
    private static final String VALUES_STRING = "'%s','%s','%s','%s','%s'";
    private final Executor executor;

    public JdbcOrderDao(Connection connection) {
        executor = new Executor(connection);
    }

    @Override
    public Order get(Long key) {
        return null;
    }

    @Override
    public void save(Order order) {

    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public void close() {

    }
}
