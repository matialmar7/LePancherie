package src.main.java.Controller;

import javafx.concurrent.Task;

public class RandomMsj extends Task {
    Controlador controlador;

    public RandomMsj(Controlador controller){
        controlador = controller;
    }
    @Override
    protected Void call(){
        for(;;){
            try{
                Thread.sleep(5000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            controlador.printRandomMensaje();
        }


    }
}
