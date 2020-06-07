package src.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import src.Model.*;

public class MainViewController {
    Model modelo;
    //ESTO NO VA, TENDRIA QUE IR EN LA VISTA
    @FXML
    Label Stock_lbl;

    public MainViewController(Model modelo){
        this.modelo = modelo;
    }
    public void Pancho_onClick(){
        modelo.addPancho(1);
        System.out.println(modelo.getPanchos());
        Stock_lbl.setText(String.valueOf(modelo.getPanchos())); //DEBERIA IR EN LA VISTA
    }
}
