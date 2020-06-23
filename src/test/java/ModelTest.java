package src.test.java;

import javafx.concurrent.Task;
import javafx.stage.Stage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import src.main.java.Controller.Controlador;
import src.main.java.Controller.PanchoIdleTask;
import src.main.java.Model.Model;

@ExtendWith(ApplicationExtension.class)
class ModelTest {
    private Model modelo;

    @Start
    private void start(Stage stage){
        Model modelo = new Model();
        stage.show();
    }
    @Test
    void addPanchoTest() {
        Model modelo = new Model();
        modelo.cleanPanchos();
        modelo.addPancho(1);
        Assertions.assertThat(modelo.getPanchos()).isEqualTo(1);
    }

    @Test
    void comprarMejoraTest(){
        Model modelo = new Model();
        modelo.addPancho(Model.Mejoras.CURSORES.getCosto());
        modelo.comprarMejora(Model.Mejoras.CURSORES);
        Assertions.assertThat(Model.Mejoras.CURSORES.getCantidad()).isEqualTo(1);
    }
    @Test
    void cambioDeNivel2Test(){
        Model modelo = new Model();
        modelo.addPancho(10000);
        modelo.updateLvl();
        Assertions.assertThat(modelo.getLvl()).isEqualTo(Model.Fondos.PLAZA);
    }
    @Test
    void cambioDeNivel3Test(){
        Model modelo = new Model();
        modelo.addPancho(400000);
        modelo.updateLvl();
        Assertions.assertThat(modelo.getLvl()).isEqualTo(Model.Fondos.RESTAURANTE);
    }
    @Test
    void cambioDeNivel4Test(){
        Model modelo = new Model();
        modelo.addPancho(2400000);
        modelo.updateLvl();
        Assertions.assertThat(modelo.getLvl()).isEqualTo(Model.Fondos.CASINO);
    }
    @Test
    void cambioDeNivel5Test(){
        Model modelo = new Model();
        modelo.addPancho(50000000);
        modelo.updateLvl();
        Assertions.assertThat(modelo.getLvl()).isEqualTo(Model.Fondos.ATLANTIS);
    }
    @Test
    void cambioDeNivel6Test(){
        Model modelo = new Model();
        modelo.addPancho(100000000);
        modelo.updateLvl();
        Assertions.assertThat(modelo.getLvl()).isEqualTo(Model.Fondos.ORBITA);
    }
    @Test
    void cambioDeNivel7Test(){
        Model modelo = new Model();
        modelo.addPancho(1000000000);
        modelo.updateLvl();
        Assertions.assertThat(modelo.getLvl()).isEqualTo(Model.Fondos.MARTE);
    }
    @Test
    void panchosPorSegTest(){
        Model modelo = new Model();
        modelo.cleanPanchos();
        Controlador mainController = new Controlador(modelo);
        Task PanchoIdleTask = new PanchoIdleTask(mainController);
        Thread panchoIdle = new Thread(PanchoIdleTask);
        panchoIdle.setDaemon(true);
        panchoIdle.start();
        modelo.addPancho(Model.Mejoras.BEBIDAS.getCosto());
        modelo.comprarMejora(Model.Mejoras.BEBIDAS);
        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertThat(modelo.getPanchos()).isNotZero();
    }
    @Test
    void actualizarNivelMejoraTest(){
        Model modelo = new Model();
        for(int i = 0;i<75; i++ ){
        modelo.addPancho(Model.Mejoras.PAPAS.getCosto());
        modelo.comprarMejora(Model.Mejoras.PAPAS);
        }
        Assertions.assertThat(Model.Mejoras.PAPAS.getNivel()).isEqualTo(2);
    }

}