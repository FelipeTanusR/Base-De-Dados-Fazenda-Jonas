/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fazenda;

import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Peso implements Serializable{
    
    
    private Date data;
    private double peso;
    private double gmd;
    private double diff;
    
    
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getPeso() {
        
        //peso = round(peso, 2);
        
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getGmd() {
        //gmd = round(gmd, 2);
        return gmd;
    }

    public void setGmd(double gmd) {
        this.gmd = gmd;
    }

    public double getDiff() {
        //diff = round(diff, 2);
        return diff;
    }

    public void setDiff(double diff) {
        this.diff = diff;
    }
    
    
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    
}
