package com.censusanalyser;

public class CensusException extends Exception {
  public enum ExceptionType {
    NO_SUCH_FILE,
    INCORRECT_FILE_TYPE,
    INCORRECT_DELIMITER,
    INCORRECT_HEADER
  }
  public ExceptionType type;
    public CensusException(String message , ExceptionType type) {
        super(message);
        this.type = type;
    }
}
