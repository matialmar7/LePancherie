package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.Main;
import src.Model.Model;


public class LoginController {
    Model modelo;

    @FXML
    private TextField AliasTxt;
    @FXML
    private Button closeBtn;
    public LoginController(Model mod){
        modelo = mod;
    }


    public void Login_onClick(ActionEvent e){
        if(AliasTxt.getText().isEmpty()){
            Alert  alerta = new Alert(Alert.AlertType.INFORMATION,"Debes ingresar tu nombre", ButtonType.CLOSE);
            alerta.setHeaderText(null);
            alerta.setTitle("Alerta");
            alerta.show();
        }
        else{
            Main.getMainView().getStage().show();
            Stage stage = (Stage)AliasTxt.getScene().getWindow();
            stage.close();
        }

    }
    public void Close_onClick(ActionEvent e){
        Stage stage = (Stage)closeBtn.getScene().getWindow();
        stage.close();
    }

}
