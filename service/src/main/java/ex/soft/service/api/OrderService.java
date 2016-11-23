package ex.soft.service.api;

import ex.soft.domain.model.Order;

import javax.servlet.http.HttpSession;

/**
 * Created by Alex108 on 23.11.2016.
 */
public interface OrderService {

    Order createOrder(HttpSession session);


    Order getOrder(Long key);


    Long placeOrder(HttpSession session, Order order);
}
