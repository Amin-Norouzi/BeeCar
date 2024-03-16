package dev.aminnorouzi.beecar;

import dev.aminnorouzi.beecar.bot.Bot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@EnableScheduling
@SpringBootApplication
public class BeeCarApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeeCarApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(Bot bot) {
        return args -> {
            try {
                TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
                botsApi.registerBot(bot);
            } catch (TelegramApiException e) {
                System.out.println(e.getMessage()); // replace with logger
            }
        };
    }
}
