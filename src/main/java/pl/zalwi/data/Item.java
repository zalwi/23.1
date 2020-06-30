package pl.zalwi.data;

import java.math.BigDecimal;

public class Item {
    private String name;
    private ItemCategory category;
    private BigDecimal price;

    public Item(String name, ItemCategory category, BigDecimal price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
