package ex.soft.service;

import ex.soft.domain.dao.OrderDao;
import ex.soft.domain.dao.UserDao;
import ex.soft.domain.model.Cart;
import ex.soft.domain.model.Order;
import ex.soft.domain.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Alex108 on 14.10.2016.
 */
@Service
public class OrderService {

    private static final String CART_ATTRIBUTE_NAME = "cart";

    private OrderDao orderDao;
    private UserDao userDao;
    @Resource
    private Cart cart;

    public OrderService() {}

    @Transactional
    public Order getOrder(HttpSession session)  {
        Cart cart = getCartSafely(session);
        Order order = new Order();
        order.setTotalQuantity(cart.getTotalQuantity());
        order.setTotalPrice(cart.getTotalPrice());
        order.setOrderItems(cart.getOrderItems());
        return order;
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


    private Cart getCartSafely(HttpSession session){
        Cart cart = (Cart) session.getAttribute(CART_ATTRIBUTE_NAME);
        return cart != null ? cart : new Cart();
    }


    @Resource(name = "orderDao")
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Resource( name = "userDao")
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
