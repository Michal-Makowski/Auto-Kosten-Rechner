package PopupWindow;

import DataBase.DbMethods;
import Validation.AddFuelValid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddFuelControler implements Initializable {

    @FXML
    private Label labelError;

    @FXML
    private TextField textFieldCost, textFieldKilometer;

    @FXML
    private DatePicker datePicker;

    @FXML
    private void buttonOkClicked(ActionEvent event) {
        if(AddFuelValid.fuelCostValid() && AddFuelValid.fuelDateValid() && AddFuelValid.fuelKilometerValid()){
            DbMethods.dbAddFuel(textFieldCost.getText(), textFieldKilometer.getText(), datePicker.getValue().toString());
            Popup.close(event);
        }else if(!AddFuelValid.fuelCostValid()){
            labelError.setText(Popup.COST_ERROR);
        }else if(!AddFuelValid.fuelDateValid()){
            labelError.setText(Popup.DATE_ERROR);
        }else if(!AddFuelValid.fuelKilometerValid()){
            labelError.setText(Popup.KILOMETER_ERROR);
        }
    }

    @FXML
    private void buttonCancelClicked(ActionEvent event){
        Popup.close(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datePicker.setValue(LocalDate.now());
    }
}
