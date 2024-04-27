package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Controller class for the Login/Register view of the application.
 * This class handles the user interactions to login GUIs.
 */
public class LoginController {

    private static String VIEW;

    @FXML
    private CheckBox agreeCheckBox;
    @FXML
    public Button registerButton;

    /**
     * Opens the registration form window.
     */
    public static void Register(Stage stageToClose) {
        try {
            VIEW = "register";
            Stage registerStage = createStage(VIEW,"Register", 450, 300);
            registerStage.show();
            if (stageToClose != null) {
                stageToClose.close(); // Close the stage passed as a parameter
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the login window.
     */
    public static void start(Stage stageToClose) {
        try {
            VIEW = "login";
            Stage loginStage = createStage(VIEW, "Login", 350, 200);
            loginStage.show();
            if (stageToClose != null) {
                stageToClose.close(); // Close the stage passed as a parameter
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Stage createStage(String VIEW, String title, int width, int height) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/" + VIEW +"-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(scene);
        return stage;
    }

    public void handleSignIn(ActionEvent actionEvent) {
        Stage stageToClose = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stageToClose.close();
        MainController.ButtonClick();
    }


    public void handleRegisterUser(ActionEvent actionEvent) {

        handleLoginHyperlink(actionEvent); // route to other method that already goes to register
    }

    public void handleLoginHyperlink(ActionEvent actionEvent) {
        Stage stageToClose = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        start(stageToClose);
    }

    public void handleRegisterHyperlink(ActionEvent actionEvent) {
        Stage stageToClose = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Register(stageToClose);
    }

    /**
     * Handles the action of clicking the agree checkbox.
     * Enables or disables the next button based on the checkbox state.
     */
    @FXML
    public void onAgreeCheckBoxClick() {
        registerButton.setDisable(!agreeCheckBox.isSelected());
    }

}
