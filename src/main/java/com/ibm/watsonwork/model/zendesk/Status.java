package com.ibm.watsonwork.model.zendesk;


public enum Status {
    New("##F5CA00"),
    Open("#E82A2A"),
    Pending("#59BBE0"),
    Solved("#828282");

    private final String colour;

    Status(String colour) {
        this.colour = colour;
    }

    public String getValue() {
        return colour;
    }
}
