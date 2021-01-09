package manager.components.task_visualize;

import javafx.scene.layout.Pane;
import manager.controller.DragAndDropObserver;

public class DragViewDecorator extends ViewComponent {

    private DragAndDropObserver observer;

    public DragViewDecorator(ViewComponent component) {
        super(component);
        observer = DragAndDropObserver.getInstance();
    }

    @Override
    public Pane draw() {
        Pane pane = component.draw();

        pane.setOnDragDetected(
                event -> {
//                    System.out.println("begin: " + event.getSceneX());
                    pane.setVisible(false);
                    observer.startDrag(this, new double[]{event.getSceneX(), event.getSceneY()});
                }
        );

        pane.setOnMouseReleased(
                event -> {
//                    System.out.println("end: " + event.getSceneX());
                    pane.setVisible(true);
                    observer.dragEndingPosition(new double[]{event.getSceneX(), event.getSceneY()});
                }
        );
        return pane;
    }
}
