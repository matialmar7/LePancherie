package src.Model;

import javafx.beans.InvalidationListener;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Model  {

    private static int StockPanchos = 0;

    public enum Mejoras{
        CURSORES(10,0.075,new Image("Res/Mejoras/Cursores/Cursores_lvl_1.png"),new Image("Res/Mejoras/Cursores/Cursores_lvl_2.png"),new Image("Res/Mejoras/Cursores/Cursores_lvl_3.png")),
        CONDIMENTOS(1,1, new Image("Res/Mejoras/Condimentos/Condimentos_lvl_1.png"),new Image("Res/Mejoras/Condimentos/Condimentos_lvl_2.png"),new Image("Res/Mejoras/Condimentos/Condimentos_lvl_3.png")),
        PAPAS(1,1,new Image("Res/Mejoras/Papas/Papas_lvl_1.png"),new Image("Res/Mejoras/Papas/Papas_lvl_2.png"),new Image("Res/Mejoras/Papas/Papas_lvl_3.png")),
        BEBIDAS(1,1,new Image("Res/Mejoras/Bebidas/Bebidas_lvl_1.png"),new Image("Res/Mejoras/Bebidas/Bebidas_lvl_2.png"),new Image("Res/Mejoras/Bebidas/Bebidas_lvl_3.png"));
        //PARRILLAS(1,1,new Image("Res/Mejoras/Parrillas"),new Image(),new Image()),
        //OLLAS(1,1,new Image("Res/Mejoras/Ollas"),new Image(),new Image()),
        //HELADERA(1,1,new Image("Res/Mejoras/Heladeras"),new Image(),new Image()),
        //CAJA_REGISTRADORA(1,1,new Image("Res/Mejoras/Caja_Registradora"),new Image(),new Image()),
        //EMPLEADOS(1,1,new Image("Res/Mejoras/Empleados"),new Image(),new Image()),
        //SUCURSALES(1,1,new Image("Res/Mejoras/Sucursales"),new Image(),new Image());

        private int cantidad;
        private int costoBase;
        private double growthRate;
        private Image lvl1;
        private Image lvl2;
        private Image lvl3;

        Mejoras(int costoBase,double costGrowth, Image lvl1, Image lvl2, Image lvl3 ){
            cantidad = 0;
            growthRate = costGrowth;
            this.costoBase = costoBase;
            this.lvl1 = lvl1;
            this.lvl2 = lvl2;
            this.lvl3 = lvl3;
        }
        public enum Nivel{
            INICIAL,MEDIO,AVANZADO;
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
        public String getMejoraUrl(Nivel lvl){
            switch (lvl){
                case INICIAL:
                    return lvl1.getUrl();
                case MEDIO:
                    return lvl2.getUrl();
                case AVANZADO:
                    return lvl3.getUrl();
            }
            return "";
        }
        public int getCosto(){
            int costo = 0;
            if(cantidad == 0){
                return costoBase;
            }
            else {
                costo = (int) (((cantidad * growthRate) +1) * costoBase);
            }
            return costo;
        }
        public Nivel getCurrentLevel(){
            int cantidadLvl2 = 50;
            int cantidadLvl3 = 100;

            if(cantidad >= cantidadLvl3){
                return Nivel.AVANZADO;
            }
            else if(cantidad >= cantidadLvl2){
                return Nivel.MEDIO;
            }
            return Nivel.INICIAL;
        }
    }
    private enum Fondos{
        //CALLE(),
        CASA(new Image("Res/Interfaz/Fondos/Fondo_Casa.png"));
        //PLAZA(),
        //FERIA(),
        //RESTAURANTE(),
        //ORBITA(),
        //MARTE();
        Image fondo;
        Fondos(Image fondo){
            this.fondo = fondo;
        }
    }

    //*****************************************************************************
    //OBSERVABLE
    private List<Observador> observadores = new ArrayList<Observador>();

    public void addObservador(Observador o) throws IllegalArgumentException{
        if(o != null){
            observadores.add(o);
        }
        else
        {
            throw new IllegalArgumentException();
        }

    }

    public void notificar(Observador o) throws IllegalArgumentException{
        if(observadores.contains(o)){
            o.update();
        }
        else{
            throw new IllegalArgumentException("El observador no se encuentra en la lista de observadores");
        }
    }

    public void notificarTodos(){
        for (Observador o: observadores) {
            notificar(o);}
    }
    //****************************************************************************

    public void addPancho(int i){
        StockPanchos += i;
        notificar(observadores.get(0));
    }
    public void takePanchos(int i){
        if(StockPanchos>= i){
            StockPanchos -= i;
            notificar(observadores.get(0));
        }
        else{
            System.out.println("Debe insertar un numero de panchos menor al stock actual");
        }
    }
    public int getPanchos(){
        return StockPanchos;
    }

    public void comprarMejora(Mejoras mejora){
        int costoMejora = mejora.getCosto();
        if(StockPanchos >= costoMejora){
            mejora.addCantidad(1);
            takePanchos(costoMejora);
        }
        else{
            System.out.println("Necesitas tener: " + costoMejora + " panchos para comprar " + mejora + " nivel " + (String.valueOf(mejora.getCantidad()+1)));
        }
    }

}
