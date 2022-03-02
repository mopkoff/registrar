package com.mopkoff.registrar.service.integration.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Value;

@Value
public class RentStateResponse {

    public static final String ANSWERED = "TZ_NUM_ANSWER";

    int tzid;
    String response;
    @JsonAlias("msg")
    String message;
    String number;
    int time;
}
