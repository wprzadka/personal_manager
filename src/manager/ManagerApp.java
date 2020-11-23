package manager;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import manager.components.State;
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

    private final DbConnection dbConn;
    private final UserInterface ui;
    private final List<ViewComponent> tasks;
    private final int width = 1200;
    private final int height = 900;

    public ManagerApp(){
        ui = new TableUserInterface(width, height);
        tasks = new ArrayList<>();

        dbConn = new RethinkDbAdapter();

        List<Task> tasksData = dbConn.getTasks();

        for(int i = 0; i < tasksData.size(); ++i){
            if(i % 5 == 0) {
                tasksData.get(i).progressState = State.IN_PROGRES;
            }
            else if(i%3 == 0){
                tasksData.get(i).progressState = State.REVIEW;
            }
        }

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
