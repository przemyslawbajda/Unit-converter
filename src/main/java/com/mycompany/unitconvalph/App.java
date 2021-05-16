/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unitconvalph;


import javax.swing.SpringLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Objects;
/**
 *
 * @author USER
 */


public class App extends JFrame implements ActionListener{
    
     Unit METER = new Unit("Metr", 1); 
     Unit FEET = new Unit("Stopa", 0.3048); 

    
    
    JTextField textField = new JTextField(10); // pole tekstowe
    
    
    String[] units = {"Metr", "Stopa"};
    
    JComboBox[] unitbox = new JComboBox[2]; //komponent ktory pozwala wybrac wprowadzana jednostke
    
    JLabel[] text = new JLabel[2];
    
    JButton button = new JButton("Konwertuj");
    
    
    
    @Override //napisanie z ActionListener
    public void actionPerformed(ActionEvent e){ //listener zawierajacy kod który wykona sie przy nacisnieciu przycisku
        
        if(textField.getText().isEmpty()){ //sprawdza czy pole do wpisywania wartosci jest puste
            JOptionPane.showMessageDialog(null, "Podaj wartość do przekonwertowania");//jesli tak to wyswietla komunikat
        }else{                                                                      //jesli nie, rozpoczyna rzutowanie i konwersje
            double number =Double.parseDouble(textField.getText());    
            String convrt_from = (String)unitbox[0].getSelectedItem();
            String convrt_to = (String)unitbox[1].getSelectedItem();
            convert(number, convrt_from, convrt_to);
        }
        
        
    }
    

    App(){
        
        
        SpringLayout layout = new SpringLayout(); //utworzenie layoutu
        this.setLayout(layout); //dodanie layoutu do okna aplikacji
        
        this.add(text[0]= new JLabel("Wybierz jednostke i wpisz wartosc:"));// dodanie napisu
        layout.putConstraint(SpringLayout.WEST, text[0], 80, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, text[0], 80, SpringLayout.NORTH, this);
        
        
        this.add(textField); //dodanie pola tekstowego
        layout.putConstraint(SpringLayout.WEST, textField, 80, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, textField, 100, SpringLayout.NORTH, this);
        
        this.add(unitbox[0]=new JComboBox(units)); // inicjalizacja komponentu w którym możliwe jest wybranie jednostki
        layout.putConstraint(SpringLayout.WEST, unitbox[0], 20, SpringLayout.EAST, textField );
        layout.putConstraint(SpringLayout.NORTH, unitbox[0], 100, SpringLayout.NORTH, this);
        
        this.add(text[1]= new JLabel("Wybierz jednostke na która konwertujesz"));
        layout.putConstraint(SpringLayout.WEST, text[1], 60, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, text[1], 15, SpringLayout.SOUTH, textField);
        
        this.add(unitbox[1]=new JComboBox(units));
        layout.putConstraint(SpringLayout.WEST, unitbox[1], 130, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, unitbox[1], 10, SpringLayout.SOUTH, text[1]);
        
        this.add(button);
        layout.putConstraint(SpringLayout.WEST, button, 130, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, button, 10, SpringLayout.SOUTH, unitbox[1]);
        
        button.addActionListener(this);
        
        
        
        this.setSize(400,400);
        this.setVisible(true);
        this.setTitle("Konwerter jednostek!");
        
    }
    
    private void convert(double value, String convrt_from, String convrt_to){
        double result;
        
        Unit unitConvertFrom = new Unit(checkUnitType(convrt_from));
        Unit unitConvertTo = new Unit(checkUnitType(convrt_to));
    
        
        result = (value*unitConvertFrom.getConversionRate())/unitConvertTo.getConversionRate();
        

        JOptionPane.showMessageDialog(null, value+" "+unitConvertFrom.getName()+ " to "+ result+" "+ unitConvertTo.getName());

    }
    
    private Unit checkUnitType(String s){
        if(s.equals(METER.getName())){
                    return METER;
        } else if(s.equals(FEET.getName())){
                    return FEET;
        } else{
           return null; 
        }
        
        
            
    }
    
    public static boolean checkifsame(String convrt_from, String convrt_to){ //funkcja sprawdza czy nie ma nastąpić konwersja z tej samej jendostki
        if(Objects.equals(convrt_from, convrt_to)){
           JOptionPane.showMessageDialog(null, "Konwersja zdaje się byc niepotrzebna");
           return true; 
        }
        return false;
    }
    
    
    public static void main(String[] args){
        App app = new App();
        
        
    }
}
