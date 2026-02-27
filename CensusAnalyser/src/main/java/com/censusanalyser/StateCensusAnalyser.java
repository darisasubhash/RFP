package com.censusanalyser;

import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws Exception {
        Reader reader = new FileReader(csvFilePath);
        CsvToBean<CSVStateCensus> csvToBean =
                new CsvToBeanBuilder<CSVStateCensus>(reader)
                        .withType(CSVStateCensus.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
        Iterator<CSVStateCensus> iterator = csvToBean.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        return count;
    }
}