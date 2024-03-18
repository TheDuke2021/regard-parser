package ru.theduke2021;

/**
 * Товар из маркетплейса.
 */
public class Item {

    private final String id;
    private final String title;
    private final String description;
    private final String imageUrl;
    private final String price;


    public Item(String id, String title, String description, String imageUrl, String price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
