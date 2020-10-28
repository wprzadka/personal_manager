import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ManagerApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Task Manager");
        var layout = new StackPane();
        var scene = new Scene(layout, 300, 450);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
