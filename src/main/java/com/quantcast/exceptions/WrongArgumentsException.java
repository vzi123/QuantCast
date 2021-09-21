package com.quantcast.exceptions;

/**
 * The {@code WrongArgumentsException}   Custom exception should be thrown when arguments provided are not valid
 */
public class WrongArgumentsException extends IllegalArgumentException{
  public WrongArgumentsException(String format, Object... args) {
    super(String.format(format, args));
  }
}
