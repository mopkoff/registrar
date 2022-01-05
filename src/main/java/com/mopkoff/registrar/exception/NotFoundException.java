package com.mopkoff.registrar.exception;

public class NotFoundException extends RuntimeException {

  static final String FORMAT = "%s was not found";

  public NotFoundException(Class<?> clazz) {
    super(String.format(FORMAT, clazz.getSimpleName()));
  }
}
