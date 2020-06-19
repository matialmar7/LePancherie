package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import src.Model.Model;

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

    //METODOS CURSORES
    @FXML
    public void Cursor_onClick(ActionEvent event){
        modelo.comprarMejora(Model.Mejoras.CURSORES);
    }

}
