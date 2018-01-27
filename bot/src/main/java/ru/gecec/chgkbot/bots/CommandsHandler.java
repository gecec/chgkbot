package ru.gecec.chgkbot.bots;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;
import ru.gecec.chgkbot.commands.StartCommand;
import ru.gecec.chgkbot.commands.StopCommand;
import ru.gecec.chgkbot.util.BotConfig;
import ru.gecec.chgkbot.commands.HelloCommand;
import ru.gecec.chgkbot.commands.HelpCommand;
import ru.gecec.chgkbot.enums.Emoji;

public class CommandsHandler extends TelegramLongPollingCommandBot {
    public static final String LOGTAG = "COMMANDSHANDLER";

    public CommandsHandler() {
        super(BotConfig.BOT_USER);

        register(new HelloCommand());
        register(new StartCommand());
        register(new StopCommand());

        HelpCommand helpCommand = new HelpCommand(this);
        register(helpCommand);

        registerDefaultAction((absSender, message) -> {
            SendMessage commandUnknownMessage = new SendMessage();
            commandUnknownMessage.setChatId(message.getChatId());
            commandUnknownMessage.setText("The command '" + message.getText() + "' is not known by this bot. Here comes some help " + Emoji.AMBULANCE);
            try {
                absSender.sendMessage(commandUnknownMessage);
            } catch (TelegramApiException e) {
                BotLogger.error(LOGTAG, e);
            }
            helpCommand.execute(absSender, message.getFrom(), message.getChat(), new String[] {});
        });
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();

            if (message.hasText()) {
                SendMessage echoMessage = new SendMessage();
                echoMessage.setChatId(message.getChatId());
                echoMessage.setText("Hey here's your message:\n" + message.getText());

                try {
                    sendMessage(echoMessage);
                } catch (TelegramApiException e) {
                    BotLogger.error(LOGTAG, e);
                }
            }
        }

    }

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }
}
