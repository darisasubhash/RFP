package com.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCode {
    @CsvBindByName(column = "State Name")
    public String stateName;
    @CsvBindByName(column = "TIN")
    public int tin;
    @CsvBindByName(column = "StateCode")
    public String stateCode;
}