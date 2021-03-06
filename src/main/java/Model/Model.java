package src.main.java.Model;

import javafx.scene.image.Image;

import java.io.*;
import java.text.CompactNumberFormat;
import java.text.NumberFormat;
import java.util.*;
import java.lang.System;

public class Model implements Observado{

    private static double StockPanchos = 0;
    private static double PanchoIdle = 0;
    private NumberFormat nf = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
    private static String Alias = "";
    private static Fondos fondo = Fondos.CASA;
    private static String mensaje = "LOS PANCHOS SON BUENOS PARA LA SALUD";
    private static String[] Mensajes = {
            "LOS PANCHOS SON BUENOS PARA LA SALUD",
            "LA PERSONA MAS LONGEVA DEL MUNDO COME DIEZ PANCHOS AL DIA",
            "COMER UN PANCHO AL DIA REDUCE EL RIESGO DE OBESIDAD EN UN -15%",
            "F",
            "A LOS PANCHOS SE LES DICE FRANCISCO",
            "EN UN KG ENTRAN 12 SALCHICAS",
            "EL PANCHO MAS LARGO DEL MUNDO TIENE 203 METROS Y PESA 120KGs","AGUANTE PANCHO VIEJAAAA",
            };

    private static long PanchoClickTimestamp = System.currentTimeMillis();

    //WEAS PARA CARGA DE ARCHIVO
    private int[] cantidadMejoras = new int[10];
    String pesho = "pesho.txt";
    private File file = null;
    //WEAS PARA CARGAR LAS MEJROAS
    private int[] costos_base  = new int[10];
    private double[] prod_base = new double[10];
    private double[] taz_crec  = new double[10];
    private double[] multiplier = new double[10];
    //Valores de configuracion mejoras
    public enum Mejoras{
        CURSORES(1,3,0.075,new Image("src/main/resources/Mejoras/Cursores/Cursores_lvl_1.png"),new Image("src/main/resources/Mejoras/Cursores/Cursores_lvl_2.png"),new Image("src/main/resources/Mejoras/Cursores/Cursores_lvl_3.png")),
        CONDIMENTOS(2,1,1, new Image("src/main/resources/Mejoras/Condimentos/Condimentos_lvl_1.png"),new Image("src/main/resources/Mejoras/Condimentos/Condimentos_lvl_2.png"),new Image("src/main/resources/Mejoras/Condimentos/Condimentos_lvl_3.png")),
        PAPAS(3,1,1,new Image("src/main/resources/Mejoras/Papas/Papas_lvl_1.png"),new Image("src/main/resources/Mejoras/Papas/Papas_lvl_2.png"),new Image("src/main/resources/Mejoras/Papas/Papas_lvl_3.png")),
        BEBIDAS(4,1,1,new Image("src/main/resources/Mejoras/Bebidas/Bebidas_lvl_1.png"),new Image("src/main/resources/Mejoras/Bebidas/Bebidas_lvl_2.png"),new Image("src/main/resources/Mejoras/Bebidas/Bebidas_lvl_3.png")),
        PARRILLAS(5,1,1,new Image("src/main/resources/Mejoras/Parrillas/Parilla_lvl_1.png"),new Image("src/main/resources/Mejoras/Parrillas/Parilla_lvl_2.png"),new Image("src/main/resources/Mejoras/Parrillas/Parilla_lvl_3.png")),
        SALCHICHERA(6,1,1,new Image("src/main/resources/Mejoras/Salchichera/Salchichera_lvl_1.png"),new Image("src/main/resources/Mejoras/Salchichera/Salchichera_lvl_2.png"),new Image("src/main/resources/Mejoras/Salchichera/Salchichera_lvl_3.png")),
        HELADERA(7,1,1,new Image("src/main/resources/Mejoras/Heladeras/Heladeras_lvl_1.png"),new Image("src/main/resources/Mejoras/Heladeras/Heladeras_lvl_2.png"),new Image("src/main/resources/Mejoras/Heladeras/Heladeras_lvl_3.png")),
        CAJA_REGISTRADORA(8,1,1,new Image("src/main/resources/Mejoras/Caja_Registradora/Caja_Registradora_lvl_1.png"),new Image("src/main/resources/Mejoras/Caja_Registradora/Cajas_Registradora_lvl_2.png"),new Image("src/main/resources/Mejoras/Caja_Registradora/Cajas_Registradora_lvl_3.png")),
        EMPLEADOS(9,1,1,new Image("src/main/resources/Mejoras/Empleados/Empleados_lvl_1.png"),new Image("src/main/resources/Mejoras/Empleados/Empleados_lvl_2.png"),new Image("src/main/resources/Mejoras/Empleados/Empleados_lvl_3.png")),
        SUCURSALES(10,1,1,new Image("src/main/resources/Mejoras/Sucursales/Sucursales_lvl_1.png"),new Image("src/main/resources/Mejoras/Sucursales/Sucursales_lvl_2.png"),new Image("src/main/resources/Mejoras/Sucursales/Sucursales_lvl_3.png"));
        public enum Nivel{
            INICIAL,MEDIO,AVANZADO
        }

        private int cantidad;
        private double prodBase;
        private int costoBase;
        private double growthRate;
        private Nivel currentLvl;
        private Image lvl1;
        private Image lvl2;
        private Image lvl3;

        Mejoras(int costoBase, double prodBase ,double costGrowth, Image lvl1, Image lvl2, Image lvl3 ){
            cantidad = 0;
            growthRate = costGrowth;
            currentLvl = Nivel.INICIAL;
            this.prodBase = prodBase;
            this.costoBase = costoBase;
            this.lvl1 = lvl1;
            this.lvl2 = lvl2;
            this.lvl3 = lvl3;
        }

        public void addCantidad(int i) throws IllegalArgumentException{
            if (i<0){
                throw new IllegalArgumentException("La cantidad no debe ser negativa");
            }
            else{
                cantidad += i;
            }
        }
        public double getProdBase() {
            return prodBase;
        }
        public int getCantidad(){
            return cantidad;
        }
        public String getMejoraUrl(){
            switch (getCurrentLevel())
            {
                case INICIAL:
                    return lvl1.getUrl();
                case MEDIO :
                    return lvl2.getUrl();
                case AVANZADO:
                    return lvl3.getUrl();
            }
            return lvl1.getUrl();
        }
        public int getCosto(){
            int costo;
            if(cantidad == 0){
                return costoBase;
            }
            else {
                costo = (int) (((cantidad * growthRate) +1) * costoBase);
            }
            return costo;
        }
        public void updateLevel(){
            int cantidadLvl2 = 75;
            int cantidadLvl3 = 150;

            if(cantidad >= cantidadLvl3){
                currentLvl =  Nivel.AVANZADO;
            }
            else if(cantidad >= cantidadLvl2){
                currentLvl =  Nivel.MEDIO;
            }
        }
        public Nivel getCurrentLevel(){
            return currentLvl;
        }
        public void setCostoBase(double costo){
            this.costoBase= (int) costo;
        }
        public void setGrowthRate(double taza){
            this.growthRate=taza;
        }
        public void setProdBase(double prod){
            this.prodBase=prod;
        }
    }
    public enum Fondos{
        CASA(0,new Image("src/main/resources/Interfaz/Fondos/Fondo_Casa.png"),0),
        PLAZA(1,new Image("src/main/resources/Interfaz/Fondos/Fondo_Plaza.png"),10000),
        RESTAURANTE(2,new Image("src/main/resources/Interfaz/Fondos/Fondo_Restaurante.png"),400000),
        CASINO(3,new Image("src/main/resources/Interfaz/Fondos/Fondo_Casino.png"),1000000),
        ATLANTIS(4,new Image("src/main/resources/Interfaz/Fondos/Fondo_Atlantis.png"),10000000),
        ORBITA(5,new Image("src/main/resources/Interfaz/Fondos/Fondo_Orbita.png"),50000000),
        MARTE(6,new Image("src/main/resources/Interfaz/Fondos/Fondo_Marte.png"),100000000);

        Image fondo;
        int CostoPanchos;
        int nivel;

        Fondos(int nivel,Image fondo, int CantPanchos){
            this.fondo = fondo;
            this.nivel = nivel;
            CostoPanchos = CantPanchos;
        }
        public int getCosto(){
            return CostoPanchos;
        }
        public String getUrl(){
            return fondo.getUrl();
        }
        public int getNivel(){
            return nivel;
        }
    }

    //*****************************************************************************
    //OBSERVABLE
    private List<Observador> observadores = new ArrayList<>();

    @Override
    public void addObservador(Observador o) throws IllegalArgumentException{
        if(o != null){
            observadores.add(o);
        }
        else
        {
            throw new IllegalArgumentException();
        }

    }
    @Override
    public void notificar(Observador o) throws IllegalArgumentException{
        if(observadores.contains(o)){
            o.update();
        }
        else{
            throw new IllegalArgumentException("El observador no se encuentra en la lista de observadores");
        }
    }
    @Override
    public void notificarTodos(){
        for (Observador o: observadores) {
            notificar(o);}
    }
    //****************************************************************************
    //Clicks
    private boolean validarClick(long timestamp){
        return (timestamp - PanchoClickTimestamp) >= 100;
    }
    private void takePanchos(int i){
            StockPanchos -= i;
            notificar(observadores.get(0));
    }
    private void updateLvl(){
        for(Fondos f : Fondos.values()){
            if(getPanchos() >= f.getCosto() && f.getNivel()>fondo.getNivel()){
                fondo = f;
            }
        }
    }
    public Fondos getLvl(){
        return fondo;
    }
    /*ACA LE SUMO i AL STOCK DE PANCHOS*/
    public void addPancho(double i){
        if(i>0)
        {
            StockPanchos += i;
            updateLvl();
            notificar(observadores.get(0));
            notificar(observadores.get(1));
        }
    }
    /*ENTRO CON UN VALOR i Y LE ADDPANCHO(i)*/
    public void addPanchoClick(){
        long timestamp = System.currentTimeMillis();
        if(validarClick(timestamp)){
            PanchoClickTimestamp = timestamp;
            if(Mejoras.CURSORES.getCantidad() == 0){
                addPancho(1);
            }
            else{
                addPancho(Mejoras.CURSORES.getCantidad()*0.1+1);
            }
        }
        else{
            setMensaje("PARA UN POCO ESTAS YENDO DEMASIADO RAPIDO!!");
        }
    }
    public void setPanchoIdle(){
        PanchoIdle = 0;
        for(Mejoras m : Mejoras.values()){
            PanchoIdle += m.getCantidad()*m.getProdBase();
        }
    }
    private void setFondo(Fondos f){
        fondo = f;
    }
    private Fondos getFondo(){
        return fondo;
    }
    public void comprarMejora(Mejoras mejora){
        if(mejora.cantidad == 200){
            setMensaje("HAS LLEGADO AL LIMITE PARA ESTA CANTIDAD DE MEJORAS");
        }
        else{
            int costoMejora = mejora.getCosto();
            if(StockPanchos >= costoMejora){
                mejora.addCantidad(1);
                mejora.updateLevel();
                takePanchos(costoMejora);
                this.setPanchoIdle();
            }
            else{
                String msj = "Necesitas tener: " + costoMejora + " panchos para comprar " + mejora + " nivel " + (mejora.getCantidad()+1);
                setMensaje(msj);
            }
        }

    }
    public int getPanchos(){
        return (int)StockPanchos;
    }
    public double getPanchoIdle(){
        return PanchoIdle;
    }
    public void setAlias(String a){
        this.Alias = a;
    }
    public void loadPlayerData(){
        this.checkFile();   //VEO SI EL ARCHIVO EXISTE Y SINO CREO
        //System.out.println("Estoy Cargando la partida de: " + this.Alias);
        Scanner lector = null;
        String line;
        int i = 0;
        try {
            lector = new Scanner(file);
            line = lector.nextLine();
            this.addPancho(Integer.parseInt(line.trim()));
            if(lector.hasNextLine()){
                line = lector.nextLine();
                int fond = Integer.parseInt(line.trim());
                for(Fondos f : Fondos.values()){
                    if(f.ordinal() == fond){
                        setFondo(f);
                    }
                }
            }

            for(Mejoras m : Mejoras.values()){
                if(lector.hasNextLine()){
                    line = lector.nextLine();
                    if(Integer.parseInt(line.trim()) > 200){
                        m.cantidad = 1;
                    }
                    else{
                        m.cantidad = Integer.parseInt(line.trim());
                        m.updateLevel();
                    }
                }
            }
            this.setPanchoIdle();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        lector.close();
        notificarTodos();
    }
    public void savePlayerData(){
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(""+ this.getPanchos()+"\n");
            writer.write(""+ this.getFondo().ordinal()+"\n");
            for(Mejoras m : Mejoras.values()){
                writer.write(m.getCantidad() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void checkFile(){
        String pato = this.Alias + ".txt";
        file = new File(pato);  //file ES EL ARCHIVO SOBRE EL CUAL VOY A TRABAJAR
        try {
            if(file.createNewFile()){   //SI file NO EXISTE LO CREO
                FileWriter writer = new FileWriter(file);
                writer.write(""+0);
                writer.close();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
    public void inicializarMejoras(){
        int counter = 0;
        int counter2 = 0;
        //File myObj = new File("Tazas_de_creci.txt");
        BufferedReader myObj = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("Tazas_de_creci.txt")));
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();

            if(data.contains("//")){
                data = myReader.nextLine();
            }
            if(counter == 10 || counter == 20||counter == 30){
                counter2 = 0;
            }
            if(counter < 10){
                costos_base[counter2] = Integer.parseInt(data);
                counter2++;
            }
            if(counter >= 10 && counter < 20 ){
                prod_base[counter2] = Double.parseDouble(data);
                counter2++;
            }
            if(counter >= 20 && counter < 30){
                taz_crec[counter2] = Double.parseDouble(data);
                counter2++;
            }
            if(counter >= 30 && counter < 40){
                multiplier[counter2] = Double.parseDouble(data);
                counter2++;
            }
            counter++;
        }
        myReader.close();

        int h = 0;
        for(Mejoras m : Mejoras.values()){
            //System.out.println(m.cantidad);
            m.setCostoBase(costos_base[h]);
            m.setProdBase(prod_base[h]);
            m.setGrowthRate(taz_crec[h]);
            //m.setMultiplier(multiplier[h]);
            h++;

        }
    }
    /*ESTO SE ENCARGA DEL MSJ QUE SE ENCUENTRA EN LA BARRA SUPERIOR*/
    public String getMensaje(){
        return mensaje;
    }
    public String getRandomMessage(){ //hacer privado
        Random rand = new Random();
        return Mensajes[rand.nextInt(Mensajes.length)]; //Elijo mensaje aleatorio
    }
    public void setMensaje(String msj){
        mensaje = msj;
        //notificar(observadores.get(0));
    }
    //Auxiliares
    public String formatText(double i){
        NumberFormat nf = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        if(i<10){
            return String.format("%.1f",i);
        }
        nf.setMaximumFractionDigits(1);
        String out = nf.format(i);
        return out;
    }
    public String formatTextLong(double i){
        NumberFormat nf = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);
        nf.setMaximumFractionDigits(2);
        nf.setMaximumIntegerDigits(2);
        String out = nf.format(i);
        return out;
    }
}
