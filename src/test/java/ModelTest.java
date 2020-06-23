package src.test.java;

import javafx.stage.Stage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import src.main.java.Model.Model;

@ExtendWith(ApplicationExtension.class)
class ModelTest {
    private Model modelo;
    @Start
    private void start(Stage stage){
        modelo = new Model();
        stage.show();
    }
    @Test
    void addPancho() {
        modelo.addPancho(1);
        Assertions.assertEquals(1,modelo.getPanchos());
    }
}