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
                    Parent root = FXMLLoader.load(getClass().getResource("/Window/MainWindow.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                }
                else{
                    labelError.setText("Falsche Username oder Password");
                }
            }
    }

    @FXML
    private void buttonSingUpClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Window/SingUp.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
