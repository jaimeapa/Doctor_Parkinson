module com.example.doctor_parkinson {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.doctor_parkinson to javafx.fxml;
    exports com.example.doctor_parkinson;
}