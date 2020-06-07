package src.Model;

import javafx.beans.InvalidationListener;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {
    private static int StockPanchos = 0;

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    public void addPancho(int i){
        StockPanchos += i;
        notifyObservers();
    }

    public int getPanchos(){
        return StockPanchos;
    }
}
