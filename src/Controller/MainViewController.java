package src.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import src.Model.*;

public class MainViewController {
    Model modelo;


    public MainViewController(Model modelo){
        this.modelo = modelo;
    }
    public void Pancho_onClick(){
        modelo.addPancho(1);
    }
    public void Cursor_onClick(){
        modelo.comprarMejora(Model.Mejoras.CURSORES);
    }
}
