package src.main.java.Controller;

import javafx.concurrent.Task;

public class PanchoIdleTask extends Task {
     Controlador controlador;

    public PanchoIdleTask(Controlador controller){
        controlador = controller;
    }
    @Override
    protected Void call(){
        for(;;){
            try{
                Thread.sleep(100);
            }
            catch (InterruptedException e){
                System.out.println("Fallo");
            }

            //Añado panchitos cada 100ms
            controlador.addPanchoIdle();
        }
    }
}
