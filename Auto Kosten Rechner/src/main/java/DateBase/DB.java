package DateBase;

import java.sql.SQLException;
import java.sql.Statement;

public class DB {

    public static void dbAddCar(String carNumber, String carBrand, String carModel, String carRegistration) throws SQLException {
        String addCar = "INSERT INTO fahrzeug (kennzeichen, marke, modell, erstzulassung) VALUES ('" + carNumber + "','" + carBrand + "','" + carModel + "','" + carRegistration + "')";
        Statement statement = DBConnector.connect().createStatement();
        statement.executeUpdate(addCar);
    }

    public static void dbAddFuel(String fuelCost, String fuelKilometer, String fuelDate) throws SQLException {
        String costArt = "Tanken";
        String addCar = "INSERT INTO kosten (kostenart, betrag, kilometerstand, datum, beschreibung) VALUES ('" + costArt + "','" + fuelCost + "','" + fuelKilometer + "','" + fuelDate + "','')";
        Statement statement = DBConnector.connect().createStatement();
        statement.executeUpdate(addCar);

    }

    public static void dbAddService(String serviceCost, String serviceKilometer, String serviceDate, String serviceComment) throws SQLException {
        String costArt = "Service";
        String addCar = "INSERT INTO kosten (kostenart, betrag, kilometerstand, datum, beschreibung) VALUES ('" + costArt + "','" + serviceCost + "','" + serviceKilometer + "','" + serviceDate + "','" + serviceComment + "')";
        Statement statement = DBConnector.connect().createStatement();
        statement.executeUpdate(addCar);
    }

}
