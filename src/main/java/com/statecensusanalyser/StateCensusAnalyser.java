package com.statecensusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.io.IOException;

public class StateCensusAnalyser {
    public int loadData(String path) throws CensusAnalyserException {
        if (path.contains(".csv")) {
            int numberOfEntries = 0;
            try {
                Reader reader = Files.newBufferedReader(Paths.get(path));
                CsvToBean<CSVStateCensusAnalyser> csvToBean = new CsvToBeanBuilder(reader).
                        withType(CSVStateCensusAnalyser.class).
                        withIgnoreLeadingWhiteSpace(true).
                        build();
                Iterator<CSVStateCensusAnalyser> csvStateCensusAnalyserIterator = csvToBean.iterator();

                while (csvStateCensusAnalyserIterator.hasNext()) {
                    CSVStateCensusAnalyser censusAnalyser = csvStateCensusAnalyserIterator.next();
                    System.out.println("State : " + censusAnalyser.getState());
                    System.out.println("Population : " + censusAnalyser.getPopulation());
                    System.out.println("AreaInSqKm : " + censusAnalyser.getAreaInSqKm());
                    System.out.println("DensityPerSqKm : " + censusAnalyser.getDensityPerSqKm());
                    System.out.println("\n=====================\n");
                    numberOfEntries++;
                }
            } catch (IOException e) {
                throw new CensusAnalyserException("Invalid File path", CensusAnalyserException.ExceptionType.WRONG_FILE);
            }
            return numberOfEntries;
        } else {
            throw new CensusAnalyserException("Invalid File Type", CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
        }
    }
}