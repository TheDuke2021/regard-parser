package ru.theduke2021.component;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import ru.theduke2021.model.Item;
import ru.theduke2021.saver.CsvFileItemsSaver;
import ru.theduke2021.saver.ItemsSaver;

import java.io.IOException;
import java.util.List;

/**
 * Класс, отвечающий за обработку передаваемых в коносоли аргументов и запуск парсинга.
 */
public class CommandLineRunner {


    /**
     * Количество товаров, которые нужно спарсить.
     */
    @Option(name = "--count", aliases = {"-c"})
    private int count = 500;


    /**
     * Название товара, по которому идёт парсинг.
     */
    @Argument(required = true, metaVar = "query - название товара, по которому идёт парсинг")
    private String query;


    private final ItemsSaver itemsSaver;
    private final RegardParser regardParser;
    private final JsoupService jsoupService;


    public CommandLineRunner(String[] args) throws CmdLineException {
        CmdLineParser cmdLineParser = new CmdLineParser(this);
        cmdLineParser.parseArgument(args);

        //Сохранение результатов в CSV. Можно поменять на любой другой формат.
        itemsSaver = new CsvFileItemsSaver();
        jsoupService = new JsoupService();
        regardParser = new RegardParser(jsoupService);
    }

    public void parseAndSaveItems() throws IOException {
        System.out.println("Начало парсинга");
        List<Item> items = regardParser.parseItems(query, count);
        itemsSaver.saveItems(items);
        System.out.println("Парсинг завершен");
    }
}
