package src.View;
import src.Controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Observable;
import java.util.Observer;

public class MainView extends Application implements Observer {

    private MainViewController controlador;

    public MainView(MainViewController controlador){

    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}