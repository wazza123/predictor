package com.company.forecast.service;

import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;

public class NeuralNetworkClassifier implements Classifier {

    private MultiLayerPerceptron neuralNetwork;
    private MomentumBackpropagation momentumBackpropagation;
    private final String NEURAL_NETWORK_FILE_PATH = "data\\neuron";

    private void createNetwork() {

        neuralNetwork = new MultiLayerPerceptron(18,70,3);
        momentumBackpropagation = new MomentumBackpropagation();
        momentumBackpropagation = new MomentumBackpropagation();
        momentumBackpropagation.setMomentum(0.7);
        momentumBackpropagation.setLearningRate(0.01);
        momentumBackpropagation.setMaxError(0.25);
        neuralNetwork.setLearningRule(momentumBackpropagation);
    }

    @Override
    public void init() {

        neuralNetwork = (MultiLayerPerceptron) MultiLayerPerceptron.createFromFile(NEURAL_NETWORK_FILE_PATH);
    }

    @Override
    public void train(DataSet dataSet) {

        createNetwork();
        neuralNetwork.learn(dataSet);
        neuralNetwork.save(NEURAL_NETWORK_FILE_PATH);
    }

    @Override
    public double[] classify(double[] inputVector) {

        neuralNetwork.setInput(inputVector);
        neuralNetwork.calculate();
        return neuralNetwork.getOutput();
    }
}
