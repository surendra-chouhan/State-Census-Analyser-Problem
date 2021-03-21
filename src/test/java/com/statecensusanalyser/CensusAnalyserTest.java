package com.statecensusanalyser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CensusAnalyserTest {
    CensusAnalyser censusAnalyser;
    private String correctPath = "C:\\Users\\Surendra\\IdeaProjects\\StateCensusAnalyser\\src\\main\\resources\\IndiaStateCensusData.csv";
    private String wrongPath = "C:\\Users\\Surendra\\IdeaProjects\\StateCensusAnalyser\\src\\main\\resources\\IndiaStateData.csv";
    private String wrongFiletype = "C:\\Users\\Surendra\\IdeaProjects\\StateCensusAnalyser\\src\\main\\resources\\IndiaStateCensusData.txt";
    private String stateCodeFilePath = "C:\\Users\\Surendra\\IdeaProjects\\StateCensusAnalyser\\src\\main\\resources\\IndiaStateCode.csv";

    @Before
    public void set() {
        censusAnalyser = new CensusAnalyser();
    }

    @Test
    public void givenDataWhenLoadedShouldReturnNumberOfRecords() throws CensusAnalyserException {
        Assert.assertEquals(29, censusAnalyser.loadData(correctPath));
    }

    @Test
    public void givenDataWhenFromWrongPathShouldReturnCustomExceptions() throws CensusAnalyserException{
        try{
            censusAnalyser.loadData(wrongPath);
        }
        catch(CensusAnalyserException e){
            System.out.println(e.type);
            Assert.assertEquals(e.type, CensusAnalyserException.ExceptionType.WRONG_FILE);
        }
    }

    @Test
    public void givenWrongFileTypeShouldThrowCustomException() throws CensusAnalyserException {
        try{
            censusAnalyser.loadData(wrongFiletype);
        }
        catch(CensusAnalyserException e) {
            System.out.println(e.type);
            Assert.assertEquals(e.type, CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
        }
    }

    @Test
    public void givenWrongDelimiterShouldThrowCustomException() {
        try{
            censusAnalyser.loadData(correctPath);
        }
        catch (CensusAnalyserException e) {
            System.out.println(e.type);
            Assert.assertEquals(e.type, CensusAnalyserException.ExceptionType.WRONG_FILE_DELIMITER);
        }
    }

    @Test
    public void givenWrongHeaderShouldThrowCustomException() {
        try{
            censusAnalyser.loadData(stateCodeFilePath);
        }
        catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals(e.type, CensusAnalyserException.ExceptionType.WRONG_HEADER);
        }
    }

    @Test
    public void givenIndianStateCodeCSVFilePathShouldReturnCorrectNumberOfRecords() throws CensusAnalyserException {
        int numOfEntries = censusAnalyser.loadStateCodeData(stateCodeFilePath);
        Assert.assertEquals(37, numOfEntries);
    }
}
