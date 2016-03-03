/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CalculationResult;
import Model.LoadData;
import Model.StatisticCalculation;
import Model.ValuePair;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SantiagoAvila
 */
public class Controller {
    
    public List<ValuePair> loadDataList(String fileName) {
        return LoadData.loadDataFromFile(fileName);
    }
    
    public CalculationResult calculateValues(List<ValuePair> values) {
        
        List<Double> xElements = new ArrayList<Double>();
        List<Double> yElements = new ArrayList<Double>();
        for(ValuePair value : values) {
            xElements.add(value.getX());
            yElements.add(value.getY());
        }
        
        CalculationResult result = new CalculationResult();
        result.setxAverage(StatisticCalculation.calculateMean(xElements));
        result.setyAverage(StatisticCalculation.calculateMean(yElements));
        result.setRegressionB1(StatisticCalculation.calculateRegressionB1(values));
        result.setCorrelationR(StatisticCalculation.calculateCorrelationCoefficient(values));
        return result;
    }
    
    
}
