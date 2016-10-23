package ex.soft.domain.model;

import java.math.BigDecimal;

/**
 * Created by Alex108 on 21.10.2016.
 */
public abstract class Product<P extends Product> {

    private Long key;
    private BigDecimal price;

    public Product() {}

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        P that = (P) o;

        return key != null ? key.equals(that.getKey()) : false;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }

}
