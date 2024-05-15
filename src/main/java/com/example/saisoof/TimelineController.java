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
    private TextField usernameField;

    @FXML
    private Label userNotFoundLabel;

    @FXML
    private Hyperlink profileLink;

    @FXML
    private Hyperlink addPostLink;

    @FXML
    public void handleProfile() {
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
    public void handleAddPost() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPostPage.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("HIVE");
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) addPostLink.getScene().getWindow();
            currentStage.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleFollow(){
        if (Main.getProfileUsingUsername(usernameField.getText()) != null){
            if (Login.getCurrentUser().getFollowing().contains(Main.getProfileUsingUsername(usernameField.getText()))){
                userNotFoundLabel.setText("Already Followed");
            }
            else{
                Login.getCurrentUser().send_follow(usernameField.getText().toLowerCase());
                userNotFoundLabel.setText("");
            }
        }
        else{
            userNotFoundLabel.setText("User not found");
        }
    }
}
