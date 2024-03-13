package ru.theduke2021;

import java.util.HashMap;
import java.util.Map;

/**
 * Товар из маркетплейса.
 */
public class Item {

    private String title;
    private String imageUrl;
    private Long price;
    /**
     * Список с произвольным количеством характеристик товара.
     * У товаров из разных категорий будут разные характеристики.
     * Например, характеристики товара "смартфон": диагональ экрана, объём оперативной памяти, поддержка 4G.
     */
    private final Map<String, String> characteristics;

    public Item(String title, String imageUrl, Long price, Map<String, String> characteristicsTemplate) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.price = price;
        characteristics = new HashMap<>(characteristicsTemplate);
    }

    public void setCharacteristic(String characteristic, String value) {
        characteristics.put(characteristic, value);
    }
}
