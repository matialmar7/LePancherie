package src.Controller;

import javafx.concurrent.Task;

public class PanchoIdleTask extends Task {
    MainViewController controlador;

    public PanchoIdleTask(MainViewController controller){
        controlador = controller;
    }
    @Override
    protected Void call() throws Exception {
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