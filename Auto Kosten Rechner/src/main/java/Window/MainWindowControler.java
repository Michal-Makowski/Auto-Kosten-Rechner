package Window;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import PopupWindow.Popup;

public class MainWindowControler {
    Popup popup = new Popup();
    private static final String ADD_CAR_TITLE = "Fahrzeug hinzufügen";
    private static final String ADD_CAR_URL = "/PopupWindow/AddCar.fxml";
    private static final String ADD_FUEL_TITLE = "Tanken hinzufügen";
    private static final String ADD_FUEL_URL = "/PopupWindow/AddFuel.fxml";
    private static final String ADD_SERVICE_TITLE = "Service hinzufügen";
    private static final String ADD_SERVICE_URL = "/PopupWindow/AddService.fxml";



    @FXML
    private void buttonAddCarClicked() throws IOException {
        popup.newPopup(ADD_CAR_TITLE,ADD_CAR_URL);
    }

    @FXML
    private void buttonAddFuelClicked() throws IOException {
        popup.newPopup(ADD_FUEL_TITLE,ADD_FUEL_URL);
    }

    @FXML
    private void buttonAddServiceClicked() throws IOException {
        popup.newPopup(ADD_SERVICE_TITLE,ADD_SERVICE_URL);
    }

}
