package ex.soft.domain.dao.connpool;

import ex.soft.domain.dao.OrderDao;
import ex.soft.domain.model.Order;

import java.util.List;

/**
 * Created by Alex108 on 12.10.2016.
 */
public class ConnectionPoolOrderDao implements OrderDao {
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
