package ex.soft.webview.validator.form;

import ex.soft.domain.form.CartForm;
import ex.soft.domain.form.OrderItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Alex108 on 15.11.2016.
 */
@Component
@Qualifier("cartFormValidator")
public class CartFromValidator implements Validator {

    @Autowired
    @Qualifier("orderItemFormValidator")
    private Validator orderItemValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CartForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("+++Validate :: " + target);
        CartForm cartForm = (CartForm) target;
        if (cartForm.getOrderItemForms() == null || cartForm.getOrderItemForms().isEmpty()){
            ValidationUtils.rejectIfEmpty(errors, "orderItemForms", "messages.empty.orderItemForms", "Cart is empty");
        } else{
            int index = 0;
            for (OrderItemForm orderItemForm : cartForm.getOrderItemForms()) {
                errors.pushNestedPath("orderItemForms[" + index + "]");
                ValidationUtils.invokeValidator(orderItemValidator, orderItemForm, errors);
                errors.popNestedPath();
                index++;
            }
        }

    }
}
