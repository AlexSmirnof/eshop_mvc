package ex.soft.webview.validator.cart;

import ex.soft.domain.model.Cart;
import ex.soft.domain.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Alex108 on 15.11.2016.
 */
@Component("cartValidator")
public class CartValidator implements Validator {

    @Autowired
    @Qualifier("orderItemValidator")
    private Validator orderItemValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Cart.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Cart cart = (Cart) target;
        if (cart.getOrderItems() == null || cart.getOrderItems().isEmpty()){
            ValidationUtils.rejectIfEmpty(errors, "orderItems", "messages.empty.orderItems", "Cart is empty");
        } else{
            int index = 0;
            for (OrderItem orderItem : cart.getOrderItems()) {
                errors.pushNestedPath("orderItems[" + index + "]");
                ValidationUtils.invokeValidator(orderItemValidator, orderItem, errors);
                errors.popNestedPath();
                index++;
            }
        }

    }
}
