package PopupWindow;

import Validation.AddCarValid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCarControler {
    String carBrandError = "Bitte richtige Fahrzeug Marke eingeben";
    String carNumberError = "Bitte richtige Kennzeichennummer eingeben";
    String carModelError = "Bitte richtige Fahrzeug Model eingeben";
    String carRegistrationError = "Bitte richtige EZ datum eingeben";

    @FXML
    TextField textFieldCarNumber, textFieldCarModel, textFieldCarBrand, textFieldCarRegistration;

    @FXML
    Label labelError;

    @FXML
    private void buttonCancelClicked(ActionEvent event){
        closeWindow(event);
    }

   @FXML
    private void buttonOkClicked(ActionEvent event){
        if(AddCarValid.carNumberValid() && AddCarValid.carBrandValid() && AddCarValid.carModelValid() && AddCarValid.carRegistrationValid()){
            //add record to DB
            closeWindow(event);
        }else if(!AddCarValid.carNumberValid()){
            labelError.setText(carNumberError);
        }else if(!AddCarValid.carBrandValid()){
            labelError.setText(carBrandError);
        }else if(!AddCarValid.carModelValid()){
            labelError.setText(carModelError);
        }else if(!AddCarValid.carRegistrationValid()){
            labelError.setText(carRegistrationError);
        }
    }

    private void closeWindow(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
