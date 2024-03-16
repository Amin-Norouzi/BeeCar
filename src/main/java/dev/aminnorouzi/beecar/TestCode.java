package dev.aminnorouzi.beecar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TestCode {
    public static void main(String[] args) {
        try {
            // Fetch the HTML content from the URL
            Document document = Jsoup.connect("https://jobinja.ir/jobs?&filters%5Bjob_categories%5D%5B%5D=&filters%5Bkeywords%5D%5B0%5D=java&filters%5Blocations%5D%5B%5D=&preferred_before=1709504390&sort_by=published_at_desc").get();

            // Select the specific HTML elements to extract data
            Elements jobElements = document.select("li.c-jobListView__item");

            // Loop through each job element and extract the required data
            for (Element jobElement : jobElements) {
                // Extracting job title and posted date
                String title = jobElement.select("h2.c-jobListView__title").text().trim();
                String postedDate = jobElement.select("span.c-jobListView__passedDays").text().trim();

                // Extracting company name
                String companyName = jobElement.select("span:contains(نرم افزاری)").text().trim();

                // Extracting location
                String location = jobElement.select("li.c-jobListView__metaItem:contains(مکان)").text().trim();

                // Extracting contract type and salary
                String contractTypeAndSalary = jobElement.select("li.c-jobListView__metaItem:contains(قرارداد)").text().trim();

                // Extracting job URL
                String jobUrl = jobElement.select("a.c-jobListView__titleLink").attr("href");

                // Print the extracted data
                System.out.println("Job Title: " + title + " " + postedDate);
                System.out.println("Company Name: " + companyName);
                System.out.println("Location: " + location);
                System.out.println("Contract Type and Salary: " + contractTypeAndSalary);
                System.out.println("Job URL: " + jobUrl);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}