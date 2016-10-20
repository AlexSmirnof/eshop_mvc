package ex.soft.domain.model;

import java.math.BigDecimal;

/**
 * Created by Alex108 on 11.10.2016.
 */
public class Phone extends Product{

    private String model;
    private String color;
    private String displaySize;
    private String length;
    private String width;
    private String camera;

    public Phone() {}

    public Phone(Long key, String model, String color, String displaySize, String length, String width, String camera, BigDecimal price) {
        super.setKey(key);
        this.model = model;
        this.color = color;
        this.displaySize = displaySize;
        this.length = length;
        this.width = width;
        this.camera = camera;
        super.setPrice(price);
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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
    public String toString() {
        return "Phone{" +
                "key=" + super.getKey() +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", displaySize='" + displaySize + '\'' +
                ", length='" + length + '\'' +
                ", width='" + width + '\'' +
                ", camera='" + camera + '\'' +
                ", price=" + super.getPrice() +
                '}';
    }
}
