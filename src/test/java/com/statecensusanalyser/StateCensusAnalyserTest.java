package com.statecensusanalyser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StateCensusAnalyserTest {
    StateCensusAnalyser stateCensusAnalyser;
    private String correctPath = "C:\\Users\\Surendra\\IdeaProjects\\StateCensusAnalyser\\src\\main\\resources\\IndiaStateCensusData.csv";
    private String wrongPath = "C:\\Users\\Surendra\\IdeaProjects\\StateCensusAnalyser\\src\\main\\resources\\IndiaStateData.csv";
    private String wrongFiletype = "C:\\Users\\Surendra\\IdeaProjects\\StateCensusAnalyser\\src\\main\\resources\\IndiaStateCensusData.txt";
    private String stateCodeFilePath = "C:\\Users\\Surendra\\IdeaProjects\\StateCensusAnalyser\\src\\main\\resources\\IndiaStateCode.csv";

    @Before
    public void set() {
        stateCensusAnalyser = new StateCensusAnalyser();
    }

    @Test
    public void givenDataWhenLoadedShouldReturnNumberOfRecords() throws CensusAnalyserException {
        Assert.assertEquals(29, stateCensusAnalyser.loadData(correctPath));
    }

    @Test
    public void givenDataWhenFromWrongPathShouldReturnCustomExceptions() throws CensusAnalyserException{
        try{
            stateCensusAnalyser.loadData(wrongPath);
        }
        catch(CensusAnalyserException e){
            System.out.println(e.type);
            Assert.assertEquals(e.type, CensusAnalyserException.ExceptionType.WRONG_FILE);
        }
    }

    @Test
    public void givenWrongFileTypeShouldThrowCustomException() throws CensusAnalyserException {
        try{
            stateCensusAnalyser.loadData(wrongFiletype);
        }
        catch(CensusAnalyserException e) {
            System.out.println(e.type);
            Assert.assertEquals(e.type, CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
        }
    }

    @Test
    public void givenWrongDelimiterShouldThrowCustomException() {
        try{
            stateCensusAnalyser.loadData(correctPath);
        }
        catch (CensusAnalyserException e) {
            System.out.println(e.type);
            Assert.assertEquals(e.type, CensusAnalyserException.ExceptionType.WRONG_FILE_DELIMITER);
        }
    }

    @Test
    public void givenWrongHeaderShouldThrowCustomException() {
        try{
            stateCensusAnalyser.loadData(stateCodeFilePath);
        }
        catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(e.type, CensusAnalyserException.ExceptionType.WRONG_HEADER);
        }
    }
}
