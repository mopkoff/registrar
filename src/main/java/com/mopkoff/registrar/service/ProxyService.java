package com.mopkoff.registrar.service;

import com.mopkoff.registrar.exception.NotFoundException;
import com.mopkoff.registrar.model.domain.ProxyConfig;
import com.mopkoff.registrar.model.repository.ProxyEntity;
import com.mopkoff.registrar.repository.ProxyRepository;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProxyService {

  private final ProxyRepository repository;
  private final GenericConversionService registrarConversionService;
  private final Random random;

  public List<ProxyConfig> findAll() {
    return repository.findAll().stream()
        .map(p -> registrarConversionService.convert(p, ProxyConfig.class))
        .collect(Collectors.toList());
  }

  public ProxyConfig findRandom() {
    return repository.findAll(pageOfOne()).stream()
        .findFirst()
        .map(p -> registrarConversionService.convert(p, ProxyConfig.class))
        .orElseThrow(() -> new NotFoundException(ProxyConfig.class));
  }

  public ProxyConfig save(ProxyConfig proxyConfig) {
    test(proxyConfig);
    var entity = registrarConversionService.convert(proxyConfig, ProxyEntity.class);
    var saved = repository.save(entity);
    return registrarConversionService.convert(saved, ProxyConfig.class);
  }

  public void test(ProxyConfig proxyConfig) {
    var template = createRestTemplate(proxyConfig);

    var a = template.exchange("https://yandex.ru/", HttpMethod.GET, null, String.class);
    assert a.getStatusCode().is2xxSuccessful();
  }

  private RestTemplate createRestTemplate(ProxyConfig proxyConfig) {

    var username = proxyConfig.getLogin();
    var password = proxyConfig.getPassword();
    var host = proxyConfig.getHost().split(":");
    var proxyUrl = host[0];
    var port = Integer.parseInt(host[1]);

    var credsProvider = new BasicCredentialsProvider();
    credsProvider.setCredentials(
        new AuthScope(proxyUrl, port),
        new UsernamePasswordCredentials(username, password)
    );

    var myProxy = new HttpHost(proxyUrl, port);
    var clientBuilder = HttpClientBuilder.create();

    clientBuilder.setProxy(myProxy).setDefaultCredentialsProvider(credsProvider).disableCookieManagement();

    var httpClient = clientBuilder.build();
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    factory.setHttpClient(httpClient);

    return new RestTemplate(factory);
  }

  private Pageable pageOfOne() {
    var total = repository.count();
    var number = random.nextInt((int) total);
    log.info("Picked {}", number);
    return PageRequest.of(number, 1);
  }
}
