package com.stocklookup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SuggestionCreationException extends RuntimeException {
  public SuggestionCreationException(String error) {
    super(error);
  }
}
