package ru.theduke2021;

import java.util.Map;

/**
 * Товар из маркетплейса.
 */
public class Item {

    private final String title;
    private final String imageUrl;
    private final String price;
    /**
     * Список с произвольным количеством характеристик товара.
     * У товаров из разных категорий будут разные характеристики.
     * Например, характеристики товара "смартфон": диагональ экрана, объём оперативной памяти, поддержка 4G.
     */
    private final Map<String, String> characteristics;

    public Item(String title, String imageUrl, String price, Map<String, String> characteristics) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.price = price;
        this.characteristics = characteristics;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price='" + price + '\'' +
                ", characteristics=" + characteristics +
                '}';
    }
}
