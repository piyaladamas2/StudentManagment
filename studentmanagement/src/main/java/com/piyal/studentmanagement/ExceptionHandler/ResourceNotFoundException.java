package com.piyal.studentmanagement.ExceptionHandler;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
