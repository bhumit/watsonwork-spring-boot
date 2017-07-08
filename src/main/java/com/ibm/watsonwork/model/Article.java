package com.ibm.watsonwork.model;

import java.util.Date;

import lombok.Data;

@Data
public class Article {

    public String author;
    public String title;
    public String description;
    public String url;
    public String urlToImage;
    public Date publishedAt;
}
