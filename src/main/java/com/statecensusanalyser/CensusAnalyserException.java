package com.statecensusanalyser;

public class CensusAnalyserException extends Exception{
    public enum ExceptionType {
        WRONG_FILE,
        WRONG_FILE_TYPE,
        WRONG_FILE_DELIMITER;
    }

    public ExceptionType type;

    public CensusAnalyserException(String s, ExceptionType type){
        super(s);
        this.type = type;
    }
}
