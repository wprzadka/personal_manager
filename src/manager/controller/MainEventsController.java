package manager.controller;


import manager.components.Task;
import manager.components.iteration.TaskFilter;
import manager.components.iteration.ViewComponentIterator;
import manager.components.task_visualize.*;
import manager.database.DbConnection;
import manager.ui.UserInterface;

import java.util.List;

public class MainEventsController {

    private final FilterSettingsBox filterSettings = new FilterSettingsBox();
    private final DbConnection dbConnection;
    private final UserInterface userInterface;
    private final List<ViewComponent> componentsList;
    private TaskFilter filter;

    public MainEventsController(
            DbConnection dbConn,
            UserInterface ui,
            List<ViewComponent> components
    ){
        dbConnection = dbConn;
        userInterface = ui;
        componentsList = components;
    }

    public void moveComponent(ViewComponent component, double[] sourcePosition, double[] destinationPosition){

        double[] drag_vector = new double[]{
                destinationPosition[0] - sourcePosition[0],
                destinationPosition[1] - sourcePosition[1]
        };
//        System.out.println(component.getTask().progressState);
        if(drag_vector[0] > 100){
            component.getTask().moveStateToNext();
        }else if(drag_vector[0] < -100){
            component.getTask().moveStateToPrev();
        }
        dbConnection.updateTask(component.getTask());
//        System.out.println(component.getTask().progressState);
        refresh();
    }

    public void addNewComponent(){

        ViewComponent createdComponent = EditComponentBox.CreateViewComponent();
        if(createdComponent != null) {
            componentsList.add(createdComponent);
            dbConnection.addTask(createdComponent.getTask());
            refresh();
        }
    }

    public void editTask(Task task){

        if(EditComponentBox.editTask(task)) {
            dbConnection.updateTask(task);
            refresh();
        }
    }

    public void editFilterSettings(){

        TaskFilter filter = filterSettings.getNewFilter();
        if(filter != null) {
            this.filter = filter;
            refresh();
        }
    }

    public void refresh(){
        userInterface.refreshTasks(new ViewComponentIterator(componentsList, filter));
    }
}
