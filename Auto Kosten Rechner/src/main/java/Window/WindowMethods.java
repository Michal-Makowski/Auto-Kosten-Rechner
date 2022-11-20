package Window;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class WindowMethods {

    public static final String LOG_IN_TITLE = "Login";
    public static final String LOG_IN_URL = "/Window/LogIn.fxml";
    public static final String SING_UP_TITLE = "Neue Konto";
    public static final String SING_UP_URL = "/Window/SingUp.fxml";
    public static final String MAIN_WINDOW_TITLE = "Auto Kosten";
    public static final String MAIN_WINDOW_URL = "/Window/MainWindow.fxml";



    public static void newWindow(ActionEvent event, String title, String url) {
        try {
            Parent root = FXMLLoader.load(WindowMethods.class.getResource(url));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
