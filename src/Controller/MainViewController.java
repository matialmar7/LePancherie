package src.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import src.Model.*;

public class MainViewController {
    Model modelo;


    public MainViewController(Model modelo){
        this.modelo = modelo;
    }

    //METODOS BOTONES MEJORAS
    public void Cursor_onClick(){
        modelo.comprarMejora(Model.Mejoras.CURSORES);
    }
    public void Condimentos_onClick(){
        modelo.comprarMejora(Model.Mejoras.CONDIMENTOS);
    }
    public void Papas_onClick(){
        modelo.comprarMejora(Model.Mejoras.PAPAS);
    }
    public void Bebidas_onClick(){
        modelo.comprarMejora(Model.Mejoras.BEBIDAS);
    }
    public void Parrillas_onClick(){
        modelo.comprarMejora(Model.Mejoras.PARRILLAS);
    }
    public void Salchichera_onClick(){
        modelo.comprarMejora(Model.Mejoras.SALCHICHERA);
    }
    public void Heladeras_onClick(){
        modelo.comprarMejora(Model.Mejoras.HELADERA);
    }
    public void Caja_onClick(){
        modelo.comprarMejora(Model.Mejoras.CAJA_REGISTRADORA);
    }
    public void Empleados_onClick(){
        modelo.comprarMejora(Model.Mejoras.EMPLEADOS);
    }
    public void Sucursales_onClick(){
        modelo.comprarMejora(Model.Mejoras.SUCURSALES);
    }

    //METODOS PARA AÑADIR PANCHOS
    public void Pancho_onClick(){
        modelo.addPanchoClick(1);
    }
    public void addPanchoIdle(){
        modelo.addPancho(modelo.getPanchoIdle());
    }
    //METODOS VARIOS
    public void printRandomMensaje(){
        modelo.setMensaje(modelo.getRandomMessage());
    }
}
