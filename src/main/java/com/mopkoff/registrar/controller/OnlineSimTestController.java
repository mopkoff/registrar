package com.mopkoff.registrar.controller;

import com.mopkoff.registrar.repository.model.User;
import com.mopkoff.registrar.service.UserService;
import com.mopkoff.registrar.service.integration.OnlineSimService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("online-sim")
@RequiredArgsConstructor
public class OnlineSimTestController {

    private final OnlineSimService onlineSimService;

    @GetMapping("balance")
    public String findAll() {
        return onlineSimService.getBalance();
    }

}
