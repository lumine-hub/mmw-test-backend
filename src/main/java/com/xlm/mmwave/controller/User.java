package com.xlm.mmwave.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    @JsonProperty("usr_id")
    private int usrId;

    @JsonProperty("usr_desp")
    private UserDetails usrDesp;

    @JsonProperty("is_on")
    private boolean isOn;

    // 内部类 UserDetails
    @Data
    public static class UserDetails {
        @JsonProperty("name")
        private String name;

        @JsonProperty("address")
        private String address;

        @JsonProperty("province")
        private String province;

        @JsonProperty("city")
        private String city;

        @JsonProperty("age")
        private int age;

        @JsonProperty("gender")
        private int gender;

        @JsonProperty("health")
        private String health;
    }
}
