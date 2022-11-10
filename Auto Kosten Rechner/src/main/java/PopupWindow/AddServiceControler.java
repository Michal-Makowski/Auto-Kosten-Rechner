package PopupWindow;

import Validation.AddServiceValid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddServiceControler {

    String serviceCommentError = "Bitte richtige Beschreibung eingeben";
    String serviceCostError = "Bitte richtige Betrag eingeben";
    String serviceDateError = "Bitte richtige Datum eingeben";
    String serviceKilometerError = "Bitte richtige Kilometerstand eingeben";

    @FXML
    Label labelError;

    @FXML
    TextField textFieldCost, textFieldKilometer, textFieldDate, textFieldComment;

    @FXML
    private void buttonCancelClicked(ActionEvent event){
        closeWindow(event);
    }

    @FXML
    private void buttonOkClicked(ActionEvent event){
        if(AddServiceValid.serviceCommentValid() && AddServiceValid.serviceCostValid() && AddServiceValid.serviceDateValid() && AddServiceValid.serviceKilometerValid()){
            //ADD to DB
            closeWindow(event);
        }else if (!AddServiceValid.serviceDateValid()){
            labelError.setText(serviceDateError);
        }else if (!AddServiceValid.serviceCostValid()){
            labelError.setText(serviceCostError);
        }else if (!AddServiceValid.serviceKilometerValid()){
            labelError.setText(serviceKilometerError);
        }else if (!AddServiceValid.serviceCommentValid()){
            labelError.setText(serviceCommentError);
        }
    }

    private void closeWindow(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
