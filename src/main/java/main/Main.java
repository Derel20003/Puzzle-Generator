package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application {

    private final Pane lineField = new Pane();

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setPrefSize(1000, 600);

        lineField.setPrefSize(1000, 500);

        Pane fieldControls = new Pane();
        fieldControls.setPrefSize(1000, 100);
        fieldControls.setLayoutY(500);
        fieldControls.setStyle("-fx-background-color: grey");

        Spinner<Integer> lineCount = new Spinner<>(0, 9, 0, 1);
        lineCount.setOnMouseClicked(event -> drawLines(lineCount.getValue()));
        lineCount.setLayoutX(450);
        lineCount.setLayoutY(40);

        fieldControls.getChildren().add(lineCount);
        root.getChildren().addAll(lineField, fieldControls);

        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Puzzle-Generator V1");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void drawLines(int lineCount){
        lineField.getChildren().clear();
        int fieldHeight = (int) lineField.getHeight();
        int fieldWidth = (int) lineField.getWidth();
        int stepsX = fieldHeight / (lineCount + 1);
        int stepsY = fieldWidth / (lineCount + 1);
        for (int i = 1; i <= lineCount; i++){
            Line newLineX = new Line(lineField.getLayoutX(), stepsX, lineField.getWidth() + lineField.getLayoutX(), stepsX);
            Line newLineY = new Line(stepsY, lineField.getLayoutY(), stepsY, lineField.getHeight() + lineField.getLayoutX());
            lineField.getChildren().addAll(newLineX, newLineY);
            stepsX += stepsX / i;
            stepsY += stepsY / i;
        }
    }
}
