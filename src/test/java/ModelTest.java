package src.test.java;

import javafx.stage.Stage;
import org.assertj.core.api.Assertions;
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
    void addPanchoTest() {
        modelo.addPancho(1);
        Assertions.assertThat(modelo.getPanchos()).isEqualTo(1);
    }

    @Test
    void comprarMejoraTest(){
        modelo.addPancho(Model.Mejoras.CURSORES.getCosto());
        modelo.comprarMejora(Model.Mejoras.CURSORES);
        Assertions.assertThat(Model.Mejoras.CURSORES.getCantidad()).isEqualTo(1);
    }

}