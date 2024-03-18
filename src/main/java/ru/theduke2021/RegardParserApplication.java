package ru.theduke2021;

import org.jsoup.HttpStatusException;

public class RegardParserApplication {

    public static void main(String[] args) {
        try {
            CommandLineRunner commandLineRunner = new CommandLineRunner(args);
            commandLineRunner.parseAndSaveItems();
        } catch (HttpStatusException e) {
            System.err.printf("Ошибка при парсинге: сервер вернул HTTP-код: %d. Возможно, Вы отправляете слишком много запросов. Подождите некоторое время и попробуйте снова%n", e.getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
