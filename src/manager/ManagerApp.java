package manager;

import javafx.application.Application;
import javafx.stage.Stage;

import manager.components.iteration.TaskFilter;
import manager.components.iteration.ViewComponentIterator;
import manager.components.task_visualize.*;
import manager.ui.UserInterface;
import manager.ui.TableUserInterface;
import manager.components.Task;
import manager.database.DbConnection;
import manager.database.RethinkDbAdapter;
import manager.controller.MainEventsController;

import java.util.ArrayList;
import java.util.List;

public class ManagerApp extends Application {

    private final MainEventsController controller = MainEventsController.getInstance();
    private final DbConnection dbConn;
    private final UserInterface ui;
    private final List<ViewComponent> components;
    private TaskFilter filter;
    private final int width = 1200;
    private final int height = 900;

    public ManagerApp(){
        ui = new TableUserInterface(width, height);
        components = new ArrayList<>();
        dbConn = new RethinkDbAdapter();
        controller.initialize(this, dbConn, components);

        List<Task> tasksData = dbConn.getTasks();

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

    public void setFilter(TaskFilter filter){
        this.filter = filter;
    }

    public void refresh(){
        ui.refreshTasks(new ViewComponentIterator(components, filter));
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Task Manager");
        stage.setScene(ui.getScene());
        refresh();
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
