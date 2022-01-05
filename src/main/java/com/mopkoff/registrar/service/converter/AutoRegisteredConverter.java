package com.mopkoff.registrar.service.converter;

import static java.lang.String.valueOf;
import static org.springframework.core.convert.TypeDescriptor.collection;
import static org.springframework.core.convert.TypeDescriptor.valueOf;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;

@Slf4j
public abstract class AutoRegisteredConverter<S, T> implements Converter<S, T> {

  private GenericConversionService registrarConversionService;

  protected ConversionService getRegistrarConversionService() {
    return registrarConversionService;
  }

  @Autowired
  public void setRegistrarConversionService(GenericConversionService registrarConversionService) {
    this.registrarConversionService = registrarConversionService;
  }

  @PostConstruct
  protected void register() {
    registrarConversionService.addConverter(this);
    log.info("registered converter {}", this);
  }

  public <A, B, C extends Collection<B>> C convertCollection(
      Collection<A> source,
      Class<A> sourceClass,
      Class<B> targetClass,
      Class<C> targetCollectionClass) {

    if (source == null || source.isEmpty()) {
      if (List.class.isAssignableFrom(targetCollectionClass)) {
        return (C) List.of();
      } else if (Set.class.isAssignableFrom(targetCollectionClass)) {
        return (C) Set.of();
      } else {
        return null;
      }
    }
    var sourceDescriptor = collection(targetCollectionClass, valueOf(sourceClass));
    var targetDescriptor = collection(targetCollectionClass, valueOf(targetClass));
    return (C) getRegistrarConversionService().convert(source, sourceDescriptor, targetDescriptor);
  }
}
