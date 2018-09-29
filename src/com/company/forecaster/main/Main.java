package com.company.forecast.main;

import com.company.forecast.entity.Match;
import com.company.forecast.service.Forecaster;
import com.company.forecast.service.FootballForecaster;
import org.neuroph.core.data.DataSet;
import org.neuroph.util.data.norm.MaxMinNormalizer;
import org.neuroph.util.data.norm.Normalizer;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static double min = Double.MAX_VALUE;
    private static double max = 0;

    public static double min(double[] d) {

        double min = d[0];

        for (double a : d) {

            min = Math.min(a,min);
        }

        return min;
    }

    public static double max(double[] d) {

        double max = 0;

        for (double a : d) {

            max = Math.max(a,max);
        }

        return max;
    }

    public static double[] norm(double[] vector) {

        double[] d = new double[vector.length];

        for (int i = 0; i < vector.length; i ++) {

            d[i] = (vector[i] - min) / (max - min);
        }

        return d;
    }

    public static double[] denorm(double[] vector) {

        double[] d = new double[vector.length];

        for (int i = 0; i < vector.length; i ++) {

            d[i] = min + (vector[i]) * (max - min);
        }

        return d;
    }

    public static void find(List<Match> matches) {

        List<Double> doubles = new ArrayList<Double>();

        for (Match match : matches) {

             double[] d = new double[4];
            d[0] = match.getHomeTeamXg();
            d[1] = match.getHostTeamXg();
            d[2] = match.getScoredGoals();
            d[3] = match.getConcededGoals();
            min = Math.min(min(d),min);
            max = Math.max(max(d),max);
        }
    }

    private static class Calendar {

        List<Match> matches;
        List<Date> dates;

        public Calendar(List<Match> matches) {

            this.matches = matches;
            dates = new ArrayList<Date>();
        }

        public List<Date> buildCalendar(Date fromDate) {

            int a = 0;

            for (int i = 0; i < matches.size(); i++) {

                if (matches.get(i).getDate().equals(fromDate)) {

                    a = i;
                    break;
                }
            }

            for (int i = a; i < matches.size() - 1; i++) {

                if (!matches.get(i).getDate().equals(matches.get(i + 1).getDate())) {

                    dates.add(matches.get(i).getDate());
                }
            }

            return dates;
        }
    }

    public static void main(String[] args) throws IOException, ParseException, InterruptedException, SQLException, ClassNotFoundException {


        /*WebParser webParser = new WebParser();
        webParser.parse();*/
       /* MatchDao matchDao = new MatchDao();
        List<Match> matches = matchDao.getMatches(Date.valueOf("2017-06-30"),Date.valueOf("2018-06-30"));
        Calendar calendar = new Calendar(matches);
        List<Date> dates = calendar.buildCalendar(Date.valueOf("2018-01-30"));
        Forecaster forecaster = new Forecaster();
        forecaster.loadMatches(matches);

        for (Date date : dates) {

            System.out.println(date);
            forecaster.forecastTour(date);
            System.out.println();
        }*/
        //new Forecaster().createTrainingSet();
        //new ForecasterR().test();

        Normalizer normalizer = new MaxMinNormalizer();
        DataSet dataSet;
        dataSet = DataSet.load("D:\\EPL_2014_2015");
        dataSet.addAll(DataSet.load("D:\\bundesliga_2014_2015"));
        dataSet.addAll(DataSet.load("D:\\bundesliga_2015_2016"));
        dataSet.addAll(DataSet.load("D:\\bundesliga_2016_2017"));
        dataSet.addAll(DataSet.load("D:\\laliga_2016_2017"));
        dataSet.addAll(DataSet.load("D:\\laliga_2015_2016"));
        dataSet.addAll(DataSet.load("D:\\laliga_2014_2015"));
        dataSet.addAll(DataSet.load("D:\\serieA_2016_2017"));
        dataSet.addAll(DataSet.load("D:\\serieA_2015_2016"));
        dataSet.addAll(DataSet.load("D:\\serieA_2014_2015"));
        dataSet.addAll(DataSet.load("D:\\EPL_2015_2016"));
        dataSet.addAll(DataSet.load("D:\\EPL_2016_2017"));
        dataSet.addAll(DataSet.load("D:\\ligue1_2016_2017"));
        dataSet.addAll(DataSet.load("D:\\ligue1_2015_2016"));
        dataSet.addAll(DataSet.load("D:\\ligue1_2014_2015"));
        dataSet.shuffle();
        normalizer.normalize(dataSet);
        
        Forecaster forecaster = new FootballForecaster();
        forecaster.init();
        //forecaster.train(dataSet);

        DataSet testSet;
        testSet = DataSet.load("D:\\laliga_2017_2018");
        testSet.addAll(DataSet.load("D:\\EPL_2017_2018"));
        testSet.addAll(DataSet.load("D:\\bundesliga_2017_2018"));
        testSet.addAll(DataSet.load("D:\\serieA_2017_2018"));
        testSet.addAll(DataSet.load("D:\\ligue1_2017_2018"));
        normalizer.normalize(testSet);

        forecaster.test(testSet);
    }
}
