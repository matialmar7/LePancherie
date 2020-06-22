package src;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import src.Controller.*;
import src.Model.Model;
import src.View.LoginView;
import src.View.MainView;
import src.View.StatsView;

import java.io.IOException;

public class Main extends Application{
    private static Model modelo;
    private static Controlador mainController;
    private static LoginController loginController;
    private static StatsController statsController;
    private static MainView mainView;
    private static LoginView loginView;
    private static StatsView statsView;
    private Task PanchoIdleTask = new PanchoIdleTask(mainController);
    private Task MessageRand = new RandomMsj(mainController);

    public static void main(String[] args) {
        modelo = new Model();
        mainController = new Controlador(modelo);
        loginController = new LoginController(modelo);
        statsController = new StatsController(modelo);
        launch(args);

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
        statsView = new StatsView(modelo, statsController);
        mainView = new MainView(modelo,mainController);

        loginView.getStage().show();

    }
    public static MainView getMainView() {
        return mainView;
    }
    public static StatsView getStatsView() {
        return statsView;
    }
}
