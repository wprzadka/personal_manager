package manager.components.task_visualize;

import javafx.scene.layout.Pane;

public class EditorViewDecorator implements ViewComponent {

    ViewComponent component;

    public EditorViewDecorator(ViewComponent subComponent){
        component = subComponent;
    }

    @Override
    public Pane draw() {
        return component.draw();
    }
}
