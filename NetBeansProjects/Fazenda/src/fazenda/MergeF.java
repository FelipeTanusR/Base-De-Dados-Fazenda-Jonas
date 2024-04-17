/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fazenda;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ivan
 */
public class MergeF implements Serializable {
    private ArrayList<FinanceiroGado> listaG;
    private ArrayList<FinanceiroSeringueira> listaS;
    private ArrayList<Estoque> listaE;

    public MergeF() {
        this.listaG = new ArrayList<>();
        this.listaS = new ArrayList<>();
        this.listaE = new ArrayList<>();
    }

    public ArrayList<FinanceiroGado> getListaG() {
        return listaG;
    }

    public void setListaG(ArrayList<FinanceiroGado> listaG) {
        this.listaG = listaG;
    }

    public ArrayList<FinanceiroSeringueira> getListaS() {
        return listaS;
    }

    public void setListaS(ArrayList<FinanceiroSeringueira> listaS) {
        this.listaS = listaS;
    }
    
    public ArrayList<Estoque> getListaE() {
        return listaE;
    }

    public void setListaE(ArrayList<Estoque> listaE) {
        this.listaE = listaE;
    }
    
    
}
