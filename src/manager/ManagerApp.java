package manager;

import javafx.application.Application;
import javafx.stage.Stage;

import manager.ui.UserInterface;
import manager.ui.TableUserInterface;
import manager.components.Task;

public class ManagerApp extends Application {

    private UserInterface ui;
    private Task mainTask;
    private final int width = 1200;
    private final int height = 900;

    public ManagerApp(){
        ui = new TableUserInterface(width, height);
        mainTask = new Task();
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Task Manager");
        stage.setScene(ui.getScene());
        ui.displayTasks(mainTask.iterator());
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
