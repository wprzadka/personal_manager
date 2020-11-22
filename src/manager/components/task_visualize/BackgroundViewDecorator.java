package manager.components.task_visualize;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import manager.components.Task;

public class BackgroundViewDecorator implements ViewComponent {

    ViewComponent component;

    public BackgroundViewDecorator(ViewComponent subComponent){
        component = subComponent;
    }

    @Override
    public Pane draw() {
        var layout = component.draw();
        layout.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(60, 100, 120),
                                new CornerRadii(4),
                                Insets.EMPTY)
        ));
        return layout;
    }

    @Override
    public Task getTask(){
        return component.getTask();
    }
}
