package manager.controller;


import manager.actions.AddTaskAction;
import manager.actions.EditTaskAction;
import manager.actions.history.ActionsRegister;
import manager.components.Task;
import manager.components.iteration.TaskFilter;
import manager.components.iteration.ViewComponentIterator;
import manager.components.task_visualize.*;
import manager.ui.UserInterface;

import java.util.List;

public class MainEventsController {

    private final FilterSettingsBox filterSettings = new FilterSettingsBox();
    private final EditComponentBox editComponentBox = new EditComponentBox();

    private final UserInterface userInterface;
    private final List<ViewComponent> componentsList;
    private final ActionsRegister actionsRegister;
    private TaskFilter filter;
    private int dragSensitivity = 100;

    public MainEventsController(
            UserInterface ui,
            List<ViewComponent> components,
            ActionsRegister history
    ){
        userInterface = ui;
        componentsList = components;
        actionsRegister = history;
    }

    public void moveComponent(ViewComponent component, double[] sourcePosition, double[] destinationPosition){

        double[] drag_vector = new double[]{
                destinationPosition[0] - sourcePosition[0],
                destinationPosition[1] - sourcePosition[1]
        };
        if(drag_vector[0] > dragSensitivity){
            component.getTask().moveStateToNext();
        }else if(drag_vector[0] < -dragSensitivity){
            component.getTask().moveStateToPrev();
        }
        actionsRegister.consumeAction(new EditTaskAction(component.getTask()));
        refresh();
    }

    public void addNewComponent(){

        Task createdTask = editComponentBox.createTask();
        if(createdTask != null) {
            actionsRegister.consumeAction(new AddTaskAction(createdTask.getTask()));
            refresh();
        }
    }

    public void editTask(Task task){

        if(editComponentBox.editTask(task)) {
            actionsRegister.consumeAction(new EditTaskAction(task));
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
