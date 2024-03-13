package ru.theduke2021;

public class RegardParserApplication {

    public static void main(String[] args) {
        CommandLineRunner commandLineRunner = new CommandLineRunner(args);
        commandLineRunner.parseAndSaveItems();
    }

}
