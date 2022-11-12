package PopupWindow;

import DateBase.DB;
import Validation.AddCarValid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AddCarControler {

    @FXML
    TextField textFieldCarNumber, textFieldCarModel, textFieldCarBrand, textFieldCarRegistration;

    @FXML
    Label labelError;

   @FXML
    private void buttonOkClicked(ActionEvent event) throws SQLException {
        if(AddCarValid.carNumberValid() && AddCarValid.carBrandValid() && AddCarValid.carModelValid() && AddCarValid.carRegistrationValid()){
            DB.dbAddCar(textFieldCarNumber.getText(), textFieldCarBrand.getText(), textFieldCarModel.getText(), textFieldCarRegistration.getText());
            Popup.close(event);
        }else if(!AddCarValid.carNumberValid()){
            labelError.setText(Popup.NUMBER_ERROR);
        }else if(!AddCarValid.carBrandValid()){
            labelError.setText(Popup.BRAND_ERROR);
        }else if(!AddCarValid.carModelValid()){
            labelError.setText(Popup.MODEL_ERROR);
        }else if(!AddCarValid.carRegistrationValid()){
            labelError.setText(Popup.REGISTRATION_ERROR);
        }
    }

    @FXML
    private void buttonCancelClicked(ActionEvent event){
        Popup.close(event);
    }
}
