package ex.soft.service;

import ex.soft.domain.form.CartForm;
import ex.soft.domain.form.OrderItemForm;
import ex.soft.domain.model.Cart;
import ex.soft.domain.model.OrderItem;
import ex.soft.domain.model.Phone;
import ex.soft.service.api.CartService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex108 on 19.10.2016.
 */
@Service
public class CartServiceImpl implements CartService {

    private static final String CART_ATTRIBUTE_NAME = "form";


    @Override
    public Cart getCart(HttpSession session){
        return getCartSafely(session);
    }

    @Override
    public void removeCart(HttpSession session){
       session.removeAttribute(CART_ATTRIBUTE_NAME);
    }

    @Override
    public void updateCart(HttpSession session, CartForm cartForm) {
        Cart cart = getCartSafely(session);
        cart.setOrderItems(mapOrderItemFormsToOrderItems(cartForm.getOrderItemForms()));
        updateCartTotalPriceAndQuantity(cart);
        session.setAttribute(CART_ATTRIBUTE_NAME, cart);
    }

    @Override
    public Long addToCart(HttpSession session, Phone phone, Long quantity){
        Cart cart = getCartSafely(session);
        updateCartWithNewProduct(cart, phone, quantity);
        session.setAttribute(CART_ATTRIBUTE_NAME, cart);
        return quantity;
    }

    @Override
    public Long deleteFromCart(HttpSession session, Long key){
        Cart cart = getCartSafely(session);
        Long result = updateCartWithoutProduct(cart, key);
        session.setAttribute(CART_ATTRIBUTE_NAME, cart);
        return result;
    }


    private List<OrderItem> mapOrderItemFormsToOrderItems(List<OrderItemForm> orderItemForms){
        final List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemForm orderItemForm : orderItemForms) {
            final OrderItem orderItem = getOrderItem();
            orderItem.setPhone(orderItemForm.getPhone());
            orderItem.setQuantity(orderItemForm.getQuantity());
            orderItems.add(orderItem);
        }
        return orderItems;
    }


    private Cart getCartSafely(HttpSession session){
        Cart cart = (Cart) session.getAttribute(CART_ATTRIBUTE_NAME);
        return cart != null ? cart : new Cart();
    }

    private OrderItem getOrderItem(){
        return new OrderItem();
    }

    private void updateCartWithNewProduct(Cart cart, Phone phone, Long quantity){
        int index = findIndexOfProductInCart(cart.getOrderItems(), phone.getKey());
//  if phone not exist in form
        if( index < 0 ){
            OrderItem newOrderItem = new OrderItem();
            newOrderItem.setPhone(phone);
            newOrderItem.setQuantity(quantity);
            cart.getOrderItems().add(newOrderItem);
//  phone already exist in form so we increase oldQuantity of that phone
        } else {
            Long oldQuantity = cart.getOrderItems().get(index).getQuantity();
            Long newQuantity = Long.sum(oldQuantity, quantity);
            cart.getOrderItems().get(index).setQuantity(newQuantity);
        }
        updateCartTotalPriceAndQuantity(cart);
    }

    private Long updateCartWithoutProduct(Cart cart, Long key){
        int index = findIndexOfProductInCart(cart.getOrderItems(), key);
//  if key not exist in form items -> index = -1
        if ( index < 0 ) {
            return 0L;
        }
        OrderItem removedItem = cart.getOrderItems().remove(index);
        updateCartTotalPriceAndQuantity(cart);
        return removedItem.getQuantity();
    }

    private int findIndexOfProductInCart(List<OrderItem> orderItems, Long key){
        int index = 0;
        for (OrderItem orderItem : orderItems) {
            if ( Long.compare(orderItem.getPhone().getKey(), key) == 0 ) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private void updateCartTotalPriceAndQuantity(Cart cart){
        BigDecimal newTotalPrice = computeCartTotalPrice(cart);
        Long newTotalQuantity = computeCartTotalQuantity(cart);
        cart.setTotalPrice(newTotalPrice);
        cart.setTotalQuantity(newTotalQuantity);
    }

    private BigDecimal computeCartTotalPrice(Cart cart){
        return cart.getOrderItems()
                .stream()
                .map(item -> item.getPhone().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Long computeCartTotalQuantity(Cart cart){
        return cart.getOrderItems()
                .stream()
                .mapToLong(item -> item.getQuantity())
                .sum();
    }

}
