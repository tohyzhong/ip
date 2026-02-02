package patrick.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    private DialogBox(String s, Image i, boolean error) {
        this.text = new Label(s);
        this.displayPicture = new ImageView(i);

        // Styling the dialog box
        this.text.setWrapText(true);
        this.displayPicture.setFitWidth(100.0);
        this.displayPicture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);
        if (error) {
            this.text.setTextFill(Color.RED);
        }

        this.getChildren().addAll(this.text, this.displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i, false);
    }

    public static DialogBox getUserDialog(String s, Image i, boolean error) {
        return new DialogBox(s, i, error);
    }

    public static DialogBox getPatrickDialog(String s, Image i) {
        var db = new DialogBox(s, i, false);
        db.flip();
        return db;
    }

    public static DialogBox getPatrickDialog(String s, Image i, boolean error) {
        var db = new DialogBox(s, i, error);
        db.flip();
        return db;
    }
}
