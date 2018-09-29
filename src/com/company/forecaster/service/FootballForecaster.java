package com.company.forecast.service;

import com.company.forecast.entity.Result;
import org.neuroph.core.data.DataSet;

import java.util.ArrayList;
import java.util.List;

public class FootballForecaster implements Forecaster {

    private Classifier classifier = new NeuralNetworkClassifier();
    
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

        double c = 0;
        double s = 0;
        List<Result> results = new ArrayList<Result>();

        for (int i = 0; i < testSet.size(); i++) {

            System.out.println(testSet.get(i).getLabel());
            double[] out = classifier.classify(testSet.get(i).getInput());
            System.out.println(out[0] + " " + out[1] + " " + out[2]);
            c++;
            results.add(new Result(testSet.get(i).getDesiredOutput(),out));

            if (result(testSet.get(i).getDesiredOutput(),out)) {

                s++;
                System.out.println(s + " out of " + c);
            }
        }

        System.out.println(s + " out of " + c + " (" + s/c*100 + "%)");
        Statistics statistics = new Statistics();
        statistics.load(results);
        statistics.calculateStatistics();
        System.out.println(statistics.getCorrectPredictionsRatio() + " %");

    }
}
