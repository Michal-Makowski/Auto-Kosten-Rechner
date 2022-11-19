package Window;

import DataBase.Car;
import DataBase.Cost;
import DataBase.DbMethods;
import PopupWindow.EditFuelControler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import PopupWindow.Popup;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainWindowControler implements Initializable {
    private Popup popup = new Popup();
    public static String carNumber;
    //change carNumber to carID add logout option and auto Date /// when car delete all cost also delete

    @FXML
    Button buttonCarDelete, buttonCarEdit;

    @FXML
    public TableView<Cost> tableCost;

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
        popup.newPopup(Popup.ADD_CAR_TITLE, Popup.ADD_CAR_URL).setOnHiding(windowEvent -> updateChoiceBox());
    }

    @FXML
    private void buttonAddFuelClicked() throws IOException {
        popup.newPopup(Popup.ADD_FUEL_TITLE,Popup.ADD_FUEL_URL).setOnHiding(event ->{updateCost();});
    }

    @FXML
    private void buttonAddServiceClicked() throws IOException {
        popup.newPopup(Popup.ADD_SERVICE_TITLE,Popup.ADD_SERVICE_URL).setOnHiding(event ->{updateCost();});
    }

    @FXML
    private void buttonCostDeleteClicked() throws SQLException {
        int selectedID = tableCost.getSelectionModel().getSelectedIndex();
        if(selectedID != -1) {
            Cost cost = tableCost.getItems().get(selectedID);
            DbMethods.dbDeleteCost(cost.getId());
            tableCost.getItems().remove(selectedID);
        }
    }

    @FXML
    private void buttonCostEditClicked() throws IOException {
        int selectedID = tableCost.getSelectionModel().getSelectedIndex();
        if(selectedID != -1){
            Cost cost = tableCost.getItems().get(selectedID);
            if(cost.getCostType().equals("Tanken")) {
                popup.newEditFuelPopup(Popup.EDIT_FUEL_TITLE, Popup.EDIT_FUEL_URL, cost).setOnHiding(event ->{updateCost();});
            }else if(cost.getCostType().equals("Service")){
                popup.newEditServicePopup(Popup.EDIT_SERVICE_TITLE,Popup.EDIT_SERVICE_URL, cost).setOnHiding(event ->{updateCost();});
            }
        }
    }

    @FXML
    private void buttonCarDeleteClicked() throws SQLException {
        int carID = choiceBox.getValue().getId();
        DbMethods.dbDeleteCar(carID);
        updateChoiceBox();

    }
    @FXML
    private void buttonCarEditClicked() throws IOException {
        Car car = choiceBox.getValue();
        popup.newEditCarPopup(Popup.EDIT_CAR_TITLE, Popup.EDIT_CAR_URL, car).setOnHiding(windowEvent -> {updateChoiceBox();});
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelUser.setText(LogInControler.user);
        updateChoiceBox();
        choiceBox.setOnAction(event -> updateView(event));
        buttonSetDisable();
    }

    public void updateChoiceBox() {
        int allCarsSize;
        try {
            choiceBox.getItems().clear();
            allCarsSize = DbMethods.allCars().size();
            choiceBox.getItems().addAll(DbMethods.allCars());
            if (allCarsSize != 0) {
                choiceBox.setValue(DbMethods.allCars().get(allCarsSize-1));
                setCar();
                updateCost();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void setCar(){
        carNumber = choiceBox.getValue().getNumber();
        labelBrand.setText(choiceBox.getValue().getBrand());
        labelModel.setText(choiceBox.getValue().getModel());
        labelRegistration.setText(String.valueOf(choiceBox.getValue().getRegistration()));
    }

    private void updateView(ActionEvent event) {
        setCar();
        updateCost();
    }

    public void updateCost() {
        try {
            ObservableList<Cost> allCosts = DbMethods.allCost(choiceBox.getValue().getNumber());
            tableCost.setItems(allCosts);
            columnCostType.setCellValueFactory(new PropertyValueFactory<Cost, String>("costType"));
            columnCost.setCellValueFactory(new PropertyValueFactory<Cost, Integer>("cost"));
            columnKilometer.setCellValueFactory(new PropertyValueFactory<Cost, Integer>("kilometer"));
            columnDate.setCellValueFactory(new PropertyValueFactory<Cost, String>("date"));
            columnComment.setCellValueFactory(new PropertyValueFactory<Cost, String>("comment"));
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buttonSetDisable(){
        if(choiceBox == null){
            buttonCarDelete.setDisable(true);
            buttonCarEdit.setDisable(true);
        }else {
            buttonCarDelete.setDisable(false);
            buttonCarEdit.setDisable(false);
        }
    }
}
