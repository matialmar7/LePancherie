package src.View;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import src.Controller.MainViewController;
import src.Model.Model;

import java.util.Observable;
import java.util.Observer;

public class MainView extends Application implements Observer {
    FXMLLoader loader;
    Model modelo = new Model();
    MainViewController controlador = new MainViewController(modelo);


    @Override
    public void start(Stage primaryStage) throws Exception {

        loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        //Añado el controlador
        loader.setController(controlador);
        //Añado el observer
        modelo.addObserver(this);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Interfaz.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("Texto.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("Mejoras.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.setTitle("Le Pancherie");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void setController(MainViewController controlador){
        this.controlador = controlador;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
