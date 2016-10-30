package ex.soft.service;

import ex.soft.domain.dao.OrderDao;
import ex.soft.domain.dao.UserDao;
import ex.soft.domain.model.Cart;
import ex.soft.domain.model.Order;
import ex.soft.domain.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Alex108 on 14.10.2016.
 */
@Service
public class OrderService {

    private OrderDao orderDao;
    private UserDao userDao;

    public OrderService() {}

    @Transactional
    public Order getOrder(Long key) {
        return orderDao.get(key);
    }

    @Transactional
    public void placeOrder(Cart cart, Long userId){
        User user = userDao.get(userId);
        Order order = new Order();
        order.setFirstName(user.getFirstName());
        order.setLastName(user.getLastName());
        order.setDeliveryAddress(user.getDeliveryAddress());
        order.setContactPhoneNo(user.getContactPhoneNo());
        order.setOrderItems(cart.getOrderItems());
        order.setTotalPrice(cart.getTotalPrice());
        order.setUserId(userId);
        orderDao.save(order);
    }

    @Resource(name = "orderDao")
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Resource( name = "userDao")
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
