package dev.aminnorouzi.beecar.task;

import dev.aminnorouzi.beecar.event.JobNotificationEvent;
import dev.aminnorouzi.beecar.model.Job;
import dev.aminnorouzi.beecar.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobTask {

    private final JobService service;
    private final ApplicationEventPublisher publisher;

    @Scheduled(cron = "0 */30 * * * ?", zone = "UTC")
    public void getJobsFromClients() {
        List<Job> entries = service.find();

        List<Job> jobs = service.filter(entries);
        if (!jobs.isEmpty()) {
            publisher.publishEvent(new JobNotificationEvent(jobs));
        }
    }
}
