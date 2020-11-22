package manager;

import javafx.application.Application;
import javafx.stage.Stage;

import manager.components.task_visualize.BackgroundViewDecorator;
import manager.components.task_visualize.BorderViewDecorator;
import manager.components.task_visualize.ViewComponent;
import manager.components.task_visualize.ViewComponentIterator;
import manager.ui.UserInterface;
import manager.ui.TableUserInterface;
import manager.components.Task;
import manager.database.DbConnection;
import manager.database.RethinkDbAdapter;

import java.util.ArrayList;
import java.util.List;

public class ManagerApp extends Application {

    private DbConnection dbConn;
    private UserInterface ui;
    private List<ViewComponent> tasks;
    private final int width = 1200;
    private final int height = 900;

    public ManagerApp(){
        ui = new TableUserInterface(width, height);
        tasks = new ArrayList<>();

        dbConn = new RethinkDbAdapter();

        List<Task> tasksData = dbConn.getTasks();
        for(var data : tasksData){
            tasks.add(
                    new BorderViewDecorator(
                        new BackgroundViewDecorator(
                            data
                    )));
        }
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Task Manager");
        stage.setScene(ui.getScene());
        ui.displayTasks(new ViewComponentIterator(tasks));
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
