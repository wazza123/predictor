package com.company.forecast.service;

import com.company.forecast.entity.Result;
import org.neuroph.core.data.DataSet;

import java.util.ArrayList;
import java.util.List;

public class FootballForecaster implements Forecaster {

    private Classifier classifier = new NeuralNetworkClassifier();

    @Override
    public void init() {

        classifier.init();
    }

    @Override
    public void train(DataSet trainingSet) {

        classifier.train(trainingSet);
    }

    @Override
    public void test(DataSet testSet) {

        List<Result> results = new ArrayList<Result>();

        for (int i = 0; i < testSet.size(); i++) {

            System.out.println(testSet.get(i).getLabel());
            double[] out = classifier.classify(testSet.get(i).getInput());
            System.out.println(out[0] + " " + out[1] + " " + out[2]);
            results.add(new Result(testSet.get(i).getDesiredOutput(),out));
        }

        Statistics statistics = new Statistics();
        statistics.load(results);
        statistics.calculateStatistics();
        System.out.println("correct predictions: " + statistics.getCorrectPredictionsRatio() + " %");
    }
}
