package Window;

import DataBase.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class SingUpControler {

    @FXML
    private TextField textFieldNewUsername, textFieldNewPassword, textFieldNewPasswordRepeat ;

    @FXML
    private Label labelError;

    @FXML
    private void buttonSingUpClicked(ActionEvent event) {
        Statement statement;
        try {
            statement = DBConnector.connect().createStatement();
            ResultSet queryResult = statement.executeQuery("SELECT count(1) FROM users WHERE username = '" + textFieldNewUsername.getText() + "'");
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    labelError.setText("Username existiert in database");
                } else {
                    if (Objects.equals(textFieldNewPassword.getText(), textFieldNewPasswordRepeat.getText())) {
                        Statement newstatement = DBConnector.connect().createStatement();
                        newstatement.executeUpdate("INSERT INTO users (username, password) VALUES ('" + textFieldNewUsername.getText() + "','" + textFieldNewPassword.getText() + "')");
                        labelError.setText("");
                        WindowMethods.newWindow(event, WindowMethods.LOG_IN_TITLE, WindowMethods.LOG_IN_URL);
                    } else {
                        labelError.setText("Falsche Password");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void buttonLogInClicked(ActionEvent event) {
        WindowMethods.newWindow(event, WindowMethods.LOG_IN_TITLE, WindowMethods.LOG_IN_URL);
    }
}
