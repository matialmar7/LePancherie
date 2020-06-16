package src.Controller;

import javafx.concurrent.Task;

public class PanchoIdleTask extends Task {
    MainViewController controlador;

    public PanchoIdleTask(MainViewController controller){
        controlador = controller;
    }
    @Override
    protected Void call() throws Exception {
        try{
            Thread.sleep(2000);
        }
        catch (InterruptedException e){
            System.out.println("Fallo");
        }
        for(;;){
            try{
                Thread.sleep(100);
            }
            catch (InterruptedException e){
                System.out.println("Fallo");
            }
            controlador.addPanchoIdle();
        }
    }
}
