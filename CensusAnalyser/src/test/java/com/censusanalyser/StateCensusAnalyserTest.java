package com.censusanalyser;

import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTest {
    private String getFilePath(String fileName) {
        return "C:/Users/DELL/IdeaProjects/RFP/CensusAnalyser/resources/"+fileName;
    }

    // UC-1
    // TC 1.1 – Correct Census File
    @Test
    public void givenStateCensusCSV_WhenCorrect_ShouldReturnRecordCount() throws CensusException {
        String filePath = "C:\\Users\\DELL\\IdeaProjects\\RFP\\CensusAnalyser\\resources\\IndianStateCensusData.csv";
        StateCensusAnalyser analyser = new StateCensusAnalyser();
        int count = analyser.loadCSVData(CSVStateCensus.class, filePath);
        Assert.assertEquals(10, count);
    }
    // TC 1.2 – Wrong File Path
    @Test
    public void givenWrongFilePath_WhenStateCensus_ShouldThrowException() {
        StateCensusAnalyser analyser = new StateCensusAnalyser();
        try {
            analyser.loadCSVData(CSVStateCensus.class,getFilePath("WrongFile.csv"));
        } catch (CensusException e) {
            Assert.assertEquals(CensusException.ExceptionType.NO_SUCH_FILE,e.type);
        }
    }
    // TC 1.3 – Incorrect File Type
    @Test
    public void givenWrongFileType_WhenStateCensus_ShouldThrowException() {
        StateCensusAnalyser analyser = new StateCensusAnalyser();
        try {
            analyser.loadCSVData(CSVStateCensus.class,getFilePath("IndiaStateCensusData.txt"));
        } catch (CensusException e) {
            Assert.assertEquals(CensusException.ExceptionType.INCORRECT_FILE_TYPE,e.type);
        }
    }
    // TC 1.4 – Incorrect Header
    @Test
    public void givenWrongHeader_WhenStateCensus_ShouldThrowException() {
        StateCensusAnalyser analyser = new StateCensusAnalyser();
        try {
            analyser.loadCSVData(CSVStateCensus.class,getFilePath("WrongHeaderStateCensus.csv"));
        } catch (CensusException e) {
            Assert.assertEquals(CensusException.ExceptionType.INCORRECT_HEADER,e.type);
        }
    }
    // TC 1.5 – Invalid Data (UNABLE_TO_PARSE)
    @Test
    public void givenInvalidData_WhenStateCensus_ShouldThrowException() {
        StateCensusAnalyser analyser = new StateCensusAnalyser();
        try {
            analyser.loadCSVData(CSVStateCensus.class,getFilePath("WrongData.csv"));
        } catch (CensusException e) {
            Assert.assertEquals(CensusException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }
    // UC-2
    // TC 2.1 – Correct State Code CSV
    @Test
    public void givenStateCodeCSV_WhenCorrect_ShouldReturnRecordCount() throws CensusException {
        String filePath = getFilePath("IndiaStateCode.csv");
        StateCensusAnalyser analyser = new StateCensusAnalyser();
        int count = analyser.loadCSVData(CSVStateCode.class, filePath);
        Assert.assertEquals(11, count);
    }
    // TC 2.2 – Wrong File Path
    @Test
    public void givenWrongFilePath_WhenStateCode_ShouldThrowException() {
        StateCensusAnalyser analyser = new StateCensusAnalyser();
        try {
            analyser.loadCSVData(CSVStateCode.class,getFilePath("WrongFile.csv"));
        } catch (CensusException e) {
            Assert.assertEquals(CensusException.ExceptionType.NO_SUCH_FILE,e.type);
        }
    }
    // TC 2.3 – Incorrect File Type
    @Test
    public void givenWrongFileType_WhenStateCode_ShouldThrowException() {
        StateCensusAnalyser analyser = new StateCensusAnalyser();
        try {
            analyser.loadCSVData(CSVStateCode.class,getFilePath("IndiaStateCode.txt"));
        } catch (CensusException e) {
            Assert.assertEquals(CensusException.ExceptionType.INCORRECT_FILE_TYPE,e.type);
        }
    }
    // TC 2.4 – Incorrect Header
    @Test
    public void givenWrongHeader_WhenStateCode_ShouldThrowException() {
        StateCensusAnalyser analyser = new StateCensusAnalyser();
        try {
            analyser.loadCSVData(CSVStateCode.class,getFilePath("WrongStateCodeHeader.csv"));
        } catch (CensusException e) {
            Assert.assertEquals(CensusException.ExceptionType.INCORRECT_HEADER,e.type);
        }
    }
    // TC 2.5 – Invalid Data (UNABLE_TO_PARSE)
    @Test
    public void givenInvalidData_WhenStateCode_ShouldThrowException() {
        StateCensusAnalyser analyser = new StateCensusAnalyser();
        try {
            analyser.loadCSVData(CSVStateCode.class,getFilePath("WrongStateCodeData.csv"));
        } catch (CensusException e) {
            Assert.assertEquals(CensusException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }
}