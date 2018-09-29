package com.company.forecast.service;

import org.neuroph.core.data.DataSet;

public interface Classifier {

    void init();

    void train(DataSet dataSet);

    double[] classify(double[] inputVector);
}
