package patrick.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import patrick.Patrick;

public class Main extends Application {
    private Patrick patrick = new Patrick();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(640);
            stage.setMinWidth(410);
            fxmlLoader.<MainWindow>getController().setPatrick(this.patrick);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
