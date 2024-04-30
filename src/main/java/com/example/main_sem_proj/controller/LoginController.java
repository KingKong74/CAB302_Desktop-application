package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.HelloApplication;
import com.example.main_sem_proj.model.SqliteUserDAO;
import com.example.main_sem_proj.model.User;
import com.example.main_sem_proj.model.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.main_sem_proj.controller.MainController.setStagePosition;

/**
 * The Controller class for the Login/Register view of the application.
 * This class handles the user interactions to login GUIs.
 */
public class LoginController {


    Validation validation = new Validation();

    private static String VIEW;

    SqliteUserDAO userDAO = new SqliteUserDAO();

    // Default constructor
    public LoginController() {
        // can initialize default values here
    }

    public Hyperlink GuestButton;
    public Button signInButton;
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private CheckBox agreeCheckBox;

    @FXML
    private Button registerButton;

    @FXML
    private Label errorMessageLabel;

    @FXML
    public TextField usernameField;

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

    @FXML
    public void handleSignIn(ActionEvent actionEvent) {
        try {
            String email = usernameField.getText();
            String password = passwordField.getText();

            // Validate email and password
            if (email.isEmpty() || password.isEmpty()) {
                errorMessageLabel.setText("Please enter your email and password");
                return;
            }

            // Authenticate user
            User user = userDAO.getUser(email, password);
            if (user != null) {
                Stage stageToClose = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stageToClose.close();
                Stage stage = (Stage) signInButton.getScene().getWindow();
                mainGUI(stage, "Welcome " + user.getFirstName() + " " + user.getLastName() + "!");
            } else {
                errorMessageLabel.setText("Invalid email or password");
            }
        } catch (Exception e) {
            errorMessageLabel.setText("An error occurred. Please try again later.");
        }
    }


    /**
     * Handles the registration of a new user.
     * This method is invoked when the register button is clicked.
     */
    @FXML
    public void handleRegisterUser(ActionEvent actionEvent) {
        // Retrieve data from text fields
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        boolean agreeToTerms = agreeCheckBox.isSelected();

        // Validate user input
        if (!password.equals(confirmPassword)) {
            errorMessageLabel.setText("Passwords do not match");
            return;
        }

        if (!agreeToTerms) {
            errorMessageLabel.setText("Please agree to the terms and conditions");
            return;
        }

        boolean EmailValidityCheck = false;
        EmailValidityCheck = validation.ValidateEmail(email);
        if (!EmailValidityCheck){
            errorMessageLabel.setText("Invalid Email");
            return;
        }
        if(userDAO.CheckEmailTaken(email)){
            errorMessageLabel.setText("Email has been taken");
            return;
        }


        userDAO.addUser(new User(email, firstName, lastName, password ));

        handleLoginHyperlink(actionEvent);
    }

    public void handleLoginHyperlink(ActionEvent actionEvent) {
        Stage stageToClose = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        start(stageToClose);
    }

    public void handleRegisterHyperlink(ActionEvent actionEvent) {
        Stage stageToClose = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Register(stageToClose);
    }

    public void handleGuestHyperlink(ActionEvent actionEvent) {
        Stage stageToClose = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stageToClose.close();
        Stage stage = (Stage) GuestButton.getScene().getWindow();
        mainGUI(stage, "Welcome Guest!" );
    }

    /**
     * Handles the action of clicking the agree checkbox.
     * Enables or disables the next button based on the checkbox state.
     */
    @FXML
    public void onAgreeCheckBoxClick() {
        registerButton.setDisable(!agreeCheckBox.isSelected());
    }

    /**
     * Sets up and displays the main graphical user interface (GUI) for the application within the provided stage.
     * This method loads the main-view.fxml file, creates a scene, configures the stage, positions it on the screen,
     * and shows it to the user.
     *
     * @param stage The stage in which the main GUI will be displayed.
     */
    public void mainGUI(Stage stage, String welcomeMessage) {
        final String TITLE = "Iz.Lumin";
        final int WIDTH = 580;
        final int HEIGHT = 280;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/main_sem_proj/view/main-view.fxml"));
            Parent root = fxmlLoader.load();

            MainController mainController = fxmlLoader.getController();
            mainController.setWelcomeLabel(welcomeMessage);

            Scene scene = new Scene(root, WIDTH, HEIGHT);
            stage.setScene(scene);
            stage.setTitle(TITLE);
            stage.setResizable(false);
            setStagePosition(stage, WIDTH, HEIGHT);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
