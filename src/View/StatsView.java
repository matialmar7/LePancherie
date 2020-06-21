package src.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import src.Controller.StatsController;
import src.Model.Model;

import java.io.IOException;

public class StatsView {
        Model modelo;
        StatsController controlador;
        FXMLLoader loader;
        Scene statsScene;
        Stage statsStage;

        public StatsView(Model mod, StatsController cont) throws IOException {
            modelo  = mod;
            controlador = cont;

            //Cargo ventana login
            loader = new FXMLLoader(getClass().getResource("StatsView.fxml"));
            loader.setController(controlador);
            Parent login = loader.load();
            statsScene = new Scene(login);
            statsScene.getStylesheets().add(getClass().getResource("css/Texto.css").toExternalForm());
            statsScene.getStylesheets().add(getClass().getResource("css/Stats.css").toExternalForm());
            statsStage = new Stage();
            statsStage.setResizable(false);
            //loginStage.setTitle("LePancherie - Login");
            statsStage.setScene(statsScene);
            statsStage.initStyle(StageStyle.UNDECORATED);
            statsStage.initModality(Modality.APPLICATION_MODAL);
        }

        public Stage getStage(){
            return statsStage;
        }
}
