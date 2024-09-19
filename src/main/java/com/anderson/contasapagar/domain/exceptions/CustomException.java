package com.anderson.contasapagar.domain.exceptions;

import lombok.Getter;

public class CustomException extends RuntimeException {
  @Getter
  private final int status;
  private final Object message;

  public CustomException(int status, Object message) {
    super(message.toString());
    this.status = status;
    this.message = message;
  }

    public Object getErrorMessage() {
    return message;
  }

}

