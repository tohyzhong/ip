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

/**
 * A custom control representing a dialog box consisting of a profile picture,
 * and a text label.
 * Factory methods help create dialogs for both user and Patrick, with specific
 * styling based on message type.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle displayCircle;

    /**
     * Private constructor to initialize the dialog box.
     * Loads the FXML layout and sets the speaker's image and text.
     *
     * @param text  The message to display.
     * @param img   The image of the speaker.
     * @param error True if the message should be styled as an error.
     */
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

    /**
     * Flips the dialog box for messages sent by Patrick, to distinguish between
     * user and bot messages.
     */
    private void flip() {
        this.setAlignment(Pos.BOTTOM_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Factory method to create a dialog box for the user.
     *
     * @param str The user's input text.
     * @param img The user's profile image.
     * @return A {@code DialogBox} configured for the user.
     */
    public static DialogBox createUserDialog(String str, Image img) {
        var db = new DialogBox(str, img, false);
        db.dialog.getStyleClass().add("user-label");
        return db;
    }

    /**
     * Factory method to create a dialog box for the user.
     *
     * @param str   The user's input text.
     * @param img   The user's profile image.
     * @param error Whether to apply the error CSS class.
     * @return A {@code DialogBox} configured for the user.
     */
    public static DialogBox createUserDialog(String str, Image img, boolean error) {
        var db = new DialogBox(str, img, error);
        db.dialog.getStyleClass().add("user-label");
        return db;
    }

    /**
     * Factory method to create a dialog box for Patrick.
     *
     * @param str Patrick's output text.
     * @param img Patrick's profile image.
     * @return A {@code DialogBox} configured for the user.
     */
    public static DialogBox createPatrickDialog(String str, Image img) {
        var db = new DialogBox(str, img, false);
        db.flip();
        db.dialog.getStyleClass().add("patrick-label");
        return db;
    }

    /**
     * Factory method to create a dialog box for Patrick.
     *
     * @param str   Patrick's output text.
     * @param img   Patrick's profile image.
     * @param error Whether to apply the error CSS class.
     * @return A {@code DialogBox} configured for the user.
     */
    public static DialogBox createPatrickDialog(String str, Image img, boolean error) {
        var db = new DialogBox(str, img, error);
        db.flip();
        db.dialog.getStyleClass().add("patrick-label");
        return db;
    }
}
