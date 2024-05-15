package com.example.saisoof;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class TimelineController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private Hyperlink profileLink;

    @FXML
    private Hyperlink addPostLink;

    @FXML
    private void handleProfile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilePage.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("HIVE");
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) profileLink.getScene().getWindow();
            currentStage.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddPost() {

    }
}
