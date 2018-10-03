package com.tipsandtricks.testdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;

import java.io.File;
import java.io.IOException;

@Getter
@ToString
public class Data {

    @JsonProperty("firstname")
    String firstname;

    @JsonProperty("lastname")
    String lastname;

    @JsonProperty("dob")
    String DOB;

    @JsonProperty("email")
    String email;

    @JsonProperty("address")
    String address;

    @JsonProperty("cc")
    CC cc;

    @JsonProperty("product")
    Product product;

    @ToString
    @Getter
    static class CC {

        @JsonProperty("number")
        String number;

        @JsonProperty("billing")
        String billingAddress;
    }

    @ToString
    @Getter
    static class Product {

        @JsonProperty("searchCriteria")
        String criteria;
    }

    public static Data get(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filename), Data.class);
    }

}