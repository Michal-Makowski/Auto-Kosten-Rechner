package PopupWindow;
import DataBase.Car;
import DataBase.Cost;
import DataBase.DbMethods;
import Validation.AddCarValid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class EditCarControler {

    Car car = new Car();

    @FXML
    TextField textFieldCarNumber, textFieldCarModel, textFieldCarBrand, textFieldCarRegistration;

    @FXML
    Label labelError;

    @FXML
    private void buttonOkClicked(ActionEvent event) throws SQLException {
        if(AddCarValid.carNumberValid() && AddCarValid.carBrandValid() && AddCarValid.carModelValid() && AddCarValid.carRegistrationValid()){
            DbMethods.dbEditCar(car.getId(), textFieldCarNumber.getText(), textFieldCarBrand.getText(), textFieldCarModel.getText(), textFieldCarRegistration.getText());
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

    public void setCar(Car car) {
        this.car = car;
        textFieldCarNumber.setText(String.valueOf(car.getNumber()));
        textFieldCarModel.setText(String.valueOf(car.getModel()));
        textFieldCarBrand.setText(String.valueOf(car.getBrand()));
        textFieldCarRegistration.setText(String.valueOf(car.getRegistration()));
    }
}
