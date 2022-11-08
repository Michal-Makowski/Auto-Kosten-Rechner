import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class MainWindowControler {

    @FXML
    Button buttonAddCar;

    @FXML
    private void buttonAddCarClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddCar.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Fahrzeug hinzufügen");
        stage.setScene(scene);
        stage.show();

    }

}
