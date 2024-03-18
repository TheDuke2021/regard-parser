package ru.theduke2021;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegardParser {

    private final String BASE_URL = "https://www.regard.ru/catalog?";
    private final String DOMAIN =  "https://www.regard.ru";

    private final Map<String, String> REQUEST_HEADERS  = Map.of(
            "Cookie", "perPage=100;"
    );

    private final JsoupService jsoupService;

    public RegardParser(JsoupService jsoupService) {
        this.jsoupService = jsoupService;
    }


    private Item parseItem(Element itemCard) {
        String id = itemCard.selectFirst(".CardId_id__mCbo0").text().replaceAll("\\D", "");
        String title = itemCard.selectFirst(".CardText_title__7bSbO").text();

        Element itemCardDescription = itemCard.selectFirst(".CardText_text__fZPl_");
        String description = itemCardDescription == null ? null : itemCardDescription.text();

        Element itemCardImage = itemCard.selectFirst(".CardImageSlider_image__W65ZP");
        String imageUrl = itemCardImage == null ? null : DOMAIN + itemCardImage.attr("src");

        Element itemCardPrice = itemCard.selectFirst(".Price_price__m2aSe");
        String price = itemCardPrice == null ? null : itemCardPrice.text().replaceAll("\\D", "");

        return new Item(id, title, description, imageUrl, price);
    }

    public List<Item> parseItems(String query, int count) throws IOException {
        query = URLEncoder.encode(query, StandardCharsets.UTF_8);

        List<Item> items = new ArrayList<>();
        int currentPage = 1;

        while (true) {
            String url = BASE_URL + "search=" + query + "&page=" + currentPage;

            System.out.println("Парсинг URL: " + url);

            Document document = jsoupService.getDocument(url, REQUEST_HEADERS);

            Elements itemCards = document.select(".Card_row__6_JG5");

            if (itemCards.isEmpty()) {
                System.out.println("Товаров больше не найдено");
                return items;
            }

            for (Element itemCard : itemCards) {
                items.add(parseItem(itemCard));

                if (items.size() == count) {
                    System.out.printf("Спарсено товаров: %d / %d%n", items.size(), count);
                    return items;
                }
            }

            System.out.printf("Спарсено товаров: %d / %d%n", items.size(), count);

            currentPage++;
        }
    }
}
