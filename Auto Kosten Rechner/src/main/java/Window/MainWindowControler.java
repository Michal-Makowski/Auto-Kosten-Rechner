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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainWindowControler implements Initializable {
    private Popup popup = new Popup();
    public static int carID;
    public static String user;
    //and auto Date

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
        popup.newEditCarPopup(Popup.EDIT_CAR_TITLE, Popup.EDIT_CAR_URL, car).setOnHiding(windowEvent -> {
            int choiceBoxId = choiceBox.getSelectionModel().getSelectedIndex();
            updateChoiceBox();
            choiceBox.getSelectionModel().select(choiceBoxId);
        });
    }

    @FXML
    private void buttonLogoutClicked(ActionEvent event){
        WindowMethods.newWindow(event, WindowMethods.LOG_IN_TITLE, WindowMethods.LOG_IN_URL);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelUser.setText(LogInControler.user);
        user = LogInControler.user;
        updateChoiceBox();
        choiceBox.setOnAction(event -> updateView(event));
        buttonSetDisable();
    }

    public void updateChoiceBox() {
        int allCarsSize;
        try {
            choiceBox.getItems().clear();
            allCarsSize = DbMethods.allCars(user).size();
            choiceBox.getItems().addAll(DbMethods.allCars(user));
            if (!choiceBox.getItems().isEmpty()) {
                choiceBox.setValue(DbMethods.allCars(user).get(allCarsSize-1));
                setCar();
                updateCost();
            }else{
                setCar();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        buttonSetDisable();
    }

    public void setCar(){
        if(choiceBox.getItems().isEmpty()) {
            labelBrand.setText("");
            labelModel.setText("");
            labelRegistration.setText("");
            choiceBox.setDisable(true);
        }else{
            carID = choiceBox.getValue().getId();
            labelBrand.setText(choiceBox.getValue().getBrand());
            labelModel.setText(choiceBox.getValue().getModel());
            labelRegistration.setText(String.valueOf(choiceBox.getValue().getRegistration()));
            choiceBox.setDisable(false);
        }
    }

    private void updateView(ActionEvent event) {
        setCar();
        updateCost();
    }

    public void updateCost() {
        try {
            ObservableList<Cost> allCosts = DbMethods.allCost(choiceBox.getValue().getId());
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
        if(choiceBox.getItems().isEmpty()){
            buttonCarDelete.setDisable(true);
            buttonCarEdit.setDisable(true);
        }else {
            buttonCarDelete.setDisable(false);
            buttonCarEdit.setDisable(false);
        }
    }
}
