/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unitconvalph;

/**
 *
 * @author USER
 */
public class Unit {
    private final String name;
    private final double conversionRate;
    
    public Unit(String n, double cv){
        this.name = n;
        this.conversionRate = cv;
    }
    
    public Unit(Unit u){
        this.name = u.name;
        this.conversionRate = u.conversionRate;
    }
    
    public String getName() {
        return name;
    }

    public double getConversionRate() {
        return conversionRate;
    }

}
