package ru.theduke2021.saver;

import ru.theduke2021.model.Item;

import java.io.IOException;
import java.util.List;

/**
 * Интерфейс для сохранения результатов парсинга
 */
public interface ItemsSaver {

    /**
     * Сохраняет результат парсинга в необходимом формате.
     * @param items спарсенные товары, которые нужно сохранить
     * @throws IOException
     */
    void saveItems(List<Item> items) throws IOException;

}
