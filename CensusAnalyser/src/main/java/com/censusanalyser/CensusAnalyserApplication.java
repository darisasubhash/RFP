package com.censusanalyser;

public class CensusAnalyserApplication {
    public static void main(String[] args) {
        System.out.println("Welcome to Indian States Census Analyser");
        StateCensusAnalyser analyser = new StateCensusAnalyser();
        try {
            int count = analyser.loadIndiaCensusData("IndiaStateCensusData.csv");
            System.out.println("Number of Records: " + count);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}