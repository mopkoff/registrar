package com.mopkoff.registrar.controller;

import com.mopkoff.registrar.model.domain.ProxyConfig;
import com.mopkoff.registrar.service.ProxyService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("proxies")
@RequiredArgsConstructor
public class ProxyController {

  private final ProxyService proxyService;

  @GetMapping
  public List<ProxyConfig> findAll() {
    return proxyService.findAll();
  }

  @PostMapping
  public ProxyConfig add(@RequestBody @Valid ProxyConfig proxyConfig) {
    return proxyService.save(proxyConfig.withId(null));
  }

  @PutMapping
  public ProxyConfig update(@RequestBody @Valid ProxyConfig proxyConfig) {
    return proxyService.save(proxyConfig);
  }
}
