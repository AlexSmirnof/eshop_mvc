package ex.soft.service.api;

import ex.soft.domain.model.Cart;
import ex.soft.domain.model.OrderItem;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Alex108 on 05.11.2016.
 */
public interface ICartService {

    Cart getCart(HttpSession session);

    void removeCart(HttpSession session);

    void updateCart(HttpSession session, List<OrderItem> orderItems);

    Long addToCart(HttpSession session, OrderItem orderItem);

    Long deleteFromCart(HttpSession session, OrderItem orderItem);
}
