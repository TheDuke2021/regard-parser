package ru.theduke2021.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Товар из маркетплейса.
 */
@JsonPropertyOrder({"id", "title", "description", "imageUrl", "price"})
public record Item(String id, String title, String description, String imageUrl, String price) {

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
