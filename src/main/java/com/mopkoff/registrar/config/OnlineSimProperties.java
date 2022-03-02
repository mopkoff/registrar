package com.mopkoff.registrar.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.onlinesim")
public class OnlineSimProperties {
    private String url;
    private String apiKey;
    public static final String SERVICE_GOOGLE = "google";
    public static final String SERVICE_YANDEX = "yandex";
    private static final String GET_BALANCE_URL = "getBalance.php";
    private static final String GET_NUMBER_URL = "getNum.php";
    private static final String GET_STATE_URL = "getState.php";
    private static final String GET_NUMBER_STATS_URL = "getNumbersStats.php";

    public String getBalanceUrl() {
        return getUrl() + GET_BALANCE_URL;
    }

    public String getNumbersStats() {
        return getUrl() + GET_NUMBER_STATS_URL + "?county=7";
    }

    public String getNumber(String service) {
        return getUrl() + GET_NUMBER_URL + "?county=7&service=" + service;
    }

    public String getState() {
        return getUrl() + GET_STATE_URL + "?county=7";
    }
}
