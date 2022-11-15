package PopupWindow;

import DataBase.Cost;
import DataBase.DbMethods;
import Validation.AddFuelValid;
import Window.MainWindowControler;
import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditFuelControler {

    Cost cost = new Cost();

    @FXML
    Label labelError;

    @FXML
    TextField textFieldCost, textFieldDate, textFieldKilometer;

    @FXML
    private void buttonOkClicked(ActionEvent event) throws SQLException {
        if(AddFuelValid.fuelCostValid() && AddFuelValid.fuelDateValid() && AddFuelValid.fuelKilometerValid()){
            DbMethods.dbEditFuel(cost.getId(), textFieldCost.getText(), textFieldKilometer.getText(), textFieldDate.getText());
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
        textFieldDate.setText(String.valueOf(cost.getDate()));
        textFieldKilometer.setText(String.valueOf(cost.getKilometer()));
    }
}
