package ru.theduke2021;

import java.net.http.HttpClient;
import java.util.List;

public class RegardParser {

    private final HttpClient httpClient;

    public RegardParser(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public List<Item> parse(String query, int count) {
        return null;
    }

}
