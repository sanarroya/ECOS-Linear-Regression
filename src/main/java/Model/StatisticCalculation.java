/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author SantiagoAvila
 */
public class StatisticCalculation {
    public static double calculateMean(List<Double> dataList) {
        double sum = 0.0;
        for(double element : dataList) {
            sum += element;
        }
        double listSize = (double)dataList.size();
        double mean = sum / listSize;
        return mean;
    }
    
    public static double calculateRegressionB1(List<ValuePair> values) {
        double sumOfValuesProduct = calculateSumOfValuesProduct(values);
        List<Double> xElements = new ArrayList<Double>();
        List<Double> yElements = new ArrayList<Double>();
        for(ValuePair value : values) {
            xElements.add(value.getX());
            yElements.add(value.getY());
        }
        double xAverage = calculateMean(xElements);
        double yAverage = calculateMean(yElements);
        double sumOfXSquarevalues = calculateSumOfSquareValues(xElements);
        
        double numerator = sumOfValuesProduct - ((double) values.size() * xAverage * yAverage);
        double enumerator = sumOfXSquarevalues - ((double) values.size() * Math.pow(xAverage, 2));
        double result = numerator / enumerator;
        return result;
    }
    
    public static double calculateCorrelationCoefficient(List<ValuePair> values) {
        double sumOfValuesProduct = calculateSumOfValuesProduct(values);
        double size = (double) values.size();
        
        sumOfValuesProduct = size * sumOfValuesProduct;
        List<Double> xElements = new ArrayList<Double>();
        List<Double> yElements = new ArrayList<Double>();
        for(ValuePair value : values) {
            xElements.add(value.getX());
            yElements.add(value.getY());
        }
        
        double xSum = calculateSumOfValues(xElements);
        double ySum = calculateSumOfValues(yElements);
        double productOfSums = xSum * ySum;
        double numerator = sumOfValuesProduct - productOfSums;
        
        double sumOfXSquarevalues = calculateSumOfSquareValues(xElements);
        double sumOfYSquarevalues = calculateSumOfSquareValues(yElements);
        
        double enumeratorPart1 = (size * sumOfXSquarevalues) - Math.pow(xSum, 2);
        double enumeratorPart2 = (size * sumOfYSquarevalues) - Math.pow(ySum, 2);
        double enumerator = Math.sqrt(enumeratorPart1 * enumeratorPart2);
        
        double result = numerator / enumerator;
        return result;
    }
    
    private static double calculateSumOfValuesProduct(List<ValuePair> values) {
        double sumOfValuesProduct = 0.0;
        for(ValuePair value : values) {
            sumOfValuesProduct += value.getX() * value.getY();
        }
        return sumOfValuesProduct;
    }
    
    private static double calculateSumOfSquareValues(List<Double> values) {
        double sumOfSquareValues = 0.0;
        for(double value : values) {
            sumOfSquareValues += Math.pow(value, 2);
        }
        return sumOfSquareValues;
    }
    
    private static double calculateSumOfValues(List<Double> values) {
        double sumOfValues = 0.0;
        for(double value : values) {
            sumOfValues += value;
        }
        return sumOfValues;
    }
}
