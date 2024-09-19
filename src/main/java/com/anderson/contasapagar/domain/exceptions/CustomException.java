package com.anderson.contasapagar.domain.exceptions;

import lombok.Getter;

public class CustomException extends RuntimeException {
  @Getter
  private final int status;
  private final String message;

  public CustomException(int status, String message) {
    super(message);
    this.status = status;
    this.message = message;
  }

    public String getErrorMessage() {
    return message;
  }

}

