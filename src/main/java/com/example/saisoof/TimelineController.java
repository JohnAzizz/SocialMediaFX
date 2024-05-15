package com.example.saisoof;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TimelineController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private Label userNotFoundLabel;

    @FXML
    private Hyperlink profileLink;

    @FXML
    private Hyperlink addPostLink;

    @FXML
    private ScrollPane scrollPosts;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        VBox posts = new VBox(20);

        for(int i = 0; i < Timeline.allposts.size(); i++){
            int index = i;
            Label usernameFLabel = new Label(Timeline.allposts.get(i).getAuthor().getUsername());

            ImageView post = new ImageView(Timeline.allposts.get(i).getUrl());
            post.setFitWidth(200);
            post.setFitHeight(300);

            Label captionLabel = new Label(Timeline.allposts.get(i).getCaption());

            ToggleButton addlikeButton = new ToggleButton("Like");
            Label numberoflikes = new Label(""+Timeline.allposts.get(i).getLikeCount()+ " Likes");
            addlikeButton.setOnAction(e->{
                if (addlikeButton.isSelected() && Timeline.allposts.get(index).Alreadyliked(Timeline.allposts.get(index),Login.getCurrentUser())) {
                    addlikeButton.setText("unlike");
                    Timeline.allposts.get(index).addlike(Timeline.allposts.get(index).getPostId());
                    numberoflikes.setText(""+(Timeline.allposts.get(index).getLikeCount())+" Likes");
                }
                else {
                    addlikeButton.setText("like");
                    Timeline.allposts.get(index).removelike(Timeline.allposts.get(index).getPostId());
                    numberoflikes.setText(""+(Timeline.allposts.get(index).getLikeCount()) + " LiKes");
                }
            });
            HBox likessection = new HBox(20,addlikeButton,numberoflikes);

            TextArea commenTextArea = new TextArea();

            commenTextArea.setPrefHeight(25);
            commenTextArea.setPrefWidth(40);
            commenTextArea.setEditable(true);
            commenTextArea.setStyle("-fx-background-color: #FAFAFA; -fx-border-width: 0px;");
            Label numberofcomments = new Label(""+Timeline.allposts.get(i).getCommentCount()+ " Comments");

            Button addcommButton = new Button("add comment");
            addcommButton.setOnAction(e->{
                if (commenTextArea.getText().isEmpty()){
                }
                else{
                    Timeline.allposts.get(index).addcomment(commenTextArea.getText());
                    numberofcomments.setText(""+ (Timeline.allposts.get(index).getCommentCount())+ " Comments");

                }
            });
            HBox commentssection = new HBox(20,addcommButton,numberofcomments);

            VBox datasection = new VBox(10,usernameFLabel,captionLabel,likessection,commenTextArea,commentssection);
            HBox Npost = new HBox(20,post, datasection);
            posts.getChildren().add(Npost);
        }

        scrollPosts.setContent(posts);
    }

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
