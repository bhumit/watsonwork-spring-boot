package com.ibm.watsonwork.model;

import java.util.List;

import lombok.Data;

@Data
public class NewsResponse {

    public String status;
    public String source;
    public String sortBy;
    public String code;
    public List<Article> articles = null;
}
