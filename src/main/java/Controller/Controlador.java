package src.main.java.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import src.main.java.Main;
import src.main.java.Model.Model;

public class Controlador {
    Model modelo;

    public Controlador(Model mod){
        modelo = mod;
    }

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
    //Menu
    @FXML
    private Button closeBtn;
    @FXML
    private Button statsBtn;

    //METODOS CURSORES

    public void Cursor_onClick(ActionEvent event){
        modelo.comprarMejora(Model.Mejoras.CURSORES);
    }
    public void Condimentos_onClick(ActionEvent event){
        modelo.comprarMejora(Model.Mejoras.CONDIMENTOS);
    }
    public void Papas_onClick(ActionEvent event){
        modelo.comprarMejora(Model.Mejoras.PAPAS);
    }
    public void Bebidas_onClick(ActionEvent event){
        modelo.comprarMejora(Model.Mejoras.BEBIDAS);
    }
    public void Parrillas_onClick(ActionEvent event){
        modelo.comprarMejora(Model.Mejoras.PARRILLAS);
    }
    public void Salchichera_onClick(ActionEvent event){
        modelo.comprarMejora(Model.Mejoras.SALCHICHERA);
    }
    public void Heladeras_onClick(ActionEvent event){
        modelo.comprarMejora(Model.Mejoras.HELADERA);
    }
    public void Caja_onClick(ActionEvent event){
        modelo.comprarMejora(Model.Mejoras.CAJA_REGISTRADORA);
    }
    public void Empleados_onClick(ActionEvent event){
        modelo.comprarMejora(Model.Mejoras.EMPLEADOS);
    }
    public void Sucursales_onClick(ActionEvent event){
        modelo.comprarMejora(Model.Mejoras.SUCURSALES);
    }

    //METODOS PRINCIPALES
    public void Pancho_onClick(){
        modelo.addPanchoClick(1);
    }
    public void addPanchoIdle(){
        modelo.addPancho(modelo.getPanchoIdle()/10);
    }
    //METODOS VARIOS
    public void printRandomMensaje(){
        modelo.setMensaje(modelo.getRandomMessage());
    }
    //METODOS MENU
    public void Close_onClick(ActionEvent e){
        modelo.savePlayerData();
        Stage stage = (Stage)closeBtn.getScene().getWindow();
        stage.close();
    }
    public void Stats_onClick(ActionEvent e){
        Main.getStatsView().getStage().show();
    }
}
