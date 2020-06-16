package src.View;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.concurrent.*;
import src.Controller.MainViewController;
import src.Controller.PanchoIdleTask;
import src.Controller.RandomMsj;
import src.Model.Model;

import java.util.Random;

public class MainView extends Application implements src.Model.Observador  {
    private FXMLLoader loader;
    private Model modelo = new Model();
    private MainViewController controlador = new MainViewController(modelo);

    private Label stock;
    private Label panchosLbl;
    private Label msjBanner;
    private Label cursorLbl;
    private Label cursorCostLbl;

    private Button cursor;

    private Task PanchoIdleTask = new PanchoIdleTask(controlador);
    private Task MessageRand = new RandomMsj(controlador);

    @Override
    public void init() throws Exception {
        Thread panchoIdle = new Thread(PanchoIdleTask);
        panchoIdle.setDaemon(true);
        panchoIdle.start();

        Thread RandomMsj = new Thread(MessageRand);
        RandomMsj.setDaemon(true);
        RandomMsj.start();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        //Añado el controlador
        loader.setController(controlador);
        //Añado el observer
        modelo.addObservador(this);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        //Asignacion de elementos
        stock = (Label) scene.lookup("#StockLbl");
        msjBanner = (Label) scene.lookup("#msjBanner");
        cursorLbl = (Label) scene.lookup("#cursorLbl");
        cursorCostLbl = (Label) scene.lookup("#cursorCostLbl");
        panchosLbl = (Label) scene.lookup("#panchosLbl");
        cursor = (Button) scene.lookup("#Cursores");

        //INCIALIZACIONES
        cursorCostLbl.setText(Integer.toString(Model.Mejoras.CURSORES.getCosto()));
        panchosLbl.setText(String.format("%.0f", modelo.getPanchoIdle()*10) + " panchos/s");

        scene.getStylesheets().add(getClass().getResource("css/Interfaz.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("css/Texto.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("css/Mejoras.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.setTitle("Le Pancherie");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public void setController(MainViewController controlador){
        this.controlador = controlador;
    }

    @Override
    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //ACTUALIZO LABELS
                stock.setText(String.valueOf(modelo.getPanchos()));
                msjBanner.setText(modelo.getMensaje());
                cursorLbl.setText(Integer.toString(Model.Mejoras.CURSORES.getCantidad()));
                cursorCostLbl.setText(Integer.toString(Model.Mejoras.CURSORES.getCosto()));
                panchosLbl.setText(String.format("%.0f", modelo.getPanchoIdle()*10) + " panchos/s");
                //ACTUALIZO GRAFICOS DE MEJORAS
                cursor.setStyle(String.format("-fx-background-image: url(%s);",Model.Mejoras.CURSORES.getMejoraUrl(Model.Mejoras.CURSORES.getCurrentLevel())));
                //Otros

            }
        });
    }

}
