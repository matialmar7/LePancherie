package src.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import src.Main;
import src.Model.Model;

public class StatsController {
    Model modelo;

    @FXML
    private Button closeBtn;


    public StatsController(Model mod){
        modelo = mod;
    }

    public void Close_onClick(ActionEvent e){
        Stage stage = (Stage)closeBtn.getScene().getWindow();
        stage.close();
    }

}
