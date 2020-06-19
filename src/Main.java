package src;

import javafx.application.Application;
import src.Controller.Controlador;
import src.Model.Model;

public class Main {
    public static Model modelo;
    public static Controlador controlador;

    public static void main(String[] args) {
        modelo = new Model();
        controlador = new Controlador(modelo);

        Application.launch(src.View.MainView.class  , args);
    }


}
