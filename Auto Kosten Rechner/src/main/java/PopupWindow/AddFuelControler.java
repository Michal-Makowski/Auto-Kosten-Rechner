package PopupWindow;

import DataBase.DbMethods;
import Validation.AddFuelValid;
import Window.MainWindowControler;
import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AddFuelControler {

    @FXML
    Label labelError;

    @FXML
    TextField textFieldCost, textFieldDate, textFieldKilometer;

    @FXML
    private void buttonOkClicked(ActionEvent event) throws SQLException {
        if(AddFuelValid.fuelCostValid() && AddFuelValid.fuelDateValid() && AddFuelValid.fuelKilometerValid()){
            DbMethods.dbAddFuel(textFieldCost.getText(), textFieldKilometer.getText(), textFieldDate.getText());
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

}