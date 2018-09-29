package com.company.forecast.service;

import com.company.forecast.entity.Result;

import java.util.List;

public class Statistics {

    private List<Result> results;
    private int correctPredictionsAmount;

    private int findMax(double[] a) {

        int max = 0;

        for (int i = 0; i < a.length; i++) {

            if (a[i] > a[max]) {

                max = i;
            }
        }

        return max;
    }

    private boolean predictionIsCorrect(double[] d, double[] f) {

        return findMax(d) == findMax(f);
    }

    public void load(List<Result> results) {

        this.results = results;
    }

    public int getGamesAmount() {

        return results.size();
    }

    public void calculateStatistics() {

        for (Result result : results) {

            if (predictionIsCorrect(result.getActualResult(),result.getPredictedResult())) {

                correctPredictionsAmount++;
            }
        }
    }

    public double getCorrectPredictionsRatio() {

        return (double) correctPredictionsAmount / (double) results.size() * 100;
    }
}
