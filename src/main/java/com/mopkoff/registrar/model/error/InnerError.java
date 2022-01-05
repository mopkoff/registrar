package com.mopkoff.registrar.model.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InnerError {

  private int code;
  private String message;
  private String clientMessage;
  private String detailsLink;
  private String target;

}
