package src.View;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import src.Controller.PanchoIdleTask;
import src.Controller.RandomMsj;
import src.Main;
import src.Model.Observador;

public class MainView extends Application implements Observador {
    private FXMLLoader loader;

    private Label stockLbl;
    private Task PanchoIdleTask = new PanchoIdleTask(src.Main.controlador);
    private Task MessageRand = new RandomMsj(src.Main.controlador);

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

        loader.setController(src.Main.controlador);

        Parent root = loader.load();

        //SUBSCRIBO OBSERVADOR
        Main.modelo.addObservador(this);

        Scene scene = new Scene(root);
        //TOMO REFERENCIAS DE OBJETOS A ACTUALIZAR
        stockLbl = (Label) scene.lookup("#StockLbl");
        //
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
