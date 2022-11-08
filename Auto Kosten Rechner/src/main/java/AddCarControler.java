import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCarControler {
    @FXML
    TextField textFieldCarNumber, textFieldCarModel, textFieldCarBrand, textFieldCarRegistration;

    @FXML
    Button buttonOk, buttonCancel;

    @FXML
    private void buttonCancelClicked(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void buttonOkClicked(){

    }

    private boolean carNumberValid(){

    }
    private boolean carBrandValid(){

    }
    private boolean carModelValid(){

    }
    private boolean carRegistrationValid(){

    }
}
