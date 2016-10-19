package ex.soft.domain.model;

import java.math.BigDecimal;

/**
 * Created by Alex108 on 11.10.2016.
 */
public class Phone {

    private Long key;
    private String model;
    private String color;
    private String displaySize;
    private String length;
    private String width;
    private String camera;
    private BigDecimal price;

    public Phone() {}

    public Phone(Long key, String model, String color, String displaySize, String length, String width, String camera, BigDecimal price) {
        this.key = key;
        this.model = model;
        this.color = color;
        this.displaySize = displaySize;
        this.length = length;
        this.width = width;
        this.camera = camera;
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(String displaySize) {
        this.displaySize = displaySize;
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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone that = (Phone) o;

        return key != null ? key.equals(that.getKey()) : false;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "key=" + key +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", displaySize='" + displaySize + '\'' +
                ", length='" + length + '\'' +
                ", width='" + width + '\'' +
                ", camera='" + camera + '\'' +
                ", price=" + price +
                '}';
    }
}
