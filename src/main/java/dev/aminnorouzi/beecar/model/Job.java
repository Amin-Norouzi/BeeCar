package dev.aminnorouzi.beecar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    private String title;
    private String company;
    private String location;
    private String contract;
    private String url;
    private String date;
    private String website;
}
