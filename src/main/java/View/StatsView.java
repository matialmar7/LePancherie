package src.main.java.View;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import src.main.java.Controller.StatsController;
import src.main.java.Model.Model;
import src.main.java.Model.Observador;
import javafx.scene.control.Label;

import java.io.IOException;

public class StatsView implements Observador {
        Model modelo;
        StatsController controlador;
        FXMLLoader loader;
        Scene statsScene;
        Stage statsStage;

        //LABEL DEL STOCK
        private Label texStock;
        private Label texPs;
        //LABEL STATS DE MEJORAS
        private Label texCURSORES;
        private Label texCONDIMENTOS;
        private Label texPAPAS;
        private Label texBEBIDAS;
        private Label texPARRILLAS;
        private Label texSALCHICHERA;
        private Label texHELADERA;
        private Label texCAJA_REGISTRADORA;
        private Label texEMPLEADOS;
        private Label texSUCURSALES;





    public StatsView(Model mod, StatsController cont) throws IOException {
            modelo  = mod;
            controlador = cont;

            //Cargo ventana login
            loader = new FXMLLoader(getClass().getResource("StatsView.fxml"));
            loader.setController(controlador);
            Parent login = loader.load();

            modelo.addObservador(this);

            statsScene = new Scene(login);

            //CARGA DE LOS LABELS
            texStock = (Label)statsScene.lookup("#texStock");
            texPs = (Label)statsScene.lookup("#texPs");
            texCURSORES = (Label)statsScene.lookup("#texCURSORES");
            texCONDIMENTOS = (Label)statsScene.lookup("#texCONDIMENTOS");
            texPAPAS = (Label)statsScene.lookup("#texPAPAS");
            texBEBIDAS = (Label)statsScene.lookup("#texBEBIDAS");
            texPARRILLAS = (Label)statsScene.lookup("#texPARRILLAS");
            texSALCHICHERA = (Label)statsScene.lookup("#texSALCHICHERA");
            texHELADERA = (Label)statsScene.lookup("#texHELADERA");
            texCAJA_REGISTRADORA = (Label)statsScene.lookup("#texCAJA_REGISTRADORA");
            texEMPLEADOS = (Label)statsScene.lookup("#texEMPLEADOS");
            texSUCURSALES = (Label)statsScene.lookup("#texSUCURSALES");


            statsScene.getStylesheets().add(getClass().getResource("css/Texto.css").toExternalForm());
            statsScene.getStylesheets().add(getClass().getResource("css/Stats.css").toExternalForm());
            statsStage = new Stage();
            statsStage.setResizable(false);
            statsStage.setTitle("Estadisticas");
            Image icono = new Image("src/main/resources/Interfaz/windowsIcon.png");
            statsStage.getIcons().add(icono);
            statsStage.setScene(statsScene);
            statsStage.initStyle(StageStyle.UNDECORATED);
            statsStage.initModality(Modality.APPLICATION_MODAL);
        }

        public Stage getStage(){
            return statsStage;
        }

    @Override
    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                texStock.setText("Stock de Panchos: " + (modelo.formatTextLong(modelo.getPanchos())));
                texPs.setText("Panchos/s: " +  (modelo.formatText(modelo.getPanchoIdle())));
                texCURSORES.setText("CURSORES:          " + String.format("%.1f",(Model.Mejoras.CURSORES.getCantidad()*Model.Mejoras.CURSORES.getProdBase())) + " p/seg = " + (String.format("%.1f", Model.Mejoras.CURSORES.getCantidad()*Model.Mejoras.CURSORES.getProdBase()/modelo.getPanchoIdle()*100)) + "%");
                texCONDIMENTOS.setText("CONDIMENTOS:  " + String.format("%.1f",(Model.Mejoras.CONDIMENTOS.getCantidad()*Model.Mejoras.CONDIMENTOS.getProdBase())) +" p/seg = " + (String.format("%.1f", Model.Mejoras.CONDIMENTOS.getCantidad()*Model.Mejoras.CONDIMENTOS.getProdBase()/modelo.getPanchoIdle()*100)) + "%");
                texPAPAS.setText("PAPAS:                 " + String.format("%.1f",(Model.Mejoras.PAPAS.getCantidad()*Model.Mejoras.PAPAS.getProdBase())) +" p/seg = " + (String.format("%.1f", Model.Mejoras.PAPAS.getCantidad()*Model.Mejoras.PAPAS.getProdBase()/modelo.getPanchoIdle()*100)) + "%");
                texBEBIDAS.setText("BEBIDAS:              " + String.format("%.1f",(Model.Mejoras.BEBIDAS.getCantidad()*Model.Mejoras.BEBIDAS.getProdBase())) +" p/seg = " + (String.format("%.1f", Model.Mejoras.BEBIDAS.getCantidad()*Model.Mejoras.BEBIDAS.getProdBase()/modelo.getPanchoIdle()*100)) + "%");
                texPARRILLAS.setText("PARRILLAS:          " +String.format("%.1f",(Model.Mejoras.PARRILLAS.getCantidad()*Model.Mejoras.PARRILLAS.getProdBase())) +" p/seg = " + (String.format("%.1f", Model.Mejoras.PARRILLAS.getCantidad()*Model.Mejoras.PARRILLAS.getProdBase()/modelo.getPanchoIdle()*100)) + "%");
                texSALCHICHERA.setText("SALCHICHERA:    " + String.format("%.1f",(Model.Mejoras.SALCHICHERA.getCantidad()*Model.Mejoras.SALCHICHERA.getProdBase())) +" p/seg = " + (String.format("%.1f", Model.Mejoras.SALCHICHERA.getCantidad()*Model.Mejoras.SALCHICHERA.getProdBase()/modelo.getPanchoIdle()*100)) + "%");
                texHELADERA.setText("HELADERA:          " +String.format("%.1f",(Model.Mejoras.HELADERA.getCantidad()*Model.Mejoras.HELADERA.getProdBase())) +" p/seg = " + (String.format("%.1f", Model.Mejoras.HELADERA.getCantidad()*Model.Mejoras.HELADERA.getProdBase()/modelo.getPanchoIdle()*100)) + "%");
                texCAJA_REGISTRADORA.setText("CAJAS:                 " + String.format("%.1f",(Model.Mejoras.CAJA_REGISTRADORA.getCantidad()*Model.Mejoras.CAJA_REGISTRADORA.getProdBase())) +" p/seg = " + (String.format("%.1f", Model.Mejoras.CAJA_REGISTRADORA.getCantidad()*Model.Mejoras.CAJA_REGISTRADORA.getProdBase()/modelo.getPanchoIdle()*100)) + "%");
                texEMPLEADOS.setText("EMPLEADOS:       " + String.format("%.1f",(Model.Mejoras.EMPLEADOS.getCantidad()*Model.Mejoras.EMPLEADOS.getProdBase())) +" p/seg = " + (String.format("%.1f", Model.Mejoras.EMPLEADOS.getCantidad()*Model.Mejoras.EMPLEADOS.getProdBase()/modelo.getPanchoIdle()*100)) + "%");
                texSUCURSALES.setText("SUCURSALES:      " + String.format("%.1f",(Model.Mejoras.SUCURSALES.getCantidad()*Model.Mejoras.SUCURSALES.getProdBase())) +" p/seg = " + (String.format("%.1f", Model.Mejoras.SUCURSALES.getCantidad()*Model.Mejoras.SUCURSALES.getProdBase()/modelo.getPanchoIdle()*100)) + "%");
            }
        });
    }
}
