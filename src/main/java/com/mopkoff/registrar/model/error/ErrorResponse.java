package com.mopkoff.registrar.model.error;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

  private int status;
  private String message;
  private String clientMessage;
  private String type;
  private String detailsLink;
  private List<InnerError> errors;
}
