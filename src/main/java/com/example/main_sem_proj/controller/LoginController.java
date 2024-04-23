package com.example.main_sem_proj.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController extends MainController {

    public static void start() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Iz.Lumin");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25 ,25));

        //Setting the scene
        Scene scene = new Scene(grid, 350, 200);
        primaryStage.setScene(scene);

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Calibri", 22));
        grid.add(scenetitle, 0,0,2,1);

        Label userName = new Label("Username");
        grid.add(userName, 0, 1);
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label password = new Label("Password:");
        grid.add(password, 0, 2);
        PasswordField passwordBox = new PasswordField();
        grid.add(passwordBox, 1, 2);

        Button signButton = new Button("Sign in");
        HBox Sbutton = new HBox(10);
        Sbutton.setAlignment(Pos.BOTTOM_LEFT);
        Sbutton.getChildren().add(signButton);
        grid.add(Sbutton, 1, 3);

        Button registerButton = new Button("Register");
        HBox Rbutton = new HBox(10);
        Rbutton.setAlignment(Pos.BOTTOM_RIGHT);
        Rbutton.getChildren().add(registerButton);
        grid.add(Rbutton,2,3);


        primaryStage.show();
        // CREATING ONACTION OF SIGNON TO OPEN MAINGUI
        signButton.setOnAction( e ->  {
            MainController.ButtonClick();
            //CLOSES SIGN ON PAGE
            primaryStage.close();
        });
    }
}
