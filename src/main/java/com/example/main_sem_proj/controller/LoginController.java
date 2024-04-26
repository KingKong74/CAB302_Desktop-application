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

    /**
     * Set
     */
    public static void Register() {
        Stage registerScreen = new Stage();
        registerScreen.setTitle("Iz.Lumin");
        registerScreen.setResizable(false);
        GridPane Regi = new GridPane();
        Regi.setAlignment(Pos.CENTER);
        Regi.setHgap(10);
        Regi.setVgap(10);
        Regi.setPadding(new Insets(25, 25, 25, 25));

        Button Confirm = new Button("Confirm");

        Scene scene = new Scene(Regi, 350, 150 );
        registerScreen.setScene(scene);

        Text scenetitle = new Text("REGISTER FORM");
        scenetitle.setFont(Font.font("Calibri", 22));
        Regi.add(scenetitle, 0, 0, 2, 1);

        Label Email = new Label("Email:");
        Regi.add(Email,0,1);
        TextField emailTextField = new TextField();
        Regi.add(emailTextField, 1, 1);

        Label Username = new Label("Username:");
        Regi.add(Username, 0, 2);
        TextField usernameTextField = new TextField();
        Regi.add(usernameTextField, 1, 2);

        Label Password = new Label("Password:");
        Regi.add(Password, 0, 3);
        TextField passwordTextField = new TextField();
        Regi.add(passwordTextField, 1, 3);

        HBox registration = new HBox(10);
        registration.setAlignment(Pos.CENTER);
        registration.getChildren().add(Confirm);
        Regi.add(registration, 3,3);

        registerScreen.show();

        Confirm.setOnAction( e -> {
            registerScreen.close();
        });

    }

    /**
     * Starts
     */
    public static void start() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Iz.Lumin");
        primaryStage.setResizable(false);
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
        Button registerButton = new Button("Register");

        HBox Sbutton = new HBox(10);
        Sbutton.setAlignment(Pos.BOTTOM_LEFT);
        Sbutton.getChildren().add(signButton);
        grid.add(Sbutton, 1, 3);

        HBox Rbutton = new HBox(10);
        Rbutton.setAlignment(Pos.BOTTOM_LEFT);
        Rbutton.getChildren().add(registerButton);
        grid.add(Rbutton, 2, 3);
        primaryStage.show();

        registerButton.setOnAction(e -> {
            Register();
        });
        // CREATING ONACTION OF SIGNON TO OPEN MAINGUI
        signButton.setOnAction( e ->  {
            MainController.ButtonClick();
            //CLOSES SIGN ON PAGE
            primaryStage.close();
        });



    }
}
