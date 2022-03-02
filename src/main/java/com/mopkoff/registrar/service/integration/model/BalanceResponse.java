package com.mopkoff.registrar.service.integration.model;

import lombok.Value;

@Value
public class BalanceResponse {

    String response;
    String balance;
    int zbalance;

    public int getIntBalance() {
        return Integer.parseInt(balance.replaceFirst("\\.\\d\\d$", ""));
    }
}
