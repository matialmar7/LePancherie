package src.Controller;

import javafx.concurrent.Task;

public class RandomMsj extends Task {
    MainViewController controlador;

    public RandomMsj(MainViewController controller){
        controlador = controller;
    }
    @Override
    protected Void call() throws Exception {
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
