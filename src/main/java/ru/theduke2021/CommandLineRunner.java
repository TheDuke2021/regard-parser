package ru.theduke2021;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.UnknownHostException;
import java.util.List;

import static java.net.Proxy.Type.HTTP;
import static java.net.Proxy.Type.SOCKS;

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
    private final List<Proxy> proxies = List.of(
            new Proxy(HTTP, new InetSocketAddress("188.234.147.54", 8019)),
            new Proxy(SOCKS, new InetSocketAddress("87.117.11.57", 1080)),
            new Proxy(SOCKS, new InetSocketAddress("37.44.238.2", 53471)),
            new Proxy(HTTP, new InetSocketAddress("62.33.207.202", 80)),
            new Proxy(SOCKS, new InetSocketAddress("188.243.99.234", 1080)),
            new Proxy(HTTP, new InetSocketAddress("95.154.124.114", 58000)),
            new Proxy(HTTP, new InetSocketAddress("80.91.26.137", 3128)),
            new Proxy(HTTP, new InetSocketAddress("5.35.92.156", 80)),
            new Proxy(SOCKS, new InetSocketAddress("194.190.169.197", 3701)),
            new Proxy(SOCKS, new InetSocketAddress("185.90.101.36", 7046)),
            new Proxy(SOCKS, new InetSocketAddress("5.44.42.115", 58386)),
            new Proxy(SOCKS, new InetSocketAddress("92.51.78.66", 4153)),
            new Proxy(SOCKS, new InetSocketAddress("185.209.28.109", 80)),
            new Proxy(SOCKS, new InetSocketAddress("176.214.78.230", 5678)),
            new Proxy(SOCKS, new InetSocketAddress("46.29.116.3", 4145)),
            new Proxy(SOCKS, new InetSocketAddress("176.197.103.58", 4145))
    );


    public CommandLineRunner(String[] args) {
        try {
            CmdLineParser cmdLineParser = new CmdLineParser(this);
            cmdLineParser.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
        }

        //Сохранение результатов в CSV. Можно поменять на любой другой формат.
        itemsSaver = new CsvFileItemsSaver();
        jsoupService = new JsoupService(proxies);
        regardParser = new RegardParser(jsoupService);
    }

    public void parseAndSaveItems() {
        List<Item> items = regardParser.parseItems(query, count);
        System.out.println(items);
        itemsSaver.saveItems(items);
    }
}
