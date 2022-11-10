import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MainWindowControler {



    @FXML
    private void buttonAddCarClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PopupWindow/AddCar.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Fahrzeug hinzufügen");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonAddFuelClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PopupWindow/AddFuel.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Tanken hinzufügen");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonAddServiceClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PopupWindow/AddService.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Service hinzufügen");
        stage.setScene(scene);
        stage.show();
    }

}
