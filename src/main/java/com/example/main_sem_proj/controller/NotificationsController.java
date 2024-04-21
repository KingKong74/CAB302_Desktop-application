package com.example.main_sem_proj.controller;

import com.example.main_sem_proj.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class NotificationsController extends MainController{

    private static final double WIDTH = 200;
    private static final double HEIGHT = 250;
    public static Stage popupStage;


    @FXML
    protected static void ButtonClick(double x, double y) {
        if (!isPopupOpen) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/notifications-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);

                double popupX = x - WIDTH / 2;
                double popupY = y - HEIGHT /2;

                // Adjust if the popup goes out of the screen bounds
                double screenWidth = Screen.getPrimary().getBounds().getWidth();
                double screenHeight = Screen.getPrimary().getBounds().getHeight();
                if (popupX < 0) {
                    popupX = 0;
                } else if (popupX + WIDTH > screenWidth) {
                    popupX = screenWidth - WIDTH;
                }
                if (popupY < 0) {
                    popupY = 0;
                } else if (popupY + HEIGHT > screenHeight) {
                    popupY = screenHeight - HEIGHT;
                }

                popupStage  = new Stage();
                popupStage.setScene(scene);
                popupStage.initStyle(StageStyle.UTILITY); // removes minimise
                popupStage.setOnCloseRequest(event -> {
                    System.out.println("Popup is closing");
                    currentPopup = null;
                });
                System.out.println("Mouse clicked at: X=" + x + ", Y=" + y);
                popupStage.setX(popupX);
                popupStage.setY(popupY);
                popupStage.showAndWait();
                isPopupOpen = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Application is closing");
            if (popupStage != null) {
                popupStage.close();
                isPopupOpen = false;
            }
        }

    }

}
