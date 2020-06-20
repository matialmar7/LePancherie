package src.View;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import src.Main;
import src.Model.Model;
import src.Model.Observador;

public class MainView extends Application implements Observador {
    private FXMLLoader loader;

    @FXML
    private Button PanchoBtn;
    @FXML
    private Button CursorBtn;
    @FXML
    private Button CondimentosBtn;
    @FXML
    private Button PapasBtn;
    @FXML
    private Button BebidasBtn;
    @FXML
    private Button ParrillasBtn;
    @FXML
    private Button SalchicherasBtn;
    @FXML
    private Button HeladerasBtn;
    @FXML
    private Button CajaBtn;
    @FXML
    private Button EmpleadosBtn;
    @FXML
    private Button SucursalesBtn;
    @FXML
    private Label stockLbl;


    @Override
    public void start(Stage primaryStage) throws Exception {

        loader = new FXMLLoader(getClass().getResource("MainView.fxml"));

        loader.setController(src.Main.controlador);

        Parent root = loader.load();

        //SUBSCRIBO OBSERVADOR
        Main.modelo.addObservador(this);

        Scene scene = new Scene(root);
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

    @Override
    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //ACTUALIZO LABELS
                stockLbl.setText(String.valueOf(src.Main.modelo.getPanchos()));
                //msjBanner.setText(modelo.getMensaje());
                //cursorLbl.setText(Integer.toString(Model.Mejoras.CURSORES.getCantidad()));
                //cursorCostLbl.setText(Integer.toString(Model.Mejoras.CURSORES.getCosto()));
                //panchosLbl.setText(String.format("%.0f", modelo.getPanchoIdle()*10) + " panchos/s");
                //ACTUALIZO GRAFICOS DE MEJORAS
                //cursor.setStyle(String.format("-fx-background-image: url(%s);",Model.Mejoras.CURSORES.getMejoraUrl(Model.Mejoras.CURSORES.getCurrentLevel())));
                //Otros

            }
        });
    }
}
