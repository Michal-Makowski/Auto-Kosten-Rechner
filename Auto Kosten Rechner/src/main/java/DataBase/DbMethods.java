package DataBase;

import Window.LogInControler;
import Window.MainWindowControler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DbMethods {

    public static void dbAddCar(String carNumber, String carBrand, String carModel, String carRegistration) throws SQLException {
        String addCar = "INSERT INTO car (number, brand, model, registration, user) VALUES ('" + carNumber + "','" + carBrand + "','" + carModel + "','" + carRegistration + "','" + LogInControler.user + "')";
        Statement statement = DBConnector.connect().createStatement();
        statement.executeUpdate(addCar);
    }

    public static void dbAddFuel(String fuelCost, String fuelKilometer, String fuelDate) throws SQLException {
        String costType = "Tanken";
        String addCar = "INSERT INTO costs (cost_type, cost, kilometer, date, comment, car) VALUES ('" + costType + "','" + fuelCost + "','" + fuelKilometer + "','" + fuelDate + "','','" + MainWindowControler.carNumber + "')";
        Statement statement = DBConnector.connect().createStatement();
        statement.executeUpdate(addCar);

    }

    public static void dbAddService(String serviceCost, String serviceKilometer, String serviceDate, String serviceComment) throws SQLException {
        String costType = "Service";
        String addCar = "INSERT INTO costs (cost_type, cost, kilometer, date, comment, car) VALUES ('" + costType + "','" + serviceCost + "','" + serviceKilometer + "','" + serviceDate + "','" + serviceComment + "','" + MainWindowControler.carNumber + "')";
        Statement statement = DBConnector.connect().createStatement();
        statement.executeUpdate(addCar);
    }

    public static void dbDeleteCost(int id) throws SQLException {
        String deleteCost = "DELETE FROM costs WHERE cost_id='"+ id +"'";
        Statement statement = DBConnector.connect().createStatement();
        statement.executeUpdate(deleteCost);
    }

    public static void dbEditFuel(int id, String fuelCost, String fuelKilometer, String fuelDate ) throws SQLException{
        String editFuel = "UPDATE costs SET cost='"+ fuelCost + "', kilometer='"+ fuelKilometer + "', date='"+ fuelDate +"' WHERE cost_id='"+ id +"'";
        Statement statement = DBConnector.connect().createStatement();
        statement.executeUpdate(editFuel);
    }

    public static void dbEditService(int id, String serviceCost, String serviceKilometer, String serviceDate, String serviceComment) throws SQLException{
        String editFuel = "UPDATE costs SET cost='"+ serviceCost + "', kilometer='"+ serviceKilometer + "', date='"+ serviceDate +"', comment='"+ serviceComment+"' WHERE cost_id='"+ id +"'";
        Statement statement = DBConnector.connect().createStatement();
        statement.executeUpdate(editFuel);
    }

    public static void dbDeleteCar(int carID) throws SQLException {
        String deleteCar = "DELETE FROM car WHERE car_id='"+ carID +"'";
        Statement statement = DBConnector.connect().createStatement();
        statement.executeUpdate(deleteCar);
    }

    public static void dbEditCar(int carID, String carNumber, String carBrand, String carModel, String carRegistration) throws SQLException{
        String editCar = "UPDATE car SET number='"+ carNumber +"' , brand='"+ carBrand +"', model='"+ carModel +"' , registration='"+ carRegistration +"' WHERE car_id='"+ carID +"'";
        Statement statement = DBConnector.connect().createStatement();
        statement.executeUpdate(editCar);
    }

    public static ArrayList<Car> allCars() throws SQLException {
        ArrayList<Car> cars = new ArrayList<>();
        String getCars = ("SELECT * FROM car");
        Statement statement =  DBConnector.connect().createStatement();
        ResultSet queryResult = statement.executeQuery(getCars);

        while (queryResult.next()){
            Car car = new Car();
            car.setId(queryResult.getInt("car_id"));
            car.setNumber(queryResult.getString("number"));
            car.setBrand(queryResult.getString("brand"));
            car.setModel(queryResult.getString("model"));
            car.setRegistration(queryResult.getInt("registration"));
            cars.add(car);
        }
        return cars;
    }

    public static ObservableList<Cost> allCost(String carNumber) throws SQLException{
        ObservableList<Cost> costs = FXCollections.observableArrayList();
        String getCosts = ("SELECT * From costs WHERE car='" + carNumber +"'");
        Statement statement =  DBConnector.connect().createStatement();
        ResultSet queryResult = statement.executeQuery(getCosts);
        while (queryResult.next()){
            Cost cost = new Cost();
            cost.setId(queryResult.getInt("cost_id"));
            cost.setCostType(queryResult.getString("cost_type"));
            cost.setCost(queryResult.getInt("cost"));
            cost.setKilometer(queryResult.getInt("kilometer"));
            cost.setDate(queryResult.getString("date"));
            cost.setComment(queryResult.getString("comment"));
            cost.setCar(queryResult.getString("car"));
            costs.add(cost);
        }
        return costs;

    }

}
