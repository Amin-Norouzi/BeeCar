package dev.aminnorouzi.beecar.service;

import dev.aminnorouzi.beecar.bot.Bot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    public boolean authenticate(User user, Bot bot) {
        return user.getId().equals(bot.getUser());
    }
}
