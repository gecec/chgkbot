package ru.gecec.chgkbot.bots;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ru.gecec.chgkbot.util.BotConfig;

public class TestBot extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText("Привет!");
            try {
                sendMessage(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return BotConfig.BOT_USER;
    }

    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }

    public void onClosing() {

    }
}
