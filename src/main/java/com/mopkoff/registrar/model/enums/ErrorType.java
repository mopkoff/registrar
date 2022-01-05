package com.mopkoff.registrar.model.enums;

public enum ErrorType {
  SECURITY("security"),
  REQUEST("request"),
  VALIDATION("validation"),
  CONSTRAINT("constraint"),
  GATEWAY("gateway"),
  INTERNAL("internal");
  final String type;

  ErrorType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
