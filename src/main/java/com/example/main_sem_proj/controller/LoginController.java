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
 * The Controller class for the Login/Registration view of the application.
 * This class handles the user interactions to login GUIs.
 */
public class LoginController {
    private final Validation validation = new Validation();
    private final SqliteUserDAO userDAO = new SqliteUserDAO();
    @FXML private Label errorMessageLabel;
    @FXML private Button signInButton;
    @FXML private Button registerButton;
    @FXML private CheckBox agreeCheckBox;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Hyperlink GuestButton;

    /**
     * Opens a window with the specified view.
     */
    public static void openWindow(String view, String title, int width, int height, Stage stageToClose) {
        try {
            Stage windowStage = createStage(view, title, width, height);
            windowStage.show();
            closeStage(stageToClose);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Closes the specified stage.
     */
    public static void closeStage(Stage stageToClose) {
        if (stageToClose != null) {
            stageToClose.close();
        }
    }

    /**
     * Opens the registration form window.
     */
    public static void openRegistrationWindow(Stage stageToClose) {
        openWindow("register", "Register", 450, 300, stageToClose);
    }

    /**
     * Opens the login window.
     */
    public static void openLoginWindow(Stage stageToClose) {
        openWindow("login", "Login", 350, 200, stageToClose);
    }

    /**
     * Creates a new stage with the specified view, title, width, and height.
     *
     * @param VIEW   The name of the FXML view file to load.
     * @param title  The title of the stage.
     * @param width  The width of the stage.
     * @param height The height of the stage.
     * @return The newly created stage.
     * @throws IOException If an error occurs while loading the FXML file.
     */
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
                setErrorMessage("Please enter your email and password");
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
                setErrorMessage("Invalid email or password");
            }
        } catch (Exception e) {
            setErrorMessage("An error occurred. Please try again later.");
        }
    }


    /**
     * Handles the registration of a new user.
     * This method is invoked when the register button is clicked.
     */
    @FXML
    public void handleRegisterUser(ActionEvent actionEvent) {
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

        if (!validation.ValidateEmail(email)) {
            setErrorMessage("Invalid Email");
            return;
        }

        if (userDAO.CheckEmailTaken(email)) {
            setErrorMessage("Email has been taken");
            return;
        }

        userDAO.addUser(new User(email, firstName, lastName, password));
        handleLoginHyperlink(actionEvent);
    }

    private void setErrorMessage(String string) {
        errorMessageLabel.setText(string);
    }

    public void handleLoginHyperlink(ActionEvent actionEvent) {
        Stage stageToClose = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        openLoginWindow(stageToClose);
    }

    public void handleRegisterHyperlink(ActionEvent actionEvent) {
        Stage stageToClose = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        openRegistrationWindow(stageToClose);
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
