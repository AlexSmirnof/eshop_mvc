package ex.soft.service.form;

import ex.soft.domain.form.OrderForm;
import ex.soft.domain.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Alex108 on 24.11.2016.
 */
@Service
public class OrderFormService {

    @Autowired
    private CartFormService cartFormService;

    public OrderForm mapCartToOrderForm(final Cart cart){
        final OrderForm orderForm = getOrderForm();
        orderForm.setOrderItemForms(cartFormService.mapOrderItemsToOrderForms(cart.getOrderItems()));
        orderForm.setTotalQuantity(cart.getTotalQuantity());
        orderForm.setTotalPrice(cart.getTotalPrice());
        return orderForm;
    }

    private OrderForm getOrderForm(){
        return new OrderForm();
    }

}
