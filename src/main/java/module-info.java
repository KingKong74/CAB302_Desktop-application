module com.example.main_sem_proj {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.main_sem_proj to javafx.fxml;
    opens com.example.main_sem_proj.controller to javafx.fxml;
    exports com.example.main_sem_proj;
    exports com.example.main_sem_proj.controller;
    exports com.example.main_sem_proj.model;
    exports com.example.main_sem_proj.model.Notifications;
    exports com.example.main_sem_proj.model.users;
    exports com.example.main_sem_proj.controller.mainGUI;
    opens com.example.main_sem_proj.controller.mainGUI to javafx.fxml;
    exports com.example.main_sem_proj.controller.authentication;
    opens com.example.main_sem_proj.controller.authentication to javafx.fxml;
}
