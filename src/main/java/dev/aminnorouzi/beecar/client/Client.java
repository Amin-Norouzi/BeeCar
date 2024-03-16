package dev.aminnorouzi.beecar.client;

import dev.aminnorouzi.beecar.model.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public interface Client {

    List<Job> getJobs();

    String getName();

    String getUrl();

    default Document getDocument() throws IOException {
        return Jsoup.connect(getUrl()).get();
    }
}
