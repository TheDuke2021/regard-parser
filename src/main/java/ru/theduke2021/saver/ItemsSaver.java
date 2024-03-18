package ru.theduke2021.saver;

import ru.theduke2021.model.Item;

import java.io.IOException;
import java.util.List;

public interface ItemsSaver {

    void saveItems(List<Item> items) throws IOException;

}
