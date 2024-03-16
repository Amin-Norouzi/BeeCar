package dev.aminnorouzi.beecar.bot.handler;

import dev.aminnorouzi.beecar.bot.Bot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public interface Handler {

    boolean supports(Update update);

    void handle(Update update, Bot bot);
}