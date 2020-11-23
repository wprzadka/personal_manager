package manager.components.task_visualize;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class DragViewDecorator extends ViewComponent {

    public DragViewDecorator(ViewComponent component) {
        super(component);
    }

    @Override
    public Pane draw() {
        Pane pane = component.draw();

        pane.setOnDragDetected(
                event -> {
                    System.out.println("begin: " + event.getSceneX());
                    pane.setVisible(false);
                }
        );

        pane.setOnMouseReleased(
                event -> {
                    System.out.println("end: " + event.getSceneX());
                    pane.setVisible(true);
                }
        );

        return pane;
    }
}
