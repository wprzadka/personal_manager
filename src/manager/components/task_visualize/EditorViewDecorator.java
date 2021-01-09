package manager.components.task_visualize;

import javafx.scene.layout.Pane;
import manager.controller.MainEventsController;

public class EditorViewDecorator extends ViewComponent {

    public EditorViewDecorator(ViewComponent component) {
        super(component);
    }

    // TODO clone during edit?
    @Override
    public Pane draw() {

        Pane pane = component.draw();

        pane.setOnMouseClicked(e -> MainEventsController.getInstance().editTask(component.getTask()));

        return pane;
    }
}
