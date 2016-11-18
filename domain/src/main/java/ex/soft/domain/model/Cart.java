package ex.soft.domain.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component("cart")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart extends AbstractCart {

    public Cart() {}

    @Override
    public String toString() {
        return "Cart{" + super.toString() + "}";
    }
}