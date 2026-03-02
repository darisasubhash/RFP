package com.censusanalyser;

import com.opencsv.exceptions.CsvException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.util.List;

public class StateCensusAnalyser {
    public <T> int loadCSVData(Class<T> csvClass, String filePath) throws CensusException {
        if (!filePath.endsWith(".csv")) {
            throw new CensusException("Incorrect file type",
                    CensusException.ExceptionType.INCORRECT_FILE_TYPE);
        }
        try (Reader reader = new FileReader(filePath)) {
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                    .withType(csvClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withThrowExceptions(true)
                    .build();
            List<T> csvList = csvToBean.parse();
            return csvList.size();
        } catch (FileNotFoundException e) {
            throw new CensusException("File not found",
                    CensusException.ExceptionType.NO_SUCH_FILE);
        } catch (IOException e) {
            throw new CensusException("Delimiter issue",
                    CensusException.ExceptionType.DELIMITER_ISSUE);
        } catch (RuntimeException e) {
            Throwable cause = e;
            while (cause.getCause() != null) {
                cause = cause.getCause();
            }
            if (cause instanceof com.opencsv.exceptions.CsvRequiredFieldEmptyException) {
                throw new CensusException("Incorrect header",
                        CensusException.ExceptionType.INCORRECT_HEADER);
            }
            if (cause instanceof com.opencsv.exceptions.CsvDataTypeMismatchException
                    || cause instanceof NumberFormatException) {
                throw new CensusException("Unable to parse data",
                        CensusException.ExceptionType.UNABLE_TO_PARSE);
            }

            throw new CensusException("Unable to parse data",
                    CensusException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}