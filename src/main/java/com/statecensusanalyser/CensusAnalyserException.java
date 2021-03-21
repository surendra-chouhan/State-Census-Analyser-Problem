package com.statecensusanalyser;

public class CensusAnalyserException extends Exception{
    public enum ExceptionType {
        WRONG_FILE,
        WRONG_FILE_TYPE;
    }

    public ExceptionType type;

    public CensusAnalyserException(ExceptionType wrongFile, ExceptionType type){}

    public CensusAnalyserException(String s, ExceptionType type){
        super(s);
        this.type = type;
    }
}
