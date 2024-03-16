package dev.aminnorouzi.beecar.bot.handler;

import dev.aminnorouzi.beecar.bot.Bot;
import dev.aminnorouzi.beecar.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
@RequiredArgsConstructor
public class UpdateHandler implements Handler {

    private final UserService service;

    @Override
    public boolean supports(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }

    @SneakyThrows
    @Override
    public void handle(Update update, Bot bot) {
        User user = update.getMessage().getFrom();
        String text = update.getMessage().getText();

        if (text.equals("/start")) {
            SendMessage message = new SendMessage();
            message.setChatId(user.getId());

            if (service.authenticate(user, bot)) {
                message.setText("Thanks Amin! You will be notified for job postings.");
            } else {
                message.setText("No Access! It's a private bot you stupid.");
            }

            bot.execute(message);
        }
    }
}