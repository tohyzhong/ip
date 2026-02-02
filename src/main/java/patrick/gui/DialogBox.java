package patrick.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle displayCircle;

    private DialogBox(String text, Image img, boolean error) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText(text);
        this.displayCircle.setFill(new javafx.scene.paint.ImagePattern(img));

        if (error) {
            this.dialog.getStyleClass().add("error-label");
        }
    }

    private void flip() {
        this.setAlignment(Pos.BOTTOM_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String s, Image i) {
        var db = new DialogBox(s, i, false);
        db.dialog.getStyleClass().add("user-label");
        return db;
    }

    public static DialogBox getUserDialog(String s, Image i, boolean error) {
        var db = new DialogBox(s, i, error);
        db.dialog.getStyleClass().add("user-label");
        return db;
    }

    public static DialogBox getPatrickDialog(String s, Image i) {
        var db = new DialogBox(s, i, false);
        db.flip();
        db.dialog.getStyleClass().add("patrick-label");
        return db;
    }

    public static DialogBox getPatrickDialog(String s, Image i, boolean error) {
        var db = new DialogBox(s, i, error);
        db.flip();
        db.dialog.getStyleClass().add("patrick-label");
        return db;
    }
}
