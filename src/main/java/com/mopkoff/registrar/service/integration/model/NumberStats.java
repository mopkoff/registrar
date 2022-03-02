package com.mopkoff.registrar.service.integration.model;

import lombok.Value;

import java.util.List;
import java.util.Map;

@Value
public class NumberStats {

    String name;
    int code;
    boolean enabled;
    Map<String, SimService> services;

    @Value
    public static class SimService {
        int id;
        int count;
        int code;
        int price;
        String service;
        String slug;
    }
}
