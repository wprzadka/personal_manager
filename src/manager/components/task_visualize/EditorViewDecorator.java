package manager.components.task_visualize;

import javafx.scene.layout.Pane;
import manager.configuration.Configuration;

public class EditorViewDecorator extends ViewComponent {

    public EditorViewDecorator(ViewComponent component) {
        super(component);
    }

    @Override
    public Pane draw() {

        Pane pane = component.draw();

        pane.setOnMouseClicked(
                e -> Configuration.getInstance().getMainEventsController().editTask(component.getTask())
        );

        return pane;
    }
}
