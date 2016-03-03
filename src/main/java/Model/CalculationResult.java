/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author SantiagoAvila
 */
public class CalculationResult {
    
    static final double XK = 386.0;
    private double regressionB1;
    private double xAverage;
    private double yAverage;
    private double correlationR;

    public CalculationResult() {
        
    }

    public double getRegressionB1() {
        return regressionB1;
    }

    public void setRegressionB1(double regressionB1) {
        this.regressionB1 = regressionB1;
    }

    public double getxAverage() {
        return xAverage;
    }

    public void setxAverage(double xAverage) {
        this.xAverage = xAverage;
    }

    public double getyAverage() {
        return yAverage;
    }

    public void setyAverage(double yAverage) {
        this.yAverage = yAverage;
    }
    
    public double getCorrelationR() {
        return correlationR;
    }

    public void setCorrelationR(double correlationR) {
        this.correlationR = correlationR;
    }
    
    public double getCorrelationSquareR() {
        return this.correlationR * this.correlationR;
    }
    
    public double getRegressionB0() {
        return this.yAverage - (this.xAverage * this.regressionB1);
    }
    
    public double getYK() {
        return this.getRegressionB0() + (this.regressionB1 * XK);
    }
}
