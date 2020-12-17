package manager.controler;

import manager.components.task_visualize.ViewComponent;

// Design Pattern: Observer
public class DragAndDropObserver {

    private static final DragAndDropObserver instance = new DragAndDropObserver();

    MainEventsController controller;

    static public DragAndDropObserver getInstance(){
        return instance;
    }

    ViewComponent currentDragged = null;
    double[] dragged_from = new double[0];

    private DragAndDropObserver(){
        controller = MainEventsController.getInstance();
    }

    public void startDrag(ViewComponent dragged, double[] position){
        currentDragged = dragged;
        dragged_from = position;
    }

    public void dragEndingPosition(double[] position){
//        float[] drag_vector = new float[]{position[0] - dragged_from[0], position[1] - dragged_from[1]};
        controller.moveComponent(currentDragged, dragged_from, position);
        currentDragged = null;
    }
}