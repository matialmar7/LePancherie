package src.View;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import src.Controller.Controlador;
import src.Model.Model;
import src.Model.Observador;

import java.io.IOException;

public class MainView implements Observador {
    private FXMLLoader loader;
    private Stage primaryStage;
    private Model modelo;
    private Controlador controlador;

    //ELEMENTOS DE INTERFAZ A UPDATEAR
    private Label msjBanner;
    //Mostrador de stock y panchos/s
    private Label stockLbl;
    private Label panchosLbl;
    //Cantidad de mejoras
    private Label cursorLbl;
    private Label condimentosLbl;
    private Label papasLbl;
    private Label bebidasLbl;
    private Label parrillasLbl;
    private Label salchicherasLbl;
    private Label heladerasLbl;
    private Label cajaLbl;
    private Label empleadosLbl;
    private Label sucursalesLvl;
    //Costo de mejoras
    private Label cursorCostLbl;
    private Label condimenttosCostLbl;
    private Label papasCostLbl;
    private Label bebidasCostLbl;
    private Label parrillasCostLbl;
    private Label salchicherasCostLbl;
    private Label heladerasCostLbl;
    private Label cajaCostLbl;
    private Label empleadosCostLbl;
    private Label sucursalesCostLbl;
    //Botones
    private Button Cursores;
    private Button Condimentos;
    private Button Papas;
    private Button Bebidas;
    private Button Parrillas;
    private Button Salchicheras;
    private Button Heladeras;
    private Button Caja;
    private Button Empleados;
    private Button Sucursales;



    public MainView(Model mod, Controlador cont) throws IOException {
        modelo = mod;
        controlador = cont;

        //Cargo ventana principal
        loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        loader.setController(controlador);
        Parent root = loader.load();

        //SUBSCRIBO OBSERVADOR
        modelo.addObservador(this);

        Scene scene = new Scene(root);

        //TOMO REFERENCIAS DE OBJETOS A ACTUALIZAR
        //Principales
        msjBanner = (Label) scene.lookup("#msjBanner");
        stockLbl = (Label) scene.lookup("#StockLbl");
        panchosLbl= (Label) scene.lookup("#panchosLbl");
        //Cantidad de mejoras
        cursorLbl= (Label) scene.lookup("#cursorLbl");
        condimentosLbl= (Label) scene.lookup("#condimentosLbl");
        papasLbl= (Label) scene.lookup("#papasLbl");
        bebidasLbl= (Label) scene.lookup("#bebidasLbl");
        parrillasLbl= (Label) scene.lookup("#parrillasLbl");
        salchicherasLbl= (Label) scene.lookup("#salchicherasLbl");
        heladerasLbl= (Label) scene.lookup("#heladerasLbl");
        cajaLbl= (Label) scene.lookup("#cajaLbl");
        empleadosLbl = (Label) scene.lookup("#empleadosLbl");
        sucursalesLvl= (Label) scene.lookup("#sucursalesLvl");
        //Costo de mejoras
        cursorCostLbl= (Label) scene.lookup("#cursorCostLbl");
        condimenttosCostLbl= (Label) scene.lookup("#condimenttosCostLbl");
        papasCostLbl= (Label) scene.lookup("#papasCostLbl");
        bebidasCostLbl= (Label) scene.lookup("#bebidasCostLbl");
        parrillasCostLbl= (Label) scene.lookup("#parrillasCostLbl");
        salchicherasCostLbl= (Label) scene.lookup("#salchicherasCostLbl");
        heladerasCostLbl= (Label) scene.lookup("#heladerasCostLbl");
        cajaCostLbl= (Label) scene.lookup("#cajaCostLbl");
        empleadosCostLbl= (Label) scene.lookup("#empleadosCostLbl");
        sucursalesCostLbl= (Label) scene.lookup("#sucursalesCostLbl");
        //Botones
        Cursores = (Button) scene.lookup("#Cursores");
        Condimentos = (Button) scene.lookup("#Condimentos");
        Papas = (Button) scene.lookup("#Papas");
        Bebidas = (Button) scene.lookup("#Bebidas");
        Parrillas = (Button) scene.lookup("#Parrillas");
        Salchicheras = (Button) scene.lookup("#Salchicheras");
        Heladeras = (Button) scene.lookup("#Heladeras");
        Caja = (Button) scene.lookup("#Caja");
        Empleados = (Button) scene.lookup("#Empleados");
        Sucursales = (Button) scene.lookup("#Sucursales");

        //Inicializo los valores en las labels
        updateLabels();
        updateMejoras();

        scene.getStylesheets().add(getClass().getResource("css/Interfaz.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("css/Texto.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("css/Mejoras.css").toExternalForm());
        primaryStage = new Stage();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Le Pancherie");
        primaryStage.setScene(scene);
        Image icono = new Image("Res/Interfaz/appIcon.png");
        primaryStage.getIcons().add(icono);
        primaryStage.initStyle(StageStyle.UNDECORATED);
    }

    @Override
    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                updateLabels();
                updateMejoras();
                updateFondo();
            }
        });
    }
    public Stage getStage(){
        return primaryStage;
    }
    private void updateLabels(){
        //ACTUALIZO LABELS STOCKS, PANCHOS/S, MEJORAS
        msjBanner.setText(modelo.getMensaje());
        stockLbl.setText(String.valueOf(modelo.getPanchos()));
        panchosLbl.setText(String.format("%.1f", modelo.getPanchoIdle()) + " panchos/s");
        //Cantidades
        cursorLbl.setText(Integer.toString(Model.Mejoras.CURSORES.getCantidad()));
        condimentosLbl.setText(Integer.toString(Model.Mejoras.CONDIMENTOS.getCantidad()));
        papasLbl.setText(Integer.toString(Model.Mejoras.PAPAS.getCantidad()));
        bebidasLbl.setText(Integer.toString(Model.Mejoras.BEBIDAS.getCantidad()));
        parrillasLbl.setText(Integer.toString(Model.Mejoras.PARRILLAS.getCantidad()));
        salchicherasLbl.setText(Integer.toString(Model.Mejoras.SALCHICHERA.getCantidad()));
        heladerasLbl.setText(Integer.toString(Model.Mejoras.HELADERA.getCantidad()));
        cajaLbl.setText(Integer.toString(Model.Mejoras.CAJA_REGISTRADORA.getCantidad()));
        empleadosLbl.setText(Integer.toString(Model.Mejoras.EMPLEADOS.getCantidad()));
        sucursalesLvl.setText(Integer.toString(Model.Mejoras.SUCURSALES.getCantidad()));
        //Costos
        cursorCostLbl.setText(Integer.toString(Model.Mejoras.CURSORES.getCosto()));
        condimenttosCostLbl.setText(Integer.toString(Model.Mejoras.CONDIMENTOS.getCosto()));
        papasCostLbl.setText(Integer.toString(Model.Mejoras.PAPAS.getCosto()));
        bebidasCostLbl.setText(Integer.toString(Model.Mejoras.BEBIDAS.getCosto()));
        parrillasCostLbl.setText(Integer.toString(Model.Mejoras.PARRILLAS.getCosto()));
        salchicherasCostLbl.setText(Integer.toString(Model.Mejoras.SALCHICHERA.getCosto()));
        heladerasCostLbl.setText(Integer.toString(Model.Mejoras.HELADERA.getCosto()));
        cajaCostLbl.setText(Integer.toString(Model.Mejoras.CAJA_REGISTRADORA.getCosto()));
        empleadosCostLbl.setText(Integer.toString(Model.Mejoras.EMPLEADOS.getCosto()));
        sucursalesCostLbl.setText(Integer.toString(Model.Mejoras.SUCURSALES.getCosto()));

    }
    private void updateMejoras(){
        Cursores.setStyle(String.format("-fx-background-image: url(%s);", Model.Mejoras.CURSORES.getMejoraUrl()));
        Condimentos.setStyle(String.format("-fx-background-image: url(%s);", Model.Mejoras.CONDIMENTOS.getMejoraUrl()));
        Papas.setStyle(String.format("-fx-background-image: url(%s);", Model.Mejoras.PAPAS.getMejoraUrl()));
        Bebidas.setStyle(String.format("-fx-background-image: url(%s);", Model.Mejoras.BEBIDAS.getMejoraUrl()));
        Parrillas.setStyle(String.format("-fx-background-image: url(%s);", Model.Mejoras.PARRILLAS.getMejoraUrl()));
        Salchicheras.setStyle(String.format("-fx-background-image: url(%s);", Model.Mejoras.SALCHICHERA.getMejoraUrl()));
        Heladeras.setStyle(String.format("-fx-background-image: url(%s);", Model.Mejoras.HELADERA.getMejoraUrl()));
        Caja.setStyle(String.format("-fx-background-image: url(%s);", Model.Mejoras.CAJA_REGISTRADORA.getMejoraUrl()));
        Empleados.setStyle(String.format("-fx-background-image: url(%s);", Model.Mejoras.EMPLEADOS.getMejoraUrl()));
        Sucursales.setStyle(String.format("-fx-background-image: url(%s);", Model.Mejoras.SUCURSALES.getMejoraUrl()));
    }
    private void updateFondo(){
        System.out.println("estoy cambiando el fondo");
    }
}

