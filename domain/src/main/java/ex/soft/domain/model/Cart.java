package ex.soft.domain.model;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Alex108 on 19.10.2016.
 */
public class Cart<P extends Product> {

    private int totalQuantity;
    private BigDecimal totalPrice;
    private Map<P, Integer> productsAndQuantities;

    public Cart() {
        productsAndQuantities = new LinkedHashMap<>();
    }

    public int getTotalQuantity() {
        return productsAndQuantities.values().stream().reduce(Integer::sum).orElse(0);
    }

    public BigDecimal getTotalPrice() {
        return productsAndQuantities.keySet().stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<P, Integer> getProductsAndQuantities() {
        return productsAndQuantities;
    }

}