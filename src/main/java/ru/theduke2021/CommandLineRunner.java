package ru.theduke2021;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.net.http.HttpClient;
import java.util.List;

public class CommandLineRunner {


    /**
     * Количество товаров, которые нужно спарсить.
     */
    @Option(name = "--count", aliases = {"-c"})
    private int count = 500;

    /**
     * Количество товаров, начиная с первого, которые нужно пропустить при парсинге.
     */
    @Option(name = "--offset", aliases = {"-o"})
    private int offset = 0;

    /**
     * Название товара, по которому идёт парсинг.
     */
    @Argument(required = true, metaVar = "query - название товара, по которому идёт парсинг")
    private String query;


    private final ItemsSaver itemsSaver;
    private final RegardParser regardParser;


    public CommandLineRunner(String[] args) {
        try {
            CmdLineParser cmdLineParser = new CmdLineParser(this);
            cmdLineParser.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
        }

        //Сохранение результатов в CSV. Можно поменять на любой другой формат.
        itemsSaver = new CsvFileItemsSaver();
        regardParser = new RegardParser(HttpClient.newHttpClient());
    }

    public void parseAndSaveItems() {
        List<Item> items = regardParser.parse(query, count);
        itemsSaver.saveItems(items);
    }
}
