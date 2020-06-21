package src;

import javafx.application.Application;
import src.Controller.Controlador;
import src.Model.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Main {
    public static Model modelo;
    public static Controlador controlador;

    public static void main(String[] args) {


        modelo = new Model();
        //modelo.inicializarMejoras();
        controlador = new Controlador(modelo);

        Application.launch(src.View.MainView.class  , args);
        //System.out.println("hola");
    }


}
