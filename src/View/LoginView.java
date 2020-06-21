package src.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.Controller.Controlador;
import src.Controller.LoginController;
import src.Model.Model;

import java.io.IOException;

public class LoginView {
    Model modelo;
    LoginController controlador;
    FXMLLoader loader;
    Scene loginScene;
    Stage loginStage;

    public LoginView(Model mod, LoginController cont) throws IOException {
        modelo  = mod;
        controlador = cont;

        //Cargo ventana login
        loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
        loader.setController(controlador);
        Parent login = loader.load();
        loginScene = new Scene(login);
        loginScene.getStylesheets().add(getClass().getResource("css/Texto.css").toExternalForm());
        loginScene.getStylesheets().add(getClass().getResource("css/Login.css").toExternalForm());
        loginStage = new Stage();
        loginStage.setResizable(false);
        loginStage.setTitle("LePancherie - Login");
        loginStage.setScene(loginScene);
        loginStage.initModality(Modality.APPLICATION_MODAL);
    }

    public Stage getStage(){
        return loginStage;
    }
}
