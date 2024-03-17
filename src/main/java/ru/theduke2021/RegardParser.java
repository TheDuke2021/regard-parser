package ru.theduke2021;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class RegardParser {

    private final String BASE_URL = "https://www.regard.ru/catalog?";
    private final String DOMAIN =  "https://www.regard.ru";

    private final JsoupService jsoupService;

    public RegardParser(JsoupService jsoupService) {
        this.jsoupService = jsoupService;
    }

    private Map<String, String> parseCharacteristics(Element itemCard) {
        Map<String, String> parsedCharacteristics = new HashMap<>();

        String itemPageUrl = DOMAIN + itemCard.selectFirst("a").attr("href");

            Document itemPage = jsoupService.getDocument(itemPageUrl);

            Elements characteristics = itemPage.select(".ShortCharacteristics_ul__S8KmA > li ");

            for (Element characteristic : characteristics) {
                String characteristicTitle = characteristic.firstElementChild().firstChild().outerHtml();
                String characteristicValue = characteristic.lastElementChild().text().replaceAll("&nbsp", " ");

                parsedCharacteristics.put(characteristicTitle, characteristicValue);
            }

        return parsedCharacteristics;
    }

    private Map<String, String> parseCharacteristicsTemplate(Element itemCard) {
        Map<String, String> characteristicsTemplate = parseCharacteristics(itemCard);
        characteristicsTemplate.forEach((key, value) -> characteristicsTemplate.put(key, null));

        return characteristicsTemplate;
    }

    private Item parseItem(Element itemCard, Map<String, String> characteristicsTemplate) {
        String title = itemCard.selectFirst(".CardText_title__7bSbO").text();
        String imageUrl = DOMAIN + itemCard.selectFirst(".CardImageSlider_image__W65ZP").attr("src");
        String price = itemCard.selectFirst(".Price_price__m2aSe").text().replaceAll("[^\\d]", "");

        Map<String, String> characteristics = new HashMap<>(characteristicsTemplate);
        Map<String, String> parsedCharacteristics = parseCharacteristics(itemCard);

        Iterator<Map.Entry<String, String>> parsedCharacteristicsIterator = parsedCharacteristics.entrySet().iterator();

        while (parsedCharacteristicsIterator.hasNext()) {
            Map.Entry<String, String> parsedCharacteristic = parsedCharacteristicsIterator.next();
            String key = parsedCharacteristic.getKey();
            String value = parsedCharacteristic.getValue();

            if (characteristics.containsKey(key))
                characteristics.put(key, value);
            else
                characteristics.remove(key);

        }

        return new Item(title, imageUrl, price, characteristics);
    }


    public List<Item> parseItems(String query, int count) {
        query = URLEncoder.encode(query, StandardCharsets.UTF_8);

        List<Item> items = new ArrayList<>();
        Map<String, String> characteristicsTemplate = null;
        int currentPage = 1;

        while (true) {
            String url = BASE_URL + "search=" + query + "&page=" + currentPage;

                Document document = jsoupService.getDocument(url);

                Elements itemCards = document.select(".Card_row__6_JG5");

                if (itemCards.isEmpty())
                    return items;

                if (characteristicsTemplate == null)
                    characteristicsTemplate = parseCharacteristicsTemplate(itemCards.get(0));

                for (Element itemCard : itemCards) {
                    items.add(parseItem(itemCard, characteristicsTemplate));

                    System.out.println("PARSED: " + items.size() + " / " + count);

                    if (items.size() == count)
                        return items;

                }
        }
    }

}
