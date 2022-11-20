package Window;

import DataBase.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LogInControler {

    public static String user;

    @FXML
    private TextField textFieldUsername, textFieldPassword;

    @FXML
    private Label labelError;

    @FXML
    private void buttonLoginClicked(ActionEvent event) {
        user = textFieldUsername.getText();
        String verifyLogin = ("SELECT count(1) FROM users WHERE username = '" + textFieldUsername.getText() + "' AND password = '" + textFieldPassword.getText() + "'");
        Statement statement = null;
        try {
            statement = DBConnector.connect().createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()) {
                if(queryResult.getInt(1) == 1) {
                    labelError.setText("");
                    WindowMethods.newWindow(event, WindowMethods.MAIN_WINDOW_TITLE, WindowMethods.MAIN_WINDOW_URL);
                }
                else{
                    labelError.setText("Falsche Username oder Password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void buttonSingUpClicked(ActionEvent event) {
        WindowMethods.newWindow(event, WindowMethods.SING_UP_TITLE, WindowMethods.SING_UP_URL);
    }
}
