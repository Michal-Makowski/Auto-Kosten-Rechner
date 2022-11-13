package PopupWindow;

import DataBase.Cost;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Popup {


    public static final String BRAND_ERROR = "Bitte richtige Fahrzeug Marke eingeben";
    public static final String NUMBER_ERROR = "Bitte richtige Kennzeichennummer eingeben";
    public static final String MODEL_ERROR = "Bitte richtige Fahrzeug Model eingeben";
    public static final String REGISTRATION_ERROR = "Bitte richtige EZ Datum eingeben";
    public static final String COST_ERROR = "Bitte richtige Betrag eingeben";
    public static final String DATE_ERROR = "Bitte richtige Datum eingeben";
    public static final String KILOMETER_ERROR = "Bitte richtige Kilometerstand eingeben";
    public static final String COMMENT_ERROR = "Bitte richtige Beschreibung eingeben";

    public static final String ADD_CAR_TITLE = "Fahrzeug hinzufügen";
    public static final String ADD_CAR_URL = "/PopupWindow/AddCar.fxml";
    public static final String ADD_FUEL_TITLE = "Tanken hinzufügen";
    public static final String ADD_FUEL_URL = "/PopupWindow/AddFuel.fxml";
    public static final String ADD_SERVICE_TITLE = "Service hinzufügen";
    public static final String ADD_SERVICE_URL = "/PopupWindow/AddService.fxml";
    public static final String EDIT_FUEL_TITLE = "Tanken ändern";
    public static final String EDIT_FUEL_URL = "/PopupWindow/EditFuel.fxml";
    public static final String EDIT_SERVICE_TITLE = "Service ändern";
    public static final String EDIT_SERVICE_URL = "/PopupWindow/EditService.fxml";

    public static void close(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void newPopup(String title, String url) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(url));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

}
