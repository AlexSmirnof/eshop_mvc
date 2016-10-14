package ex.soft.service;

import ex.soft.domain.dao.OrderDao;
import ex.soft.domain.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Alex108 on 14.10.2016.
 */
@Service
public class OrderService implements IService<Order> {

    private OrderDao orderDao;

    public OrderService() {}

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Transactional
    @Override
    public Order get(Long key) {
        return null;
    }

    @Transactional
    @Override
    public void save(Order order) {

    }

    @Transactional
    @Override
    public List<Order> findAll() {
        return null;
    }

    @Transactional
    @Override
    public void close() {

    }
}
