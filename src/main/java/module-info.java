module com.example.main_sem_proj {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.main_sem_proj to javafx.fxml;
    exports com.example.main_sem_proj;
    exports com.example.main_sem_proj;
    opens com.example.main_sem_proj to javafx.fxml;
    exports com.example.main_sem_proj.main_sem_proj;
    opens com.example.main_sem_proj.main_sem_proj to javafx.fxml;
    exports com.example.main_sem_proj.controller;
    opens com.example.main_sem_proj.controller to javafx.fxml;
}