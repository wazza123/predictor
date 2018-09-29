package com.company.forecast.service;

import com.company.forecast.entity.Result;

import java.util.List;

public class Statistics {

    private List<Result> results;
    private int correctPredictionsAmount;

    private int max(double[] a) {

        int max = 0;

        for (int i = 0; i < a.length; i++) {

            if (a[i] > a[max]) {

                max = i;
            }
        }

        return max;
    }

    private boolean result(double[] d, double[] f) {

        return max(d) == max(f);
    }

    public void load(List<Result> results) {

        this.results = results;
    }

    public int getGamesAmount() {

        return results.size();
    }

    public void calculateStatistics() {

        int s = 0;

        for (Result result : results) {

            if (result(result.getActualResult(),result.getPredictedResult())) {

                s++;
            }
        }

        correctPredictionsAmount = s;
    }

    public double getCorrectPredictionsRatio() {

        return (double) correctPredictionsAmount / (double) results.size() * 100;
    }
}
