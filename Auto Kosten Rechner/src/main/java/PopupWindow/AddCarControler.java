package PopupWindow;

import DataBase.DbMethods;
import Validation.AddCarValid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddCarControler {

    @FXML
    private TextField textFieldCarNumber, textFieldCarModel, textFieldCarBrand, textFieldCarRegistration;

    @FXML
    private Label labelError;

   @FXML
    private void buttonOkClicked(ActionEvent event) {
        if(AddCarValid.carNumberValid() && AddCarValid.carBrandValid() && AddCarValid.carModelValid() && AddCarValid.carRegistrationValid()){
            DbMethods.dbAddCar(textFieldCarNumber.getText(), textFieldCarBrand.getText(), textFieldCarModel.getText(), textFieldCarRegistration.getText());
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
