package com.censusanalyser;

public class CensusAnalyserApplication {
    public static void main(String[] args) {
        System.out.println("Welcome to Indian States Census Analyser");
        StateCensusAnalyser analyser = new StateCensusAnalyser();
        try {
            int count = analyser.loadCSVData(CSVStateCensus.class,
                    "C:/Users/DELL/IdeaProjects/RFP/CensusAnalyser/resources/IndianStateCensusData.csv");
            System.out.println("Records Loaded: " + count);
        } catch (CensusException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}