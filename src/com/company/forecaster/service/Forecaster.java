package com.company.forecast.service;

import org.neuroph.core.data.DataSet;

public interface Forecaster {

    void init();
    
    void train(DataSet trainingSet);

    void test(DataSet testSet);
}
