/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fazenda;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

/**
 *
 * @author ivan
 */
public class Indice {
    
    public Indice(ArrayList<Animal> listax){
        lista = listax;
        tamanhoL = lista.size();
        
    }
    private ArrayList<Animal> lista;
    private final int tamanhoL;
    //ArrayList<Animal> listaAux;
    
    public double taxaPrenhez(int monta){
        int x = 0;
        for(int i = 0; i < tamanhoL - 1; i++){
            if(lista.get(i).getSexo().equals("Fêmea")&&lista.get(i).getPrenha().equals("Prenha")){
                x++;
            }
        }
        return x * 100/ monta;
    }
    
    
    //taxa aborto
    
    public double taxaAborto(int monta){
        int x = 0;
        for(int i = 0; i < tamanhoL - 1; i++){
            if(lista.get(i).getSexo().equals("Fêmea")&&lista.get(i).getPrenha().equals("Aborto")){
                x++;
            }
        }
        return x * 100/ monta;
    }
    
    public double taxaMortalidade(int index){
        Date data = new Date();
        LocalDate data2 = convertToLocalDateViaInstant(data);
        
        switch(index){
            
            //1 mês
            case 1: {
                
                data2.minusMonths(1);
            }
            case 2: {
                data2.minusYears(1);
            }
            case 3:{
                
                data2.minusYears(5);
            }
        }
        
        int x = 0;
        for(int i = 0; i < tamanhoL; i++){
            
            if(convertToLocalDateViaInstant(lista.get(i).getDataSaida()).isBefore(data2))
                {
                    if(lista.get(i).getStatus() == 4){
                    x++;
                    }
            }
        }
        return (x * 100) / tamanhoL;
    }
    
    public double taxaDescarte(int index){
        Date data = new Date();
        LocalDate data2 = convertToLocalDateViaInstant(data);
        
        switch(index){
            
            //1 mês
            case 1: {
                
                data2.minusMonths(1);
            }
            case 2: {
                data2.minusYears(1);
            }
            case 3:{
                
                data2.minusYears(5);
            }
        }
        
        int x = 0;
        for(int i = 0; i < tamanhoL; i++){
            
            if(convertToLocalDateViaInstant(lista.get(i).getDataSaida()).isBefore(data2))
                {
                    if(lista.get(i).getStatus()== 2){
                    x++;
                    }
            }
        }
        return (x * 100) / tamanhoL;
    }
    
    public double taxaDesfrute(int index){
        
        Date data = new Date();
        LocalDate data2 = convertToLocalDateViaInstant(data);
        
        switch(index){
            
            //1 mês
            case 1: {
                
                data2.minusMonths(1);
            }
            case 2: {
                data2.minusYears(1);
            }
            case 3:{
                
                data2.minusYears(5);
            }
        }
        
        int x = 0;
        for(int i = 0; i < tamanhoL; i++){
            if(convertToLocalDateViaInstant(lista.get(i).getDataSaida()).isAfter(data2)){
                    if(lista.get(i).getStatus() == 3){
                    x++;
                }
            }
        }
        return (x * 100) / tamanhoL;
    }
    
    public double taxaNatalidade(int monta){
        int meses = 9;
        int x = 0;
        Date data = new Date();
        LocalDate data2 = convertToLocalDateViaInstant(data);
        data2.minusMonths(meses);
        for(int i = 0; i < tamanhoL ; i++){
            if(convertToLocalDateViaInstant(lista.get(i).getDataNascimento()).isBefore(data2)){
                x++;
            }
        }
        return x * 100 / monta;
    }
    
    public double relacaoDesmame(){
        double somaB = 0;
        int qtdB = 0;
        int qtdM = 0;
        double somaM = 0;
        int brinco;
        ArrayList<Integer> maes = new ArrayList<>();
        
        for(int i = 0; i < tamanhoL; i++){
            if(lista.get(i).getTipo().equals("Bezerro")){
                brinco = lista.get(i).brincoMae;
                
                qtdB++;
                somaB = somaB + lista.get(i).getPesagem().get(lista.get(i).getPesagem().size() - 1).getPeso();
                if(brinco != 0){
                    maes.add(brinco);
                }
            }
        }
        for(int i = 0; i < tamanhoL; i++){
            for(int j = 0; j < maes.size() - 1; j++){
                if(maes.get(j) == lista.get(i).brinco){
                    maes.remove(j);
                    somaM = somaM + lista.get(i).getPesagem().get(lista.get(i).getPesagem().size()-1).getPeso();
                    qtdM++;
                }
            }
        }
        return ((somaB / qtdB) * 100) / (somaM / qtdM);
    }
    
    //media do peso de todos os animais que nasceram entre 7 e 9 meses atrás
    
    public long taxaDesmame(){
        Date data = new Date();
        Date dataux = data;
        Date dataMin;
        Date dataMax;
        System.out.println("bu");
        Date referenceDate = new Date();
        Calendar c = Calendar.getInstance(); 
        c.setTime(referenceDate); 
        c.add(Calendar.MONTH, -7);
        dataMax = c.getTime();
        
        referenceDate = new Date();
        c = Calendar.getInstance(); 
        c.setTime(referenceDate); 
        c.add(Calendar.MONTH, -9);
        dataMin = c.getTime();
        
        long soma = 0;
        int qtd = 0;
        
        for(int i = 0; i < tamanhoL; i++){
            if(lista.get(i).getDataNascimento().after(dataMin) && lista.get(i).getDataNascimento().before(dataMax)){
                    soma += lista.get(i).getPesagem().get(lista.get(i).getPesagem().size() - 1).getPeso();
                    qtd++;
            }
        }
        if(qtd == 0) return 0;
        return soma / qtd;
    }
    
    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
    }
}
