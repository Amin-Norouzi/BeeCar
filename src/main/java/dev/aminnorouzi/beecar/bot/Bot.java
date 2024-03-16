package dev.aminnorouzi.beecar.bot;

import dev.aminnorouzi.beecar.bot.handler.UpdateHandler;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Data
@Component
public class Bot extends TelegramLongPollingBot {

    private final UpdateHandler handler;
    private final String token;
    private final String username;
    private final Long user;

    public Bot(UpdateHandler handler, String token, String username, Long user) {
        super(token);
        this.handler = handler;
        this.token = token;
        this.username = username;
        this.user = user;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (handler.supports(update)) {
            handler.handle(update, this);
        }
    }

    @Override
    public String getBotUsername() {
        return this.username;
    }
}