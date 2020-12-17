package manager.controler;


import manager.ManagerApp;
import manager.components.task_visualize.ViewComponent;

// Design Pattern: Singleton (?)
public class MainEventsController {

    private static final MainEventsController instance = new MainEventsController();
    private ManagerApp application;

    private MainEventsController(){}

    public static MainEventsController getInstance(){
        return instance;
    }

    public void setApplication(ManagerApp app){
        application = app;
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
//        System.out.println(component.getTask().progressState);
        application.refresh();
    }

}
