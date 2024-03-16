package dev.aminnorouzi.beecar.client;

import dev.aminnorouzi.beecar.model.Job;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobvisionClient implements Client {

    @Override
    public List<Job> getJobs() {
        return List.of();
    }

    @Override
    public String getName() {
        return "جاب‌ویژن";
    }

    @Override
    public String getUrl() {
        return "https://jobvision.ir/jobs/category/developer?keyword=%D8%AC%D8%A7%D9%88%D8%A7&remote=false&internship=false&suitableForDisabled=false&militaryServiceBenefit=false&page=1&sort=0";
    }
}
