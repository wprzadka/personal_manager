package manager.components.task_visualize;

import javafx.scene.layout.Pane;

public class EditorViewDecorator extends ViewComponent {

    public EditorViewDecorator(ViewComponent component) {
        super(component);
    }

    // TODO clone during edit?
    @Override
    public Pane draw() {
        return component.draw();
    }
}
