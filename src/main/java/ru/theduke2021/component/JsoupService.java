package ru.theduke2021.component;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

public class JsoupService {

    public Document getDocument(String url, Map<String, String> headers) throws IOException {
        return Jsoup.connect(url)
                .headers(headers)
                .get();
    }
}
