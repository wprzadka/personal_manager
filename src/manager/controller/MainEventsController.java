package manager.controller;


import manager.ManagerApp;
import manager.components.task_visualize.BackgroundViewDecorator;
import manager.components.task_visualize.BorderViewDecorator;
import manager.components.task_visualize.DragViewDecorator;
import manager.components.task_visualize.ViewComponent;
import manager.database.DbConnection;

import java.util.List;

// Design Pattern: Singleton
public class MainEventsController {

    private static final MainEventsController instance = new MainEventsController();
    private ManagerApp application;
    private DbConnection dbConnection;
    private List<ViewComponent> componentsList;
    private boolean isInitialized = false;

    private MainEventsController(){}

    public static MainEventsController getInstance(){
        return instance;
    }

    public void initialize(ManagerApp app, DbConnection dbConn, List<ViewComponent> components){
        if(isInitialized){
            throw new RuntimeException("MainEventsController had been already initialized");
        }
        application = app;
        dbConnection = dbConn;
        componentsList = components;
        isInitialized = true;
    }

    public void moveComponent(ViewComponent component, double[] sourcePosition, double[] destinationPosition){
        if(!isInitialized){
            throw new RuntimeException("MainEventsController is not initialized");
        }
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
//        System.out.println(component.getTask().progressState);
        application.refresh();
    }

    public void addNewComponent(){
        if(!isInitialized){
            throw new RuntimeException("MainEventsController is not initialized");
        }
        ViewComponent createdComponent = EditComponentBox.CreateViewComponent();
        if(createdComponent != null) {
            componentsList.add(
                    new DragViewDecorator(
                            new BorderViewDecorator(
                                    new BackgroundViewDecorator(
                                            createdComponent
            ))));
            application.refresh();
        }
    }

}
