package Window;

import DataBase.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LogInControler {

    public static String user;

    @FXML
    TextField textFieldUsername, textFieldPassword;

    @FXML
    Label labelError;

    @FXML
    private void buttonLoginClicked(ActionEvent event) throws SQLException, IOException {
        user = textFieldUsername.getText();
        String verifyLogin = ("SELECT count(1) FROM users WHERE username = '" + textFieldUsername.getText() + "' AND password = '" + textFieldPassword.getText() + "'");
        Statement statement =  DBConnector.connect().createStatement();
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
    }

    @FXML
    private void buttonSingUpClicked(ActionEvent event) throws IOException {
        WindowMethods.newWindow(event, WindowMethods.SING_UP_TITLE, WindowMethods.SING_UP_URL);
    }
}
