package com.example.demo;

import com.example.demo.controller.Controller;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenu {

    private final Stage stage;
    private final Scene scene;

    public MainMenu(Stage stage) {
        this.stage = stage;
        this.scene = initializeScene();
    }

    public Scene getScene() {
        return scene;
    }

    private Scene initializeScene() {
        Pane root = new Pane();

        // Set the background image
        ImageView background = new ImageView(new Image(getClass().getResource("/com/example/demo/images/mainmenu.png").toExternalForm()));
        background.setFitHeight(stage.getHeight());
        background.setFitWidth(stage.getWidth());
        root.getChildren().add(background);

        // Add the title
        ImageView title = new ImageView(new Image(getClass().getResource("/com/example/demo/images/skybattle.png").toExternalForm()));
        title.setFitWidth(500); // Adjust title size
        title.setPreserveRatio(true);
        title.setLayoutX(stage.getWidth() / 2 - 250); // Center horizontally
        title.setLayoutY(50); // Position near the top
        root.getChildren().add(title);

        // Add the Play button
        ImageView playButton = createButton("/com/example/demo/images/play.png", stage.getWidth() / 2 - 150, 200);
        playButton.setOnMouseClicked(e -> launchGame());
        root.getChildren().add(playButton);

        // Add the Level Selection button
        ImageView levelSelectButton = createButton("/com/example/demo/images/levelselection.png", stage.getWidth() / 2 - 150, 300);
        levelSelectButton.setOnMouseClicked(e -> showLevelSelection());
        root.getChildren().add(levelSelectButton);

        // Add the Quit button
        ImageView quitButton = createButton("/com/example/demo/images/quit.png", stage.getWidth() / 2 - 150, 400);
        quitButton.setOnMouseClicked(e -> stage.close());
        root.getChildren().add(quitButton);

        return new Scene(root, stage.getWidth(), stage.getHeight());
    }

    private ImageView createButton(String imagePath, double x, double y) {
        ImageView button = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
        button.setFitWidth(300); // Button width
        button.setPreserveRatio(true); // Maintain aspect ratio
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }

    private void launchGame() {
        try {
            Controller controller = new Controller(stage);
            controller.startLevelOne(); // Starts LevelOne
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showLevelSelection() {
        LevelSelection levelSelection = new LevelSelection(stage);
        stage.setScene(levelSelection.getScene());
    }
}
