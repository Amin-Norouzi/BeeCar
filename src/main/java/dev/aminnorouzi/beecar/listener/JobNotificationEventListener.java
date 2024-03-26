package dev.aminnorouzi.beecar.listener;

import dev.aminnorouzi.beecar.bot.Bot;
import dev.aminnorouzi.beecar.event.JobNotificationEvent;
import dev.aminnorouzi.beecar.model.Job;
import dev.aminnorouzi.beecar.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobNotificationEventListener implements ApplicationListener<JobNotificationEvent> {

    private final String template = "<b>\uD83D\uDC1D %s</b>\n\n- %s\n- %s\n- %s\n\n#%s %s";

    private final JobService service;
    private final Bot bot;

    private Integer counter = 1;

    @Override
    public void onApplicationEvent(JobNotificationEvent event) {
        if (counter > 1)  {
            List<Job> jobs = service.checkup(event.getJobs());

            for (Job job : jobs) {
                String text = template.formatted(
                        job.getTitle(), job.getCompany(), job.getLocation(), job.getContract(), job.getWebsite(), job.getDate()
                );

                SendMessage message = getMessage(job, text);

                try {
                    bot.execute(message);
                } catch (TelegramApiException exception) {
                    log.error(exception.getMessage(), exception);
                }
            }
        }

        counter++;
    }

    private SendMessage getMessage(Job job, String text) {
        SendMessage message = new SendMessage();
        message.setText(text);
        message.disableWebPagePreview();
        message.setParseMode(ParseMode.HTML);

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("مشاهده");
        button.setUrl(job.getUrl());

        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(List.of(
                List.of(button)
        ));

        message.setReplyMarkup(keyboard);
        message.setChatId(bot.getUser());
        return message;
    }
}
