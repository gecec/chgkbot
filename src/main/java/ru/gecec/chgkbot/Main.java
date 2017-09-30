package ru.gecec.chgkbot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ru.gecec.chgkbot.bots.CommandsHandler;

@SpringBootApplication
public class Main implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    //access command line arguments
    @Override
    public void run(String... args) throws Exception {
        ApiContextInitializer.init();

        TelegramBotsApi botsApi =  new TelegramBotsApi();

        try {
            botsApi.registerBot(new CommandsHandler());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
