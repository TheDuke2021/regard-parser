package ru.theduke2021;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CsvFileItemsSaver implements ItemsSaver {

    private final String FILENAME = "regard-items.csv";

    private final CsvMapper csvMapper = new CsvMapper();


    @Override
    public void saveItems(List<Item> items) throws IOException {
        File file = new File(FILENAME);

        CsvSchema csvSchema = csvMapper.schemaFor(Item.class)
                .withHeader();

        csvMapper.writer(csvSchema).writeValue(file, items);
        System.out.println("Результат парсинга сохранён в файле " + file.getAbsolutePath());
    }

}
