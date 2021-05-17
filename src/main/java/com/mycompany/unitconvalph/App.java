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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */


public class App extends JFrame implements ActionListener{
    
     Unit METER = new Unit("Meter", 1); 
     Unit FEET = new Unit("Feet", 0.3048); 
     
     List<Unit> unitList = new ArrayList<Unit>();
    
    
    JTextField textField = new JTextField(10); // pole tekstowe
    
    
     List<String> unitNameList = new ArrayList<>();
    
    JComboBox[] unitbox = new JComboBox[2]; //komponent ktory pozwala wybrac wprowadzana jednostke
    
    JLabel[] text = new JLabel[2];
    
    JButton button = new JButton("Convert");
    
    
    
    void fillUnitList(List<Unit> unitList){
        unitList.add(new Unit("Meter", 1));
        unitList.add(new Unit("Feet", 0.3048));
        unitList.add(new Unit("Kilometer", 1000));
    }
    
    void fillUnitNameList(List<Unit> unitList, List<String> unitNameList){
        
        for(int i=0; i<unitList.size(); i++){
            unitNameList.add(unitList.get(i).getName());
            
        }
    }
    
    
    @Override //napisanie z ActionListener
    public void actionPerformed(ActionEvent e){ //listener zawierajacy kod który wykona sie przy nacisnieciu przycisku
        
        if(textField.getText().isEmpty()){ //sprawdza czy pole do wpisywania wartosci jest puste
            JOptionPane.showMessageDialog(null, "Insert value to convert");//jesli tak to wyswietla komunikat
        }else{                                                                      //jesli nie, rozpoczyna rzutowanie i konwersje
            double number =Double.parseDouble(textField.getText());    
            String convrt_from = (String)unitbox[0].getSelectedItem();
            String convrt_to = (String)unitbox[1].getSelectedItem();
            convert(number, convrt_from, convrt_to);
        }
        
        
    }
    

    App(){
        
        fillUnitList(unitList);
        
        fillUnitNameList(unitList, unitNameList);
        
        SpringLayout layout = new SpringLayout(); //utworzenie layoutu
        this.setLayout(layout); //dodanie layoutu do okna aplikacji
        
        this.add(text[0]= new JLabel("Choose unit and insert value:"));// dodanie napisu
        layout.putConstraint(SpringLayout.WEST, text[0], 80, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, text[0], 80, SpringLayout.NORTH, this);
        
        
        this.add(textField); //dodanie pola tekstowego
        layout.putConstraint(SpringLayout.WEST, textField, 80, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, textField, 100, SpringLayout.NORTH, this);
        
        this.add(unitbox[0]=new JComboBox(unitNameList.toArray())); // inicjalizacja komponentu w którym możliwe jest wybranie jednostki
        layout.putConstraint(SpringLayout.WEST, unitbox[0], 20, SpringLayout.EAST, textField );
        layout.putConstraint(SpringLayout.NORTH, unitbox[0], 100, SpringLayout.NORTH, this);
        
        this.add(text[1]= new JLabel("Choose unit you want to convert:"));
        layout.putConstraint(SpringLayout.WEST, text[1], 60, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, text[1], 15, SpringLayout.SOUTH, textField);
        
        this.add(unitbox[1]=new JComboBox(unitNameList.toArray()));
        layout.putConstraint(SpringLayout.WEST, unitbox[1], 130, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, unitbox[1], 10, SpringLayout.SOUTH, text[1]);
        
        this.add(button);
        layout.putConstraint(SpringLayout.WEST, button, 130, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, button, 10, SpringLayout.SOUTH, unitbox[1]);
        
        button.addActionListener(this);
        
        
        
        this.setSize(400,400);
        this.setVisible(true);
        this.setTitle("Unit converter!");
        
    }
    
    private void convert(double value, String convrt_from, String convrt_to){
        double result;
        
        Unit unitConvertFrom = new Unit(checkUnitType(convrt_from, unitList));
        Unit unitConvertTo = new Unit(checkUnitType(convrt_to, unitList));
    
        if(!checkifsame(unitConvertFrom, unitConvertTo)){
            result = BigDecimal.valueOf( (value*unitConvertFrom.getConversionRate())/unitConvertTo.getConversionRate())
                    .setScale(4, RoundingMode.HALF_UP).doubleValue();
            
            JOptionPane.showMessageDialog(null, value+" "+unitConvertFrom.getName()+ " = "+ result+" "+ unitConvertTo.getName());
        }
        
        

    }
    
    private Unit checkUnitType(String s, List<Unit> unitList){
        
        for(int i =0; i<unitList.size(); i++ ){
            
            if(s.equals(unitList.get(i).getName())){
                  return unitList.get(i) ;
            }
        }
        
        
        return null;
            
    }
    
    private boolean checkifsame(Unit convrt_from, Unit convrt_to){ 
        if(convrt_from.getName().equals(convrt_to.getName())){
           JOptionPane.showMessageDialog(null, "Conversion seems unnessesary");
           return true; 
        }
        return false;
    }
    
    
    public static void main(String[] args){
        App app = new App();

        
        
    }
}
