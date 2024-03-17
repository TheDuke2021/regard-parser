package ru.theduke2021;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class JsoupService {

    private final List<Proxy> proxies;

    public JsoupService(List<Proxy> proxies) {
        this.proxies = proxies;
    }

    private Proxy getRandomProxy() {
        int randomIndex = ThreadLocalRandom.current().nextInt(proxies.size());

        return proxies.get(randomIndex);
    }

    public Document getDocument(String url) {
        Proxy proxy = getRandomProxy();

        try {
            return Jsoup.connect(url)
                    .proxy(proxy)
                    .timeout(5000)
                    .get();
        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("PROBLEM: " + proxy);
            return getDocument(url);
        }
    }

}
