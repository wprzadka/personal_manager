package manager;

import javafx.application.Application;
import javafx.stage.Stage;

import manager.components.task_visualize.BorderViewDecorator;
import manager.components.task_visualize.ViewComponent;
import manager.components.task_visualize.ViewComponentIterator;
import manager.ui.UserInterface;
import manager.ui.TableUserInterface;
import manager.components.Task;

import java.util.ArrayList;
import java.util.List;

public class ManagerApp extends Application {

    private UserInterface ui;
    private List<ViewComponent> tasks;
    private final int width = 1200;
    private final int height = 900;

    public ManagerApp(){
        ui = new TableUserInterface(width, height);
        tasks = new ArrayList<>();
        var initial = new Task("test");
        initial.setDescription("initial task, don't care about it");
        initial.setType("useless");
        tasks.add(new BorderViewDecorator(initial));
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Task Manager");
        stage.setScene(ui.getScene());
        ui.displayTasks(new ViewComponentIterator(tasks));
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
