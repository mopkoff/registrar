package com.mopkoff.registrar.service.integration;

import com.mopkoff.registrar.config.OnlineSimProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class OnlineSimService {

    private static final String API_KEY_HEADER = "apikey";
    private static final String GET_BALANCE_URL = "getBalance.php";

    private final RestTemplate restTemplate;
    private final OnlineSimProperties onlineSimProperties;

    @SneakyThrows
    public String getBalance() {

        var request = new HttpEntity<>(getHeaders());
        var response = restTemplate.exchange(onlineSimBalanceUrl(), HttpMethod.GET, request, String.class);

        return response.getBody();
    }

    private String onlineSimBalanceUrl() {
        return onlineSimProperties.getUrl() + GET_BALANCE_URL;
    }

    private HttpHeaders getHeaders() {
        var headers = new HttpHeaders();
        headers.add(API_KEY_HEADER, onlineSimProperties.getApiKey());
        headers.add(HttpHeaders.ACCEPT, APPLICATION_JSON_VALUE);

        return headers;
    }
}
