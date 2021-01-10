package manager;

import javafx.application.Application;
import javafx.stage.Stage;

import manager.components.task_visualize.*;
import manager.configuration.Configuration;
import manager.ui.UserInterface;
import manager.components.Task;
import manager.database.DbConnection;
import manager.controller.MainEventsController;

import java.util.List;

public class ManagerApp extends Application {

    private final DbConnection dbConn = Configuration.getInstance().getDbConnection();
    private final UserInterface ui = Configuration.getInstance().getUserInterface();
    private final MainEventsController eventController = Configuration.getInstance().getMainEventsController();

    public ManagerApp(){
        List<Task> tasksData = dbConn.getTasks();
        List<ViewComponent> components = Configuration.getInstance().getComponentsList();
        for(var data : tasksData){
            components.add(
                    new EditorViewDecorator(
                        new DragViewDecorator(
                            new BorderViewDecorator(
                                new BackgroundViewDecorator(
                                    data
                    )))));
        }
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Task Manager");
        stage.setScene(ui.getScene());
        eventController.refresh();
        stage.show();
    }

    @Override
    public void stop(){

        dbConn.close();
    }

    public static void main(String[] args){
        launch(args);
    }
}
