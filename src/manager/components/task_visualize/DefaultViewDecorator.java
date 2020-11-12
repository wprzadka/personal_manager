package manager.components.task_visualize;

import javafx.scene.layout.Pane;

public class DefaultViewDecorator implements ViewComponent {

    ViewComponent component;

    public DefaultViewDecorator(ViewComponent subComponent){
        component = subComponent;
    }

    @Override
    public Pane draw() {
        return component.draw();
    }
}
