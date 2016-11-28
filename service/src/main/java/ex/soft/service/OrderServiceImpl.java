package ex.soft.service;

import ex.soft.domain.dao.OrderDao;
import ex.soft.domain.form.OrderForm;
import ex.soft.domain.model.Cart;
import ex.soft.domain.model.Order;
import ex.soft.domain.model.User;
import ex.soft.service.api.CartService;
import ex.soft.service.api.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * Created by Alex108 on 14.10.2016.
 */
@Service
@PropertySource("classpath:messages/prices.properties")
public class OrderServiceImpl implements OrderService {

    @Value("${product.delivery}")
    private BigDecimal DELIVERY_PRICE;

    @Resource(name = "orderDao")
    private OrderDao orderDao;

    @Resource
    private CartService cartService;

    @Resource
    private UserService userService;

    @Override
    @Transactional
    public Order getOrder(Long key){
        return orderDao.get(key);
    }

    @Override
    @Transactional
    public Long placeOrder(HttpSession session, OrderForm orderForm){
        Order order = createOrder();
        Cart cart = cartService.getCart(session);
        User user = userService.getUser();
        mapToOrder(order, orderForm, cart, user);
        Long resultKey = orderDao.save(order);
        cartService.removeCart(session);
        return resultKey;
    }

    private Order createOrder(){
        return new Order();
    }

    private void mapToOrder(Order order, OrderForm orderForm, Cart cart, User user){

        order.setFirstName      (orderForm.getFirstName());
        order.setLastName       (orderForm.getLastName());
        order.setDeliveryAddress(orderForm.getDeliveryAddress());
        order.setContactPhoneNo (orderForm.getContactPhoneNo());
        order.setDescription    (orderForm.getDescription());

        order.setOrderItems   (cart.getOrderItems());
        order.setTotalQuantity(cart.getTotalQuantity());
        order.setSubTotalPrice(cart.getTotalPrice());
        order.setTotalPrice   (cart.getTotalPrice().add(DELIVERY_PRICE));

        order.setUser(user);
    }

}
