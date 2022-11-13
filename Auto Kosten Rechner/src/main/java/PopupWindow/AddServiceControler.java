package PopupWindow;

import DataBase.DbMethods;
import Validation.AddServiceValid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AddServiceControler {

    @FXML
    Label labelError;

    @FXML
    TextField textFieldCost, textFieldKilometer, textFieldDate, textFieldComment;

    @FXML
    private void buttonOkClicked(ActionEvent event) throws SQLException {
        if(AddServiceValid.serviceCommentValid() && AddServiceValid.serviceCostValid() && AddServiceValid.serviceDateValid() && AddServiceValid.serviceKilometerValid()){
            DbMethods.dbAddService(textFieldCost.getText(), textFieldKilometer.getText(), textFieldDate.getText(), textFieldComment.getText());
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
}
