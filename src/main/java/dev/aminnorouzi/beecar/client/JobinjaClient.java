package dev.aminnorouzi.beecar.client;

import dev.aminnorouzi.beecar.model.Job;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class JobinjaClient implements Client {

    @Override
    public List<Job> getJobs() {
        try {
            List<Job> entries = new ArrayList<>();
            Document document = getDocument();

            Elements elements = document.select("li.c-jobListView__item");
            for (Element element : elements) {
                String title = element.select("h2.c-jobListView__title a").text().trim();
                String date = element.select("span.c-jobListView__passedDays").text().trim();
                String url = element.select("a.c-jobListView__titleLink").attr("href");

                Elements info = element.select("ul.o-listView__itemComplementInfo span");
                String company = info.get(0).text().trim();
                String location = info.get(1).text().trim();
                String contract = info.get(3).text().trim();

                Job entry = new Job(title, company, location, contract, url, date, getName());
                entries.add(entry);
            }

            return entries;
        } catch (IOException exception) {
            log.error(exception.getMessage(), exception);
        }

        return List.of();
    }

    @Override
    public String getName() {
        return "جابینجا";
    }

    @Override
    public String getUrl() {
        return "https://jobinja.ir/jobs?&filters%5Bjob_categories%5D%5B%5D=&filters%5Bkeywords%5D%5B0%5D=java&filters%5Blocations%5D%5B%5D=&preferred_before=1709504390&sort_by=published_at_desc";
    }
}
