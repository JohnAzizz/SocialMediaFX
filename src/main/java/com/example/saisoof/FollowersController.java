package com.example.saisoof;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class FollowersController implements Initializable {

    @FXML
    private ScrollPane scrollFollowing;

    VBox allFollowing = new VBox(10);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (UserProfile following : Login.getCurrentUser().getFollowing()){
            ImageView profilePic = new ImageView();
            if (following.getProfilePicture() != null){
                profilePic.setImage(new Image(following.getProfilePicture()));
                profilePic.setFitWidth(75);
                profilePic.setFitHeight(75);
            }


            Label usernameLabel = new Label(following.getUsername());
            usernameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

            HBox details = new HBox(20, profilePic, usernameLabel);
            details.setAlignment(Pos.CENTER);

            allFollowing.getChildren().add(details);

        }

        scrollFollowing.setContent(allFollowing);
    }
}
