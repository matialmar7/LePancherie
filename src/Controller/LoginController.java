package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.Main;
import src.Model.Model;


public class LoginController {
    Model modelo;

    @FXML
    private TextField AliasTxt;

    public LoginController(Model mod){
        modelo = mod;
    }


    public void Login_onClick(ActionEvent e){
        Main.getMainView().getStage().show();
        //Main.getLoginView().getStage().close();
        Stage stage = (Stage)AliasTxt.getScene().getWindow();
        stage.close();
    }
}
