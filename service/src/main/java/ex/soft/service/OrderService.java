package ex.soft.service;

import ex.soft.domain.dao.OrderDao;
import ex.soft.domain.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Alex108 on 14.10.2016.
 */
@Service
public class OrderService {

    private OrderDao orderDao;

    public OrderService() {}

    @Resource(name = "orderDao")
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Transactional
    public Order getOrder(Long key) {
        return orderDao.get(key);
    }

    @Transactional
    public void placeOrder(Order order){
       orderDao.save(order);
    }

}
