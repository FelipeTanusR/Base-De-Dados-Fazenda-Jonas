/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fazenda;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.Serializable;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.ArrayList;

//imports teste
import java.time.LocalDate;
import java.time.ZoneId;
//imports teste

public class Animal implements Serializable{
    Date dataNascimento = new Date(0);
    Date dataCcompra = new Date(0);
    Date dataSaida = new Date(0);
    Peso ps;
    
    int brincoMae;
    int brincoPai;
    int brinco;
    int idadeCompra;
    int status;
    int brincoReprodutor;

    
    boolean PrimeiraG;

    
    
    String prenha;
    String origem;
    String tipo;
    String raca;
    String pelagem;
    String lote;
    String statusSaida;
    String causaObito;
    String Obs;
    String Sexo;
    
    double idade;    
    
    //lista pesagem
    private ArrayList<Peso> pesagem;
    //lista vacinas
    private ArrayList<Vacina> vacinas;
    
    public SimpleDateFormat sdf;

    
    
    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getObs() {
        return Obs;
    }

    public void setObs(String Obs) {
        this.Obs = Obs;
    }
    
    
    
    public Animal(){
        this.pesagem = new ArrayList<>();
        this.vacinas = new ArrayList<>();
        this.sdf = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    
    //calculos e funções do peso
    public ArrayList<Peso> getPesagem(){
        return pesagem;
    }
    
    public void inserePesagem(double peso, String data){
        Date Dataux = new Date();
        Peso aux = new Peso();
        try{
            Dataux = sdf.parse(data);
        }catch(ParseException e)
        {
            System.out.println("erro: " + e.getMessage());
        }
        aux.setData(Dataux);
        aux.setPeso(peso);
        pesagem.add(aux);
        if(pesagem.indexOf(aux) == 0){
            aux.setGmd(0);
            aux.setDiff(0);
        }
        else{
            int i = pesagem.indexOf(aux);
            aux.setDiff(peso - pesagem.get(i-1).getPeso());
            aux.setGmd(calcularGMD(Dataux, pesagem.get(i-1).getData(), peso, pesagem.get(i-1).getPeso()));
        }
    }
    
    //calculos e funções de vacinas
    public ArrayList<Vacina> getVacinas(){
        return vacinas;
    }
    
    public void insereVacina(String nome, String validade, String data){
        Vacina aux = new Vacina();
        aux.setNome(nome);
        try{
             aux.setDataVacina(sdf.parse(data));
             aux.setDataVencimento(sdf.parse(data));
        }catch(ParseException e)
        {
            System.out.println("erro: " + e.getMessage());
        }
        vacinas.add(aux);
    }
    
    //função teste
    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
    }
    
    
    public double calculaIdade(){
        Date aux2 = new Date(0);
        Date data;
        double aux;
        if(dataNascimento.compareTo(aux2)!=0){
            data = dataNascimento;
        }
        else{
            data = dataCcompra;
        }
        Date hoje = new Date();
        LocalDate s2 = convertToLocalDateViaInstant(hoje);
        LocalDate s1 = convertToLocalDateViaInstant(data);
        
        
        aux = ((double)ChronoUnit.DAYS.between(s1,s2)) /  30;
        aux = Peso.round(aux,0);
        
        return aux;
    }
    public double calcularGMD(Date data1, Date data2, double pesoA, double pesoF){
        long dias = compararDatas(data1, data2);
        
        return (pesoF - pesoA) / dias;
    }
    public long compararDatas(Date data1, Date data2){
        long dias;
        LocalDate s1 = convertToLocalDateViaInstant(data1);
        LocalDate s2 = convertToLocalDateViaInstant(data2);
        
        dias = ChronoUnit.DAYS.between(s1, s2);
        //System.out.println("dias para calcular o gmd: "+ dias);
        return dias;
    }
    
    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    
    public int getBrincoReprodutor() {
        return brincoReprodutor;
    }

    public void setBrincoReprodutor(int brincoReprodutor) {
        this.brincoReprodutor = brincoReprodutor;
    }
    
    public String getPrenha() {
        return prenha;
    }

    public void setPrenha(String prenha) {
        this.prenha = prenha;
    }

    public Date getDataNascimento() {
        return this.dataNascimento;   
    }

    public void setDataNascimento(String data) {
         if(data.equals("  /  /    ")){
           
            this.dataNascimento = new Date(0);
            
        }else{
            try{
                this.dataNascimento = sdf.parse(data);
            }catch(ParseException e)
            {
                System.out.println("erro: " + e.getMessage());
            }
        }
        
    }

    public Date getDataCcompra() {
        return this.dataCcompra;
    }

    public void setDataCcompra(String data) {
         if(data.equals("  /  /    ")){
            
                this.dataCcompra = new Date(0);
            
        }else{
            try{
                this.dataCcompra = sdf.parse(data);
            }catch(ParseException e)
            {
                System.out.println("erro: " + e.getMessage());
            }
        }
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String data) {
        try{
            this.dataSaida = sdf.parse(data);
        }catch(ParseException e)
        {
            System.out.println("erro: " + e.getMessage());
        }
    }

    public int getBrincoMae() {
        return brincoMae;
    }

    public void setBrincoMae(int brincoMae) {
        this.brincoMae = brincoMae;
    }

    public int getBrincoPai() {
        return brincoPai;
    }

    public void setBrincoPai(int brincoPai) {
        this.brincoPai = brincoPai;
    }

    public int getBrinco() {
        return brinco;
    }

    public void setBrinco(int brinco) {
        this.brinco = brinco;
    }

    public int getIdadeCompra() {
        return idadeCompra;
    }

    public void setIdadeCompra(int idadeCompra) {
        this.idadeCompra = idadeCompra;
    }

    public double getIdade() {
        return idade;
    }

    public void setIdade(double idade) {
        this.idade = idade;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPelagem() {
        return pelagem;
    }

    public void setPelagem(String pelagem) {
        this.pelagem = pelagem;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getStatusSaida() {
        return statusSaida;
    }

    public void setStatusSaida(String statusSaida) {
        this.statusSaida = statusSaida;
    }

    public String getCausaObito() {
        return causaObito;
    }

    public void setCausaObito(String causaObito) {
        this.causaObito = causaObito;
    }
    public boolean isPrimeiraG() {
        return PrimeiraG;
    }

    public void setPrimeiraG(boolean PrimeiraG) {
        this.PrimeiraG = PrimeiraG;
    }

    
}