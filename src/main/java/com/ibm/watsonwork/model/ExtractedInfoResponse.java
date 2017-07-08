package com.ibm.watsonwork.model;

import java.util.List;

import lombok.Data;

@Data
public class ExtractedInfoResponse {

    private List<Entity> entities;
    private List<Keyword> keywords;

}
