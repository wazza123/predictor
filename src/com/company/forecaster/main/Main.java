package com.company.forecast.main;

import com.company.forecast.service.FootballForecaster;
import com.company.forecast.service.Forecaster;
import org.neuroph.core.data.DataSet;
import org.neuroph.util.data.norm.MaxMinNormalizer;
import org.neuroph.util.data.norm.Normalizer;

public class Main {

    public static void main(String[] args) {

        Normalizer normalizer = new MaxMinNormalizer();
        DataSet dataSet;
        dataSet = DataSet.load("data\\EPL_2014_2015");
        dataSet.addAll(DataSet.load("data\\bundesliga_2014_2015"));
        dataSet.addAll(DataSet.load("data\\bundesliga_2015_2016"));
        dataSet.addAll(DataSet.load("data\\bundesliga_2016_2017"));
        dataSet.addAll(DataSet.load("data\\laliga_2016_2017"));
        dataSet.addAll(DataSet.load("data\\laliga_2015_2016"));
        dataSet.addAll(DataSet.load("data\\laliga_2014_2015"));
        dataSet.addAll(DataSet.load("data\\serieA_2016_2017"));
        dataSet.addAll(DataSet.load("data\\serieA_2015_2016"));
        dataSet.addAll(DataSet.load("data\\serieA_2014_2015"));
        dataSet.addAll(DataSet.load("data\\EPL_2015_2016"));
        dataSet.addAll(DataSet.load("data\\EPL_2016_2017"));
        dataSet.addAll(DataSet.load("data\\ligue1_2016_2017"));
        dataSet.addAll(DataSet.load("data\\ligue1_2015_2016"));
        dataSet.addAll(DataSet.load("data\\ligue1_2014_2015"));
        dataSet.shuffle();
        normalizer.normalize(dataSet);
        
        Forecaster forecaster = new FootballForecaster();
        forecaster.init();
        //forecaster.train(dataSet);

        DataSet testSet;
        testSet = DataSet.load("data\\laliga_2017_2018");
        testSet.addAll(DataSet.load("data\\EPL_2017_2018"));
        testSet.addAll(DataSet.load("data\\bundesliga_2017_2018"));
        testSet.addAll(DataSet.load("data\\serieA_2017_2018"));
        testSet.addAll(DataSet.load("data\\ligue1_2017_2018"));
        normalizer.normalize(testSet);

        forecaster.test(testSet);
    }
}
