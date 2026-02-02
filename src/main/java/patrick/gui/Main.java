package patrick.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import patrick.Patrick;

public class Main extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image patrickImage = new Image(this.getClass().getResourceAsStream("/images/Patrick.png"));
    private Patrick patrick = new Patrick();

    @Override
    public void start(Stage stage) {
        this.scrollPane = new ScrollPane();
        this.dialogContainer = new VBox();
        this.scrollPane.setContent(this.dialogContainer);

        this.userInput = new TextField();
        this.sendButton = new Button("Send");

        this.initPatrick();

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(this.scrollPane, this.userInput, this.sendButton);

        this.scene = new Scene(mainLayout);

        stage.setScene(this.scene);
        stage.show();

        // Window Formatting
        stage.setTitle(Patrick.BOT_NAME);
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        this.scrollPane.setPrefSize(385, 535);
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        this.scrollPane.setVvalue(1.0);
        this.scrollPane.setFitToWidth(true);

        this.dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        this.userInput.setPrefWidth(325.0);

        this.sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(this.scrollPane, 1.0);

        AnchorPane.setBottomAnchor(this.sendButton, 1.0);
        AnchorPane.setRightAnchor(this.sendButton, 1.0);

        AnchorPane.setLeftAnchor(this.userInput, 1.0);
        AnchorPane.setBottomAnchor(this.userInput, 1.0);

        // Handling User Input
        this.sendButton.setOnMouseClicked((event) -> {
            this.handleUserInput();
        });
        this.userInput.setOnAction((event) -> {
            this.handleUserInput();
        });

        // Scroll down to the end every time dialogContainer's height changes.
        this.dialogContainer.heightProperty().addListener((observable) -> this.scrollPane.setVvalue(1.0));
    }

    private void initPatrick() {
        String patrickText = this.patrick.init();
        this.dialogContainer.getChildren().addAll(
                DialogBox.getPatrickDialog(patrickText, this.patrickImage),
                DialogBox.getPatrickDialog(
                        String.format("Hello! I'm %s.\nWhat can I do for you?", Patrick.BOT_NAME),
                        this.patrickImage));
    }

    private void handleUserInput() {
        String userText = this.userInput.getText();
        this.dialogContainer.getChildren()
                .addAll(DialogBox.getUserDialog(userText, this.userImage));
        this.userInput.clear();
        this.patrick.handleUserInput(userText, this);
    }

    public void display(String str) {
        this.dialogContainer.getChildren()
                .addAll(DialogBox.getPatrickDialog(str, this.patrickImage));
    }

    public void displayError(String error) {
        this.dialogContainer.getChildren()
                .addAll(DialogBox.getPatrickDialog("ERROR! " + error, this.patrickImage, true));
    }

    public void displayParamError(String error) {
        this.dialogContainer.getChildren()
                .addAll(DialogBox.getPatrickDialog("Parameter Failure! " + error, this.patrickImage, true));
    }

    public void endPatrick() {
        Platform.exit();
    }
}
