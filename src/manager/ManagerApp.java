package manager;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import manager.ui.UserInterface;
import manager.ui.DesktopUserInterface;

public class ManagerApp extends Application {

    private UserInterface ui;
    private final int width = 1200;
    private final int height = 900;

    public ManagerApp(){
        ui = new DesktopUserInterface(width, height);
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Task Manager");
        var scene = new Scene(ui.getLayout(), 1200, 900);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
