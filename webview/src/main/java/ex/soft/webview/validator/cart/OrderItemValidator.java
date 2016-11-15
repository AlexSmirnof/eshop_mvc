package ex.soft.webview.validator.cart;

import ex.soft.domain.model.OrderItem;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Alex108 on 15.11.2016.
 */
@Component("orderItemValidator")
public class OrderItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(OrderItem.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        OrderItem orderItem = (OrderItem) target;
        if (orderItem != null && orderItem.getQuantity() == null ){
            errors.rejectValue("quantity", "error.empty.quantity");
        } else if (orderItem != null && orderItem.getQuantity() <= 0L){
            errors.rejectValue("quantity", "error.notpositive.quantity");
        }
    }
}
