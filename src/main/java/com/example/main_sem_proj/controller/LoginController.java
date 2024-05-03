package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.model.SqliteUserDAO;
import com.example.main_sem_proj.model.User;
import com.example.main_sem_proj.model.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Controller class for the Login/Registration view of the application.
 * This class handles the user interactions to login GUIs.
 */
public class LoginController extends StageController{
    static StageController stageController = new StageController();
    private final Validation validation = new Validation();
    private final SqliteUserDAO userDAO = new SqliteUserDAO();
    @FXML private Label errorMessageLabel;
    @FXML private Button registerButton;
    @FXML private CheckBox agreeCheckBox;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private PasswordField confirmPasswordField;

    //
    // User login
    //

    @FXML
    public void handleSignIn(ActionEvent actionEvent) {
        try {
            String email = usernameField.getText();
            String password = passwordField.getText();

            // Validate email and password
            if (!validateEmailAndPassword(email, password)) {
                setErrorMessage("Please enter your email and password");
                return;
            }

            // Authenticate user
            User user = authenticateUser(email, password);
            if (user != null) {
                Stage stageToClose = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                openMainGUI(stageToClose, "Welcome " + user.getFirstName() + " " + user.getLastName() + "!");
            } else {
                setErrorMessage("Invalid email or password");
            }
        } catch (Exception e) {
            setErrorMessage("An error occurred. Please try again later.");
        }
    }

    /**
     * Validates the entered email and password.
     *
     * @param email    The email to be validated.
     * @param password The password to be validated.
     * @return True if both email and password are valid, otherwise false.
     */
    private boolean validateEmailAndPassword(String email, String password) {
        return !(email.isEmpty() || password.isEmpty());
    }

    /**
     * Authenticates the user with the provided email and password.
     *
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return The authenticated user if found, otherwise null.
     */
    private User authenticateUser(String email, String password) {
        return userDAO.getUser(email, password);
    }

    //
    // User Register
    //

    @FXML public void onAgreeCheckBoxClick() {
        registerButton.setDisable(!agreeCheckBox.isSelected());
    }

    /**
     * Handles the registration of a new user.
     * This method is invoked when the register button is clicked.
     */
    @FXML
    public void handleRegisterUser(ActionEvent actionEvent) throws IOException {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        boolean agreeToTerms = agreeCheckBox.isSelected();

        if (!password.equals(confirmPassword)) {
            setErrorMessage("Passwords do not match");
            return;
        }

        if (!agreeToTerms) {
            setErrorMessage("Please agree to the terms and conditions");
            return;
        }

        if (!validateEmail(email)) {
            setErrorMessage("Invalid Email");
            return;
        }

        if (isEmailTaken(email)) {
            setErrorMessage("Email has been taken");
            return;
        }

        addUser(email, firstName, lastName, password);
        handleLoginHyperlink(actionEvent);
    }

    /**
     * Validates the entered email.
     *
     * @param email The email to be validated.
     * @return True if the email is valid, otherwise false.
     */
    private boolean validateEmail(String email) {
        return validation.ValidateEmail(email);
    }

    /**
     * Checks if the entered email is already taken by another user.
     *
     * @param email The email to be checked.
     * @return True if the email is already taken, otherwise false.
     */
    private boolean isEmailTaken(String email) {
        return userDAO.CheckEmailTaken(email);
    }

    /**
     * Adds a new user with the provided details.
     *
     * @param email     The email of the new user.
     * @param firstName The first name of the new user.
     * @param lastName  The last name of the new user.
     * @param password  The password of the new user.
     */
    private void addUser(String email, String firstName, String lastName, String password) {
        userDAO.addUser(new User(email, firstName, lastName, password));
    }

    //
    // Methods related to GUI navigation
    //

    public static void openRegistrationWindow(Stage stageToClose) throws IOException {
        FXMLLoader fxmlLoader = stageController.loadFXML("register");
        stageController.openStage(fxmlLoader, "Register", 450, 300, stageToClose);
    }

    public static void openLoginWindow(Stage stageToClose) throws IOException {
        FXMLLoader fxmlLoader = stageController.loadFXML("login");
        stageController.openStage(fxmlLoader, "Login", 350, 200, stageToClose);
    }

    private void setErrorMessage(String string) {
        errorMessageLabel.setText(string);
    }

    public void handleLoginHyperlink(ActionEvent actionEvent) throws IOException {
        Stage stageToClose = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        openLoginWindow(stageToClose);
    }

    public void handleRegisterHyperlink(ActionEvent actionEvent) throws IOException {
        Stage stageToClose = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        openRegistrationWindow(stageToClose);
    }

    public void handleGuestHyperlink(ActionEvent actionEvent) throws IOException {
        Stage stageToClose = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        openMainGUI(stageToClose,"Welcome Guest!");
    }

    /**
     * Opens the main GUI window with the provided welcome message, closing the specified stage.
     *
     * @param stageToClose   The stage to be closed before opening the main GUI window.
     * @param welcomeMessage The welcome message to be displayed in the main GUI.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    private void openMainGUI(Stage stageToClose, String welcomeMessage) throws IOException {
        final int WIDTH = 580;
        final int HEIGHT = 280;

        Point2D bottomRightPosition = calculateBottomRightPosition(WIDTH, HEIGHT);
        FXMLLoader fxmlLoader = loadFXML("main");

        MainController mainController = fxmlLoader.getController();
        mainController.setWelcomeLabel(welcomeMessage);

        openStage(fxmlLoader, "Iz.Lumen", WIDTH, HEIGHT, stageToClose, bottomRightPosition.getX(), bottomRightPosition.getY());

    }
}
