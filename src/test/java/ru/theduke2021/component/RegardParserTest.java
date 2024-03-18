package ru.theduke2021.component;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.theduke2021.model.Item;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RegardParserTest {

    private final String mockHtmlFilename = "mockHtml.html";

    @Mock
    private JsoupService jsoupService;

    private RegardParser underTest;


    @BeforeEach
    void beforeEach() {
        underTest = new RegardParser(jsoupService);
    }


    @Test
    void shouldParseItemsCorrectly() throws IOException {
        Document mockDocument = Jsoup.parse(RegardParserTest.class.getClassLoader().getResourceAsStream(mockHtmlFilename), "UTF-8", "/");

        when(jsoupService.getDocument(anyString(), anyMap())).thenReturn(mockDocument);

        final String query = "iphone";
        final int count = 5;

        List<Item> expected = List.of(
                new Item("77896",
                        "Смартфон Apple iPhone 15 Pro Max 256Gb Black Titanium (MU2N3ZA/A)",
                        "экран 6.7\", OLED, 1290x2796, 256 Гб встроенной памяти, стандарт связи: 2G, 3G, LTE, 5G, поддержка 2-х SIM-карт, аккумулятор 4441 мАч",
                        "https://www.regard.ru/api/site/cacheimg/goods/5980534/160",
                        "150890"),
                new Item(
                        "80928",
                        "Смартфон Apple iPhone 15 Pro 1Tb Natural Titanium (MTUT3J/A)",
                        "экран 6.1\", OLED, 1179x2556, 1 Тб встроенной памяти, стандарт связи: 2G, 3G, LTE, 5G, аккумулятор 3274 мАч",
                        "https://www.regard.ru/api/site/cacheimg/goods/5997491/160",
                        "201790"
                ),
                new Item(
                        "665916",
                        "Смартфон Apple iPhone 15 Plus 256Gb Blue (MVJM3CH/A)",
                        "экран 6.7\", OLED, 1290x2796, 6 Гб оперативной памяти, 256 Гб встроенной памяти, стандарт связи: 2G, 3G, LTE, 5G, поддержка 2-х SIM-карт, аккумулятор 4383 мАч",
                        "https://www.regard.ru/api/site/cacheimg/goods/6071852/160",
                        "117110"
                ),
                new Item(
                        "83461",
                        "Смартфон Apple iPhone 15 Pro Max 256Gb Black Titanium (MU6P3J/A)",
                        "экран 6.7\", OLED, 1290x2796, 256 Гб встроенной памяти, стандарт связи: 2G, 3G, LTE, 5G, аккумулятор 4441 мАч",
                        "https://www.regard.ru/api/site/cacheimg/goods/5996360/160",
                        "152840"
                ),
                new Item(
                        "83459",
                        "Смартфон Apple iPhone 15 Pro 256Gb Black Titanium (MTQ83CH/A)",
                        "экран 6.1\", OLED, 1179x2556, 256 Гб встроенной памяти, стандарт связи: 2G, 3G, LTE, 5G, поддержка 2-х SIM-карт, аккумулятор 3274 мАч",
                        "https://www.regard.ru/api/site/cacheimg/goods/5996332/160",
                        "135820"
                )
        );

        assertEquals(expected, underTest.parseItems(query, count));
    }

}
