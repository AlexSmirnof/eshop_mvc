package ex.soft.domain.model;

import java.util.Map;

/**
 * Created by Alex108 on 19.10.2016.
 */
public class Cart {

    private Map<Phone, Long> products;

    public Cart() {}

    public Map<Phone, Long> getProducts() {
        return products;
    }

    public void setProducts(Map<Phone, Long> products) {
        this.products = products;
    }
}
