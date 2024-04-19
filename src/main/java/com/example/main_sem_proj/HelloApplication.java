package com.example.main_sem_proj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

    public class HelloApplication extends Application {

        // Constants defining the window title and size
        public static final String TITLE = "Iz.Lumin";
        public static final int WIDTH = 300;
        public static final int HEIGHT = 300;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setResizable(false); // changed to cannot resize (BK)
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
