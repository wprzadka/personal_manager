package manager;

import javafx.application.Application;
import javafx.stage.Stage;

import manager.components.State;
import manager.components.task_visualize.*;
import manager.ui.UserInterface;
import manager.ui.TableUserInterface;
import manager.components.Task;
import manager.database.DbConnection;
import manager.database.RethinkDbAdapter;
import manager.controler.MainEventsController;

import java.util.ArrayList;
import java.util.List;

public class ManagerApp extends Application {

    private final MainEventsController controller = MainEventsController.getInstance();;
    private final DbConnection dbConn;
    private final UserInterface ui;
    private final List<ViewComponent> tasks;
    private final int width = 1200;
    private final int height = 900;

    public ManagerApp(){
        controller.setApplication(this);
        ui = new TableUserInterface(width, height);
        tasks = new ArrayList<>();

        dbConn = new RethinkDbAdapter();

        List<Task> tasksData = dbConn.getTasks();

        // TODO remove temporary states change
        for(int i = 0; i < tasksData.size(); ++i){
            if(i % 5 == 0) {
                tasksData.get(i).progressState = State.IN_PROGRESS;
            }
            else if(i%3 == 0){
                tasksData.get(i).progressState = State.REVIEW;
            }
        }

        for(var data : tasksData){
            tasks.add(
                    new DragViewDecorator(
                        new BorderViewDecorator(
                            new BackgroundViewDecorator(
                                data
                    ))));
        }
    }

    public void refresh(){
        ui.refreshTasks(new ViewComponentIterator(tasks));
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Task Manager");
        stage.setScene(ui.getScene());
        ui.refreshTasks(new ViewComponentIterator(tasks));
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
