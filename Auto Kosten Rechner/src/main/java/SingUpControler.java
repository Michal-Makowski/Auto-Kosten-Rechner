import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class SingUpControler {

    @FXML
    TextField textFieldNewUsername;

    @FXML
    TextField textFieldNewPassword;

    @FXML
    TextField textFieldNewPasswordRepeat;

    @FXML
    Button buttonLogIn;

    @FXML
    Button buttonSingUp;

    @FXML
    Label labelError;

    @FXML
    private void buttonSingUpClicked() throws SQLException {
        Statement statement = DBConnector.connect().createStatement();
            ResultSet queryResult = statement.executeQuery("SELECT count(1) FROM users WHERE username = '" + textFieldNewUsername.getText() + "'");
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    labelError.setText("Username existiert in database");
                } else {
                    if (Objects.equals(textFieldNewPassword.getText(), textFieldNewPasswordRepeat.getText())) {
                        Statement newstatement = DBConnector.connect().createStatement();
                        newstatement.executeUpdate("INSERT INTO users (username, password) VALUES ('" + textFieldNewUsername.getText() + "','" + textFieldNewPassword.getText() + "')");
                        labelError.setText("");
                    } else {
                        labelError.setText("Falsche Password");
                    }
                }
            }
    }

    @FXML
    private void buttonLogInClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
