package ex.soft.webview.validator.form;

import ex.soft.domain.form.OrderItemForm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Alex108 on 15.11.2016.
 */
@Component
@Qualifier("orderItemFormValidator")
public class OrderItemFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(OrderItemForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        OrderItemForm orderItemForm = (OrderItemForm) target;
        if (orderItemForm != null && orderItemForm.getQuantity() == null ){
            errors.rejectValue("quantity", "error.empty.quantity");
        } else if (orderItemForm != null && orderItemForm.getQuantity() <= 0L){
            errors.rejectValue("quantity", "error.notpositive.quantity");
        }
    }
}
