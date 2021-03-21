package com.statecensusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.io.IOException;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
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
                Iterable<CSVStateCensusAnalyser> iterator = () -> csvStateCensusAnalyserIterator;
                return (int) StreamSupport.stream(iterator.spliterator(), false).count();
            } catch (IOException e) {
                throw new CensusAnalyserException("Invalid File path", CensusAnalyserException.ExceptionType.WRONG_FILE);
            } catch (RuntimeException e) {
                if (e.getMessage().contains("CSV header"))
                    throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.WRONG_HEADER);

                throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.WRONG_FILE_DELIMITER);
            }
        } else {
            throw new CensusAnalyserException("Invalid File Type", CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
        }
    }

    public int loadStateCodeData(String path) throws CensusAnalyserException {
        if (path.contains(".csv")) {
            try {
                Reader reader = Files.newBufferedReader(Paths.get(path));
                CsvToBean<CSVStateCodeAnalyser> csvToBean = new CsvToBeanBuilder(reader).
                        withType(CSVStateCodeAnalyser.class).
                        withIgnoreLeadingWhiteSpace(true).
                        build();

                Iterator<CSVStateCodeAnalyser> csvStateCodeAnalyserIterator = csvToBean.iterator();
                Iterable<CSVStateCodeAnalyser> iterator=() -> csvStateCodeAnalyserIterator;
                return (int) StreamSupport.stream(iterator.spliterator(),false).count();
            }
            catch (IOException e) {
                throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.WRONG_FILE);
            }
            catch(RuntimeException e) {
                throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.WRONG_FILE_DELIMITER);
            }
        }
        else {
            throw new CensusAnalyserException("Wrong File Type", CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
        }
    }
}