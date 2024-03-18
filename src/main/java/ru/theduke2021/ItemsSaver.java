package ru.theduke2021;

import java.io.IOException;
import java.util.List;

public interface ItemsSaver {

    void saveItems(List<Item> items) throws IOException;

}
