/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fazenda;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author felipe
 */
public class Save {
    
    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    private final ArrayList<Animal> lista;
    private ArrayList<FinanceiroGado> listaG;
    private ArrayList<FinanceiroSeringueira> listaS;
    private ArrayList<Estoque> listaE;
    private MergeF merge;
    
    public Save(){
        this.lista = new ArrayList<>();
        carregar(lista);
        
        this.listaG = new ArrayList<>();
        this.listaS = new ArrayList<>();
        this.listaE = new ArrayList<>();
        this.merge = new MergeF();
        carregaFinanceiro();
        
    }
    
    public void salvaFinanceiro(ArrayList<FinanceiroGado> listag, ArrayList<FinanceiroSeringueira> listas, ArrayList<Estoque> listae){
        //abrir output
        outputB();
        //salvar lista
        escreveB(listag, listas, listae);
        //fechar output
        fechaArquivo();
    }
    
    public void carregaFinanceiro(){
        //abrir input
        inputB();
        //copiar lista
        copiaB();
        //fechar input
        fechaInput();
    }
    
    public void outputB(){
        try{
            output = new ObjectOutputStream(Files.newOutputStream(Paths.get("Financeiro.txt")));
        }
        catch(IOException ioException)
        {
            System.err.println("erro abrindo programa");
            System.exit(1);
        }
    }
    
    public void inputB(){
     try{
            input = new ObjectInputStream(Files.newInputStream(Paths.get("Financeiro.txt")));
        }
        catch(IOException ioException)
        {
            System.err.println("erro abrindo arquivo leitura");
            outputB();
            fechaArquivo();
            //System.exit(1);
        }   
    }
    public void copiaB(){
        try{
            this.merge = (MergeF) input.readObject();
        }
        catch(EOFException endOfFile){
            System.out.println("fim do arquivo");
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(IOException ioException){
            System.err.println("erro lendo arquivo");
        } 
        
        this.listaG = this.merge.getListaG();
        this.listaS = this.merge.getListaS();
        this.listaE = this.merge.getListaE();
    }
    public void escreveB(ArrayList<FinanceiroGado> listag, ArrayList<FinanceiroSeringueira> listas, ArrayList<Estoque> listae){
        this.merge.setListaG(listag);
        this.merge.setListaS(listas);
        this.merge.setListaE(listae);
        try{
            output.writeObject(this.merge);
        }
        catch(IOException ioException){
                System.err.println("erro escrevendo");
            }
    }
    
    
    public ArrayList<Animal> search(String numero, String sexo, double idadeMin, double idadeMax,
            String pesoMin, String pesoMax, String status, String Tipo, String vacina, String prenhez, String raca, String lote){
        int num;
                
          
        
        if(numero.equals("")){
            
            num = 0;
        }else{
            num = Integer.parseInt(numero);
        }
        double pMax;
        if("".equals(pesoMax)){
            pMax = 0;
        }else{
            pMax = Double.parseDouble(pesoMax);
        }
        double pMin;
        if("".equals(pesoMin)){
            pMin = 0;
        }else{
            pMin = Double.parseDouble(pesoMin);
        }
        int statusI;
        switch (status) {
            case "Ativo":
                statusI = 1;
                break;
            case "Descarte":
                statusI = 2;
                break;
            case "Óbito":
                statusI = 4;
                break;
            case "Venda":
                statusI = 3;
                break;
            case "Selecionar":
                statusI = 0;
                break;
            default:
                statusI = 0;
                break;
        }
        double age;
        boolean vacinado = false;
        
        
        
        ArrayList<Animal> listaAux = new ArrayList<>();
        Animal aux;
        
        
        
        
        
        for(int i = 0; i < lista.size(); i++){
            aux = lista.get(i);
            age = aux.getIdade();
            if(aux.getBrinco() == num || num == 0){
                
                if(aux.getSexo().equals(sexo) || sexo.equals("Selecionar")){

                    if(aux.getStatus() == statusI || statusI == 0){
                        
                        //se quebrar é isso aqui
                        
                            double p = aux.getPesagem().get(aux.getPesagem().size() - 1).getPeso();

                        
                        
                        if(p >= pMin && (p <= pMax || pMax == 0)){


                            if(age >= idadeMin && (age <= idadeMax||idadeMax==0)){
                                                        

                                if(aux.getTipo().equals(Tipo)||Tipo.equals("Selecionar")){
                                    
                                                                


                                        if(vacina.equals("Selecionar")){
                                            vacinado = true;
                                        }else{
                                            ArrayList<Vacina> vacinar = aux.getVacinas();
                                            for(int j = 0; j < vacinar.size(); j++){
                                                if(vacinar.get(j).nome.equals(vacina)){
                                                    vacinado = true;
                                                }
                                            }
                                        }
                                        if(vacinado == true){
                                            if(aux.getRaca().equals(raca) || "Selecionar".equals(raca)){
                                                if(aux.getLote().equals(lote) || lote.equals("Selecionar")){
                                                    
                                                    
                                                    listaAux.add(lista.get(i));
                                                }
                                            }

                                        }
                                    
                                }
                             }
                        }
                    }
                }
            {
                
                }
            }
        }
        return listaAux;
    }
    
    
    private void carregar(ArrayList<Animal> lista){
        abreInput();
        copia(lista);
        fechaInput();
    }
    
    public void salvar(ArrayList<Animal> lista){
        
        abreArquivo();
        escreve(lista);
        fechaArquivo();
        
        
    }
    
    public void abreArquivo(){
        try{
            output = new ObjectOutputStream(Files.newOutputStream(Paths.get("Gado.txt")));
        }
        catch(IOException ioException)
        {
            System.err.println("erro abrindo programa");
            System.exit(1);
        }
    }
    
    
    
    public void escreve(ArrayList<Animal> lista){
        int n = lista.size();
        
        
        for(int i = 0; i < n; i++){
            try{
            output.writeObject(lista.get(i));
            
            }catch(IOException ioException){
                System.err.println("erro escrevendo");
            }
        }
        
        
    }
    
    public void fechaArquivo(){
        try{
            if(output != null){
            output.close();
            }
        }
        catch(IOException ioException){
            System.err.println("erro fechando arquivo");
        }
    }
    
    public void abreInput(){
        try{
            input = new ObjectInputStream(Files.newInputStream(Paths.get("Gado.txt")));
        }
        catch(IOException ioException)
        {
            System.err.println("erro abrindo arquivo leitura");
            abreArquivo();
            fechaArquivo();
            //System.exit(1);
        }
    }
    
    public void copia(ArrayList<Animal> lista){
        try{
            while(true){
                
                lista.add((Animal) input.readObject());
                //System.out.println("add");
            }
        }
        catch(EOFException endOfFile){
            System.out.println("fim do arquivo");
        }
        catch(ClassNotFoundException classNotFound){
            System.err.println("classe nao encontrada ou incorreta");
        }
        catch(IOException ioException){
            System.err.println("erro lendo arquivo");
        }
    }
    
    public void fechaInput(){
        try{
            if(input != null){
                input.close();
            }
        }
        catch(IOException ioException){
            System.err.println("erro fechando arquivo");
            System.exit(1);
        }
    }
    
    public ArrayList<Animal> getLista(){
        ArrayList<Animal> listaAux = new ArrayList<>();
        
        for(int i = 0; i < lista.size(); i++){
            listaAux.add(lista.get(i));
        }
        
        return listaAux;
    }
    
    public ArrayList<FinanceiroGado> getListaG(){
        ArrayList<FinanceiroGado> listaAux = new ArrayList<>();
        
        for(int i = 0; i < listaG.size(); i++){
            listaAux.add(listaG.get(i));
        }
        
        return listaAux;
    }
    public ArrayList<Estoque> getListaE(){
        ArrayList<Estoque> listaAux = new ArrayList<>();
        
        for(int i = 0; i < listaE.size(); i++){
            listaAux.add(listaE.get(i));
        }
        
        return listaAux;
    }
    
    public ArrayList<FinanceiroSeringueira> getListaF(){
        ArrayList<FinanceiroSeringueira> listaAux = new ArrayList<>();
        
        for(int i = 0; i < listaS.size(); i++){
            listaAux.add(listaS.get(i));
        }
        
        return listaAux;
    }
    
    public void addAnimal(Animal aux){
        this.lista.add(aux);
    }
    public void addFG(FinanceiroGado aux){
        this.listaG.add(aux);
    }
    public void addFS(FinanceiroSeringueira aux){
        this.listaS.add(aux);
    }
    public void addE(Estoque aux){
        this.listaE.add(aux);
    }
    public void RemoverAnimal(Animal aux){
        
        this.lista.remove(aux);
        
        this.salvar(lista);
    }
    
    public void removeFG(FinanceiroGado aux){
        this.listaG.remove(aux);
        this.salvaFinanceiro(listaG, listaS, listaE);
    }
    public void removeFS(FinanceiroSeringueira aux){
        this.listaS.remove(aux);
        this.salvaFinanceiro(listaG, listaS, listaE);
    }
    public void removeE(Estoque aux){
        this.listaE.remove(aux);
        this.salvaFinanceiro(listaG, listaS, listaE);
    }
    
}
