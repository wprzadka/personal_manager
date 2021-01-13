package manager.controller;


import manager.actions.Action;
import manager.actions.DeleteTaskAction;
import manager.actions.MoveTaskStateAction;
import manager.actions.register.ActionsRegister;
import manager.components.ContentContainer;
import manager.components.State;
import manager.components.Task;
import manager.components.iteration.TaskFilter;
import manager.components.task_visualize.*;
import manager.ui.UserInterface;

public class MainEventsController {

    private final FilterSettingsBox filterSettings = new FilterSettingsBox();
    private final EditComponentBox editComponentBox = new EditComponentBox();

    private final UserInterface userInterface;
    private final ContentContainer components;
    private final ActionsRegister actionsRegister;
    private TaskFilter filter;
    private int dragSensitivity = 100;

    public MainEventsController(
            UserInterface userInterface,
            ContentContainer components,
            ActionsRegister actionsRegister
    ){
        this.userInterface = userInterface;
        this.components = components;
        this.actionsRegister = actionsRegister;
    }

    public void moveComponent(ViewComponent component, double[] sourcePosition, double[] destinationPosition){

        double[] drag_vector = new double[]{
                destinationPosition[0] - sourcePosition[0],
                destinationPosition[1] - sourcePosition[1]
        };
        State newState = null;
        if(drag_vector[0] > dragSensitivity){
            newState = component.getTask().progressState.next();
        }else if(drag_vector[0] < -dragSensitivity){
            newState = component.getTask().progressState.prev();
        }
        if(newState != null) {
            actionsRegister.consumeAction(new MoveTaskStateAction(component.getTask(), newState));
            refresh();
        }
    }

    public void addNewComponent(){

        Action action = editComponentBox.createTask();
        if(action != null) {
            actionsRegister.consumeAction(action);
            refresh();
        }
    }

    public void editTask(Task task){
        Action action = editComponentBox.editTask(task);
        if(action != null) {
            actionsRegister.consumeAction(action);
            refresh();
        }
    }

    public void deleteTask(Task task){
        actionsRegister.consumeAction(new DeleteTaskAction(task));
        refresh();
    }

    public void editFilterSettings(){

        TaskFilter filter = filterSettings.getNewFilter();
        if(filter != null) {
            this.filter = filter;
            refresh();
        }
    }

    public void revertLastAction(){
        actionsRegister.revertLastAction();
        refresh();
    }

    public void refresh(){
        userInterface.refreshTasks(components.iterator(filter));
    }
}
