package com.mopkoff.registrar.controller;

import com.mopkoff.registrar.service.integration.OnlineSimService;
import com.mopkoff.registrar.service.integration.model.BalanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("online-sim")
@RequiredArgsConstructor
public class OnlineSimTestController {

    private final OnlineSimService onlineSimService;

    @GetMapping("balance")
    public BalanceResponse getBalance() {
        return onlineSimService.getBalance();
    }

}
