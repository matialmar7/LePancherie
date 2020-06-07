package src.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {
    FXMLLoader loader;

    @Override
    public void start(Stage primaryStage) throws Exception {

        loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Interfaz.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("Texto.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("Mejoras.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.setTitle("Le Pancherie");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
