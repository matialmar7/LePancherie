package src.Model;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.System;
import java.util.Scanner;


public class Model implements Observado{

    private static double StockPanchos = 0;
    private static double PanchoIdle = 0;
    private static String mensaje = "LOS PANCHOS SON BUENOS PARA LA SALUD";
    private static String[] Mensajes = {
            "LOS PANCHOS SON BUENOS PARA LA SALUD",
            "LA PERSONA MAS LONGEVA DEL MUNDO COME DIEZ PANCHOS AL DIA",
            "COMER UN PANCHO AL DIA REDUCE EL RIESGO DE OBESIDAD EN UN -15%",
            "F",
            "A LOS PANCHOS SE LES DICE FRANCISCO",
            "EN UN KG ENTRAN 12 SALCHICAS",
            "EL PANCHO MAS LARGO DEL MUNDO TIENE 203 METROS Y PESA 120KGs",
            };


    public Model(){
    }

    private static long PanchoClickTimestamp = System.currentTimeMillis();

    private int costos_base [] = new int[10];
    private double prod_base [] = new double[10];
    private double taz_crec [] = new double[10];
    private double[] multiplier = new double[10];
    private int counter = 0;
    private int counter2 = 0;



    public enum Mejoras{


        CURSORES(15,100,1.75,1,new Image("Res/Mejoras/Cursores/Cursores_lvl_1.png"),new Image("Res/Mejoras/Cursores/Cursores_lvl_2.png"),new Image("Res/Mejoras/Cursores/Cursores_lvl_3.png")),
        CONDIMENTOS(90,10,1,1, new Image("Res/Mejoras/Condimentos/Condimentos_lvl_1.png"),new Image("Res/Mejoras/Condimentos/Condimentos_lvl_2.png"),new Image("Res/Mejoras/Condimentos/Condimentos_lvl_3.png")),
        PAPAS(600,100,1,1,new Image("Res/Mejoras/Papas/Papas_lvl_1.png"),new Image("Res/Mejoras/Papas/Papas_lvl_2.png"),new Image("Res/Mejoras/Papas/Papas_lvl_3.png")),
        BEBIDAS(6000,1000,1,1,new Image("Res/Mejoras/Bebidas/Bebidas_lvl_1.png"),new Image("Res/Mejoras/Bebidas/Bebidas_lvl_2.png"),new Image("Res/Mejoras/Bebidas/Bebidas_lvl_3.png")),
        PARRILLAS(60000,10000,1,1,new Image("Res/Mejoras/Parrillas/Parilla_lvl_1.png"),new Image("Res/Mejoras/Parrillas/Parilla_lvl_2.png"),new Image("Res/Mejoras/Parrillas/Parilla_lvl_3.png")),
        SALCHICHERA(1,100000,1,1,new Image("Res/Mejoras/Salchichera/Salchichera_lvl_1.png"),new Image("Res/Mejoras/Salchichera/Salchichera_lvl_2.png"),new Image("Res/Mejoras/Salchichera/Salchichera_lvl_3.png")),
        HELADERA(1,10000000,1,1,new Image("Res/Mejoras/Heladeras/Heladeras_lvl_1.png"),new Image("Res/Mejoras/Heladeras/Heladeras_lvl_2.png"),new Image("Res/Mejoras/Heladeras/Heladeras_lvl_3.png")),
        CAJA_REGISTRADORA(1000000,1,1,1,new Image("Res/Mejoras/Caja_Registradora/Caja_Registradora_lvl_1.png"),new Image("Res/Mejoras/Caja_Registradora/Cajas_Registradora_lvl_2.png"),new Image("Res/Mejoras/Caja_Registradora/Cajas_Registradora_lvl_3.png")),
        EMPLEADOS(1,1,1,1,new Image("Res/Mejoras/Empleados/Empleados_lvl_1.png"),new Image("Res/Mejoras/Empleados/Empleados_lvl_2.png"),new Image("Res/Mejoras/Empleados/Empleados_lvl_3.png")),
        SUCURSALES(1,1,1,1,new Image("Res/Mejoras/Sucursales/Sucursales_lvl_1.png"),new Image("Res/Mejoras/Sucursales/Sucursales_lvl_2.png"),new Image("Res/Mejoras/Sucursales/Sucursales_lvl_3.png"));


        private int cantidad;
        private double costoBasee;
        private double prodBase;
        private double growthRate;
        private double multiplier;
        private Nivel currentLvl;
        private Image lvl1;
        private Image lvl2;
        private Image lvl3;

        Mejoras(double costoBasee,double prodBase,double costGrowth,double multiplier, Image lvl1, Image lvl2, Image lvl3 ){
            cantidad = 0;
            growthRate = costGrowth;
            currentLvl = Nivel.INICIAL;
            this.costoBasee = costoBasee;
            this.prodBase = prodBase;
            this.multiplier=multiplier;
            this.lvl1 = lvl1;
            this.lvl2 = lvl2;
            this.lvl3 = lvl3;
        }
        public enum Nivel{
            INICIAL,MEDIO,AVANZADO
        }

        public void addCantidad(int i) throws IllegalArgumentException{
            if (i<0){
                throw new IllegalArgumentException("La cantidad no debe ser negativa");
            }
            else{
                cantidad += i;
            }
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
            };
            return lvl1.getUrl();
        }
        public double getCosto(){

            return (int) (((cantidad * growthRate)+1) * costoBasee);
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
        //CAMBIAR
        public double getPanchoIdleValue(){
            System.out.println(this.prodBase);
            System.out.println(this.cantidad);
            return this.cantidad*this.prodBase; //ESTO ES CUALQUIER PELOTUDES
        }
        public void setCostoBase(double costo){
            this.costoBasee=costo;
        }
        public void setGrowthRate(double taza){
            this.growthRate=taza;
        }
        public void setProdBase(double prod){
            this.prodBase=prod;
        }
        public void setMultiplier(double multi){
            this.multiplier=multi;
        }



    }
    private enum Fondos{
        CALLE(new Image("Res/Interfaz/Fondos/Fondo_Calle.png"),0),
        CASA(new Image("Res/Interfaz/Fondos/Fondo_Casa.png"),1000),
        PLAZA(new Image("Res/Interfaz/Fondos/Fondo_Plaza.png"),10000),
        FERIA(new Image("Res/Interfaz/Fondos/Fondo_Feria.png"),100000),
        RESTAURANTE(new Image("Res/Interfaz/Fondos/Fondo_Restaurante.png"),1000000),
        ORBITA(new Image("Res/Interfaz/Fondos/Fondo_Orbita.png"),10000000),
        MARTE(new Image("Res/Interfaz/Fondos/Fondo_Marte.png"),100000000);

        Image fondo;
        int CostoPanchos;
        Fondos(Image fondo, int CantPanchos){
            this.fondo = fondo;
            CostoPanchos = CantPanchos;
        }
    }

    //*****************************************************************************
    //OBSERVABLE
    private List<Observador> observadores = new ArrayList<>();


    public double getCostBase(int i){
        return costos_base[i];
    }

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


    private boolean validarClick(long timestamp){
        return (timestamp - PanchoClickTimestamp) >= 100;
    }
    private void takePanchos(double i){
        if(StockPanchos>= i){
            StockPanchos -= i;
            notificar(observadores.get(0));
        }
        else{
            System.out.println("Debe insertar un numero de panchos menor al stock actual");
        }
    }
    public void addPancho(double i){
        if(i>0)
        {
            StockPanchos += i;
            notificar(observadores.get(0));
        }
    }
    public void addPanchoClick(double i){
        long timestamp = System.currentTimeMillis();
        if(validarClick(timestamp)){
            PanchoClickTimestamp = timestamp;
            addPancho(i);
        }
        else{
            setMensaje("PARA UN POCO ESTAS YENDO DEMASIADO RAPIDO!!");
        }
    }
    /*public void addPanchoIdle(double val){
        PanchoIdle = val;
    }*/
    public void comprarMejora(Mejoras mejora){
        double costoMejora = mejora.getCosto();
        if(StockPanchos >= costoMejora){
            mejora.addCantidad(1);
            mejora.updateLevel();
            takePanchos(costoMejora);
            updatePanchoIdle();
        }
        else{
            String msj = "Necesitas tener: " + costoMejora + " panchos para comprar " + mejora + " nivel " + (mejora.getCantidad()+1);
            setMensaje(msj);
        }
    }

    private void updatePanchoIdle() {
        PanchoIdle = 0;
        for(Mejoras m : Mejoras.values()){
            PanchoIdle += m.prodBase*m.cantidad;
        }
    }

    public int getPanchos(){
        return (int)StockPanchos;
    }
    public double getPanchoIdle(){
        return PanchoIdle;
    }
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
    public void inicializarMejoras(){
        try {
            int counter = 0;
            int counter2 = 0;
            File myObj = new File("D:/Google_Drive/Falcultad/Ing Software/Lepancherie/src/Model/Tazas_de_creci.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println(data);

                if(data.contains("//")){
                    data = myReader.nextLine();
                }
                System.out.println(data);
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

                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int h = 0;
        for(Mejoras m : Mejoras.values()){
            //System.out.println(m.cantidad);
            m.setCostoBase(costos_base[h]);
            m.setProdBase(prod_base[h]);
            m.setGrowthRate(taz_crec[h]);
            m.setMultiplier(multiplier[h]);
            h++;

        }
    }
}
