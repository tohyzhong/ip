package patrick.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import patrick.Patrick;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image patrickImage = new Image(this.getClass().getResourceAsStream("/images/Patrick.png"));

    private Patrick patrick;

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    /** Injects the Patrick instance */
    public void setPatrick(Patrick p) {
        this.patrick = p;
        this.initPatrick();
    }

    @FXML
    private void handleUserInput() {
        String userText = this.userInput.getText();
        this.dialogContainer.getChildren()
                .addAll(DialogBox.getUserDialog(userText, this.userImage));
        this.userInput.clear();
        this.patrick.handleUserInput(userText, this);
    }

    @FXML
    public void display(String str) {
        this.dialogContainer.getChildren()
                .addAll(DialogBox.getPatrickDialog(str, this.patrickImage));
    }

    @FXML
    public void displayError(String error) {
        this.dialogContainer.getChildren()
                .addAll(DialogBox.getPatrickDialog("ERROR! " + error, this.patrickImage, true));
    }

    @FXML
    public void displayParamError(String error) {
        this.dialogContainer.getChildren()
                .addAll(DialogBox.getPatrickDialog("Parameter Failure! " + error, this.patrickImage, true));
    }

    @FXML
    public void endPatrick() {
        Platform.exit();
    }

    @FXML
    private void initPatrick() {
        String patrickText = this.patrick.init();
        this.dialogContainer.getChildren().addAll(
                DialogBox.getPatrickDialog(patrickText, this.patrickImage),
                DialogBox.getPatrickDialog(
                        String.format("Hello! I'm %s.\nWhat can I do for you?", Patrick.BOT_NAME),
                        this.patrickImage));
    }
}
