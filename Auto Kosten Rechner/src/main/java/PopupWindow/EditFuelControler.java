package PopupWindow;

import DataBase.Cost;
import DataBase.DbMethods;
import Validation.AddFuelValid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.time.LocalDate;


public class EditFuelControler {

    private Cost cost = new Cost();

    @FXML
    private Label labelError;

    @FXML
    private TextField textFieldCost, textFieldKilometer;

    @FXML
    private DatePicker datePicker;

    @FXML
    private void buttonOkClicked(ActionEvent event) {
        if(AddFuelValid.fuelCostValid() && AddFuelValid.fuelDateValid() && AddFuelValid.fuelKilometerValid()){
            DbMethods.dbEditFuel(cost.getId(), textFieldCost.getText(), textFieldKilometer.getText(), datePicker.getValue().toString());
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


    public void setFuel(Cost cost) {
        this.cost = cost;
        textFieldCost.setText(String.valueOf(cost.getCost()));
        datePicker.setValue(LocalDate.parse(cost.getDate()));
        textFieldKilometer.setText(String.valueOf(cost.getKilometer()));
    }
}
