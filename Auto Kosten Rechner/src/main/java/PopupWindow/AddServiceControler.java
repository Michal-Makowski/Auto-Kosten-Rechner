package PopupWindow;

import DataBase.DbMethods;
import Validation.AddServiceValid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddServiceControler implements Initializable {

    @FXML
    private Label labelError;

    @FXML
    private TextField textFieldCost, textFieldKilometer, textFieldComment;

    @FXML
    private DatePicker datePicker;

    @FXML
    private void buttonOkClicked(ActionEvent event) {
        if(AddServiceValid.serviceCommentValid() && AddServiceValid.serviceCostValid() && AddServiceValid.serviceDateValid() && AddServiceValid.serviceKilometerValid()){
            DbMethods.dbAddService(textFieldCost.getText(), textFieldKilometer.getText(), datePicker.getValue().toString(), textFieldComment.getText());
            Popup.close(event);
        }else if (!AddServiceValid.serviceDateValid()){
            labelError.setText(Popup.DATE_ERROR);
        }else if (!AddServiceValid.serviceCostValid()){
            labelError.setText(Popup.COST_ERROR);
        }else if (!AddServiceValid.serviceKilometerValid()){
            labelError.setText(Popup.KILOMETER_ERROR);
        }else if (!AddServiceValid.serviceCommentValid()){
            labelError.setText(Popup.COMMENT_ERROR);
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
