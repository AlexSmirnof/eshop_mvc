package ex.soft.domain.model;

import java.math.BigDecimal;

/**
 * Created by Alex108 on 11.10.2016.
 */
public class Phone {

    private Long key;
    private String model;
    private BigDecimal price;

    public Phone() {}

    public Phone(Long key, String model, BigDecimal price) {
        this.key = key;
        this.model = model;
        this.price = price;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

        Phone that = (Phone) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;

    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "key=" + key +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}
