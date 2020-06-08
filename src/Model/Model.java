package src.Model;

import javafx.beans.InvalidationListener;
import java.util.ArrayList;
import java.util.List;

public class Model  {

    private static int StockPanchos = 0;

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

}
