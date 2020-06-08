package src.View;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.application.Platform;

import src.Controller.MainViewController;
import src.Model.Model;

public class MainView extends Application implements src.Model.Observador  {
    private FXMLLoader loader;
    private Model modelo = new Model();
    private MainViewController controlador = new MainViewController(modelo);
    private Label stock;
    @Override
    public void start(Stage primaryStage) throws Exception {

        loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        //Añado el controlador
        loader.setController(controlador);
        //Añado el observer
        modelo.addObservador(this);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        stock = (Label) scene.lookup("#StockLbl");

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
    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stock.setText(String.valueOf(modelo.getPanchos()));
            }
        });
    }
}
