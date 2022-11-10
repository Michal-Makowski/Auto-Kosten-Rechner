package PopupWindow;

import Validation.AddFuelValid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddFuelControler {

    String fuelCostError = "Bitte richtige Betrag eingeben";
    String fuelDateError = "Bitte richtige Datum eingeben";
    String fuelKilometerError = "Bitte richtige Kilometerstand eingeben";

    @FXML
    Label labelError;

    @FXML
    TextField textFieldCost, textFieldDate, textFieldKilometer;

    @FXML
    private void buttonCancelClicked(ActionEvent event){
        closeWindow(event);
    }

    @FXML
    private void buttonOkClicked(ActionEvent event){
        if(AddFuelValid.fuelCostValid() && AddFuelValid.fuelDateValid() && AddFuelValid.fuelKilometerValid()){
            //ADD record to DB
            closeWindow(event);
        }else if(!AddFuelValid.fuelCostValid()){
            labelError.setText(fuelCostError);
        }else if(!AddFuelValid.fuelDateValid()){
            labelError.setText(fuelDateError);
        }else if(!AddFuelValid.fuelKilometerValid()){
            labelError.setText(fuelKilometerError);
        }
    }

    private void closeWindow(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
