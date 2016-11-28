package ex.soft.service.form;

import ex.soft.domain.form.CartForm;
import ex.soft.domain.form.OrderItemForm;
import ex.soft.domain.model.Cart;
import ex.soft.domain.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex108 on 24.11.2016.
 */
@Service
public class CartFormService {

    public CartForm mapCartToCartForm(final Cart cart){
        final CartForm cartForm = getCartForm();
        cartForm.setOrderItemForms(mapOrderItemsToOrderForms(cart.getOrderItems()));
        cartForm.setTotalPrice(cart.getTotalPrice());
        cartForm.setTotalQuantity(cart.getTotalQuantity());
        return cartForm;
    }

    public List<OrderItemForm> mapOrderItemsToOrderForms(final List<OrderItem> orderItems){
        final List<OrderItemForm> orderItemForms = new ArrayList<>();
        for (final OrderItem orderItem : orderItems) {
            final OrderItemForm orderItemForm = getOrderItemForm();
            orderItemForm.setPhone(orderItem.getPhone());
            orderItemForm.setQuantity(orderItem.getQuantity());
            orderItemForms.add(orderItemForm);
        }
        return orderItemForms;
    }


    private CartForm getCartForm(){
        return new CartForm();
    }

    private OrderItemForm getOrderItemForm(){
        return new OrderItemForm();
    }

}
