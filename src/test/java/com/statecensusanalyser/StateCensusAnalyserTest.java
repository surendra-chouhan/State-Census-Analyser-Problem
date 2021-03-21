package com.statecensusanalyser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StateCensusAnalyserTest {
    StateCensusAnalyser stateCensusAnalyser;
    private String correctPath = "C:\\Users\\Surendra\\IdeaProjects\\StateCensusAnalyser\\src\\main\\resources\\IndiaStateCensusData.csv";
    private String wrongPath = "C:\\Users\\Surendra\\IdeaProjects\\StateCensusAnalyser\\src\\main\\resources\\IndiaStateData";

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
            throw new CensusAnalyserException("Wrong File Path");
        }
    }
}
