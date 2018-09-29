package com.company.forecast.entity;

import java.util.Arrays;

public class Result {

    private double[] actualResult;
    private double[] predictedResult;

    public Result(double[] actualResult, double[] predictedResult) {

        this.actualResult = Arrays.copyOf(actualResult,actualResult.length);
        this.predictedResult = Arrays.copyOf(predictedResult, predictedResult.length);
    }
    
    public double[] getActualResult() {

        return actualResult;
    }

    public double[] getPredictedResult() {

        return predictedResult;
    }

    public void setActualResult(double[] actualResult) {

        this.actualResult = actualResult;
    }

    public void setPredictedResultResult(double[] predictedResult) {

        this.predictedResult = predictedResult;
    }

}
