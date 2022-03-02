package com.mopkoff.registrar.service.integration;

import com.mopkoff.registrar.config.OnlineSimProperties;
import com.mopkoff.registrar.service.integration.model.BalanceResponse;
import com.mopkoff.registrar.service.integration.model.NumberStats;
import com.mopkoff.registrar.service.integration.model.RentNumResponse;
import com.mopkoff.registrar.service.integration.model.RentStateResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class OnlineSimService {

    private static final String API_KEY_HEADER = "apikey";
    private final RestTemplate restTemplate;
    private final OnlineSimProperties onlineSimProperties;

    public RentStateResponse rentNumberGoogle() {
        var stats = getServiceStats(OnlineSimProperties.SERVICE_GOOGLE);
        var balance = getBalance();
        if (balance.getIntBalance() < stats.getPrice()){
            throw new IllegalStateException("Not enough money");
        }
        var numberResponse = getNumber(OnlineSimProperties.SERVICE_GOOGLE);
        var number = getState(numberResponse.getTzid());
        return number;
    }

    @SneakyThrows
    public BalanceResponse getBalance() {
        var response = restTemplate.exchange(onlineSimProperties.getBalanceUrl(), HttpMethod.GET, request(), BalanceResponse.class);
        return response.getBody();
    }

    public NumberStats.SimService getServiceStats(String service) {
        return getNumberStats().getServices().get(service);
    }

    @SneakyThrows
    public NumberStats getNumberStats() {
        var response = restTemplate.exchange(onlineSimProperties.getNumbersStats(), HttpMethod.GET, request(), NumberStats.class);
        var body = response.getBody();
        if (!body.isEnabled()) {
            throw new IllegalStateException("bad status");
        }
        return body;
    }

    @SneakyThrows
    public RentNumResponse getNumber(String service) {
        var response = restTemplate.exchange(onlineSimProperties.getNumber(service), HttpMethod.GET, request(), RentNumResponse.class);
        var body = response.getBody();
        if (body.getResponse() != 1) {
            throw new IllegalStateException("bad status");
        }
        return body;
    }

    @SneakyThrows
    public RentStateResponse getState(int tzId) {
        var response = restTemplate.exchange(onlineSimProperties.getState(), HttpMethod.GET, request(), RentStateResponse.class);
        var body = response.getBody();
        log.info("Rented: " + body);
        return body;
    }

    private HttpEntity request() {
        return new HttpEntity<>(getHeaders());
    }

    private HttpHeaders getHeaders() {
        var headers = new HttpHeaders();
        headers.add(API_KEY_HEADER, onlineSimProperties.getApiKey());
        headers.add(HttpHeaders.ACCEPT, APPLICATION_JSON_VALUE);

        return headers;
    }
}
