package src;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import src.Controller.Controlador;
import src.Controller.LoginController;
import src.Controller.PanchoIdleTask;
import src.Controller.RandomMsj;
import src.Model.Model;
import src.View.LoginView;
import src.View.MainView;

import java.io.IOException;

public class Main extends Application{
    private static Model modelo;
    private static Controlador controlador;
    private static LoginController loginController;
    private static MainView mainView;
    private static LoginView loginView;

    public static void main(String[] args) {
        modelo = new Model();
        controlador = new Controlador(modelo);
        loginController = new LoginController(modelo);
        launch(args);

    }
    private Task PanchoIdleTask = new PanchoIdleTask(controlador);
    private Task MessageRand = new RandomMsj(controlador);

    public static MainView getMainView() {
        return mainView;
    }

    @Override
    public void init(){
        Thread panchoIdle = new Thread(PanchoIdleTask);
        panchoIdle.setDaemon(true);
        panchoIdle.start();

        Thread RandomMsj = new Thread(MessageRand);
        RandomMsj.setDaemon(true);
        RandomMsj.start();
    }
    @Override
    public void start(Stage stage) throws Exception {
        loginView = new LoginView(modelo, loginController);
        mainView = new MainView(modelo,controlador);
        loginView.getStage().show();

        //mainView.getStage().show();
    }
}
