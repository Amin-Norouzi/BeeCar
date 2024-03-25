package dev.aminnorouzi.beecar.service;

import dev.aminnorouzi.beecar.client.Client;
import dev.aminnorouzi.beecar.client.JobinjaClient;
import dev.aminnorouzi.beecar.client.JobvisionClient;
import dev.aminnorouzi.beecar.model.Job;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobService {

    private final Map<String, Job> jobs = new HashMap<>();

    private final Set<Client> clients = Set.of(
            new JobinjaClient(),
            new JobvisionClient()
    );

    public List<Job> find() {
        List<Job> entries = new ArrayList<>();

        clients.forEach(client -> entries.addAll(client.getJobs()));

        return entries;
    }

    public List<Job> filter(List<Job> entries) {
        Iterator<Job> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Job entry = iterator.next();
            if (jobs.containsKey(entry.getUrl())) {
                iterator.remove();
            } else {
                jobs.put(entry.getUrl(), entry);
            }
        }

        return entries;
    }

    public List<Job> checkup(List<Job> entries) {
        String keyword = "نامشخص";

        entries.forEach(entry -> {
            if (entry.getCompany() == null) entry.setCompany(keyword);
            if (entry.getContract() == null) entry.setContract(keyword);
            if (entry.getLocation() == null) entry.setLocation(keyword);
        });

        return entries;
    }

}
