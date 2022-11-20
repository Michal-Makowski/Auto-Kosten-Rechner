package PopupWindow;

import DataBase.Cost;
import DataBase.DbMethods;
import Validation.AddServiceValid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.time.LocalDate;

public class EditServiceControler {

    private Cost cost = new Cost();

    @FXML
    private Label labelError;

    @FXML
    private TextField textFieldCost, textFieldKilometer, textFieldComment;

    @FXML
    private DatePicker datePicker;

    @FXML
    private void buttonOkClicked(ActionEvent event) {
        if(AddServiceValid.serviceCommentValid() && AddServiceValid.serviceCostValid() && AddServiceValid.serviceDateValid() && AddServiceValid.serviceKilometerValid()){
            DbMethods.dbEditService(cost.getId(), textFieldCost.getText(), textFieldKilometer.getText(), datePicker.getValue().toString(), textFieldComment.getText());
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

    public void setService(Cost cost) {
        this.cost = cost;
        textFieldCost.setText(String.valueOf(cost.getCost()));
        datePicker.setValue(LocalDate.parse(cost.getDate()));
        textFieldKilometer.setText(String.valueOf(cost.getKilometer()));
        textFieldComment.setText(String.valueOf(cost.getComment()));
    }
}
