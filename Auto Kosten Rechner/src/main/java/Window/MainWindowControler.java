package Window;

import DataBase.Car;
import DataBase.Cost;
import DataBase.DbMethods;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import PopupWindow.Popup;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainWindowControler implements Initializable {
    private Popup popup = new Popup();
    public static String carNumber;

    @FXML
    private  TableView<Cost> tableCost;

    @FXML

    private  TableColumn<Cost, String> columnCostType, columnDate, columnComment;

    @FXML

    private  TableColumn<Cost, Integer> columnCost, columnKilometer;

    @FXML
    private Label labelBrand, labelModel, labelRegistration, labelUser;

    @FXML
    private ChoiceBox<Car> choiceBox;

    @FXML
    private void buttonAddCarClicked() throws IOException {
        popup.newPopup(Popup.ADD_CAR_TITLE, Popup.ADD_CAR_URL);
    }

    @FXML
    private void buttonAddFuelClicked() throws IOException {
        popup.newPopup(Popup.ADD_FUEL_TITLE,Popup.ADD_FUEL_URL);
    }

    @FXML
    private void buttonAddServiceClicked() throws IOException {
        popup.newPopup(Popup.ADD_SERVICE_TITLE,Popup.ADD_SERVICE_URL);
    }

    @FXML
    private void buttonCostDeleteClicked() throws SQLException {
        int selectedID = tableCost.getSelectionModel().getSelectedIndex();
        if(selectedID != -1) {
            Cost cost = tableCost.getItems().get(selectedID);
            DbMethods.dbDeleteCost(cost.getId());
            updateCost();
        }
    }

    @FXML
    private void buttonCostEditClicked() throws SQLException, IOException {
        int selectedID = tableCost.getSelectionModel().getSelectedIndex();
        if(selectedID != -1){
            Cost cost = tableCost.getItems().get(selectedID);
            if(cost.getCostType().equals("Tanken")) {
                popup.newPopup(Popup.EDIT_FUEL_TITLE, Popup.EDIT_FUEL_URL);
            }else if(cost.getCostType().equals("Service")){
                popup.newPopup(Popup.EDIT_SERVICE_TITLE,Popup.EDIT_SERVICE_URL);
            }

            //if return true ?? updateCost do not work??
            updateCost();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int allCarsSize;
        labelUser.setText(LogInControler.user);
        try {
            allCarsSize = DbMethods.allCars().size();
            choiceBox.getItems().addAll(DbMethods.allCars());
            if (allCarsSize != 0){
                choiceBox.setValue(DbMethods.allCars().get(0));
                setCar();
                //change value of chocebox
                updateCost();

            }
            choiceBox.setOnAction(event -> {
                try {
                    updateView(event);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setCar(){
        carNumber = choiceBox.getValue().getNumber();
        labelBrand.setText(choiceBox.getValue().getBrand());
        labelModel.setText(choiceBox.getValue().getModel());
        labelRegistration.setText(String.valueOf(choiceBox.getValue().getRegistration()));
    }

    private void updateView(ActionEvent event) throws SQLException {
        setCar();
        updateCost();
    }

    public static void addNewCar(){
        //update choicebox after addNewCar to DB
        // and update table after add new fuel/service
    }


    public void updateCost() throws SQLException {
        ObservableList<Cost> allCosts = DbMethods.allCost(choiceBox.getValue().getNumber());
        tableCost.setItems(allCosts);
        columnCostType.setCellValueFactory(new PropertyValueFactory<Cost, String>("costType"));
        columnCost.setCellValueFactory(new PropertyValueFactory<Cost, Integer>("cost"));
        columnKilometer.setCellValueFactory(new PropertyValueFactory<Cost, Integer>("kilometer"));
        columnDate.setCellValueFactory(new PropertyValueFactory<Cost, String>("date"));
        columnComment.setCellValueFactory(new PropertyValueFactory<Cost, String>("comment"));

    }

    //add edit delete from table / edit delete cars / logout
}
