package com.statecensusanalyser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StateCensusAnalyserTest {
    StateCensusAnalyser stateCensusAnalyser;
    private String path = "C:\\Users\\Surendra\\IdeaProjects\\StateCensusAnalyser\\src\\main\\resources\\IndiaStateCensusData.csv";

    @Before
    public void set() {
        stateCensusAnalyser = new StateCensusAnalyser();
    }

    @Test
    public void givenDataWhenLoadedShouldReturnNumberOfRecords() {
        int size = stateCensusAnalyser.loadData(path);
        Assert.assertEquals(29, size);
    }
}
