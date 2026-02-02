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

/**
 * Controller for MainWindow. Provides the layout for other controls.
 * This class handles the primary user interface interactions, including user
 * input, and displaying dialogs.
 */
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

    /**
     * Sets up autoscrolling in the GUI.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    /** Injects the Patrick instance */
    public void setPatrick(Patrick p) {
        this.patrick = p;
        this.initPatrick();
    }

    /**
     * Captures user text, renders it in the GUI, clears the input field, and passes
     * the raw string to the logic controller.
     */
    @FXML
    private void handleUserInput() {
        String userText = this.userInput.getText();
        this.dialogContainer.getChildren()
                .addAll(DialogBox.getUserDialog(userText, this.userImage));
        this.userInput.clear();
        this.patrick.handleUserInput(userText, this);
    }

    /**
     * Renders a standard response from Patrick in the dialog container.
     *
     * @param str The output string processed by Patrick.
     */
    @FXML
    public void display(String str) {
        this.dialogContainer.getChildren()
                .addAll(DialogBox.getPatrickDialog(str, this.patrickImage));
    }

    /**
     * Displays a specialised error message regarding any application errors.
     *
     * @param error The descriptive error message indicating what was wrong.
     */
    @FXML
    public void displayError(String error) {
        this.dialogContainer.getChildren()
                .addAll(DialogBox.getPatrickDialog("ERROR! " + error, this.patrickImage, true));
    }

    /**
     * Displays a specialised error message regarding invalid Parameters.
     *
     * @param error The descriptive error message indicating what was wrong.
     */
    @FXML
    public void displayParamError(String error) {
        this.dialogContainer.getChildren()
                .addAll(DialogBox.getPatrickDialog("Parameter Failure! " + error, this.patrickImage, true));
    }

    /**
     * Triggers a clean shutdown of the JavaFX App.
     */
    @FXML
    public void endPatrick() {
        Platform.exit();
    }

    /**
     * Initiates an instance of Patrick and outputs the initialisation status and
     * welcome message.
     */
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
