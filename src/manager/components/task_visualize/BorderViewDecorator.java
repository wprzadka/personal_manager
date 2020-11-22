package manager.components.task_visualize;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import manager.components.Task;

public class BorderViewDecorator implements ViewComponent {

    ViewComponent component;

    public BorderViewDecorator(ViewComponent subComponent){
        component = subComponent;
    }

    @Override
    public Pane draw() {
        Pane layout = component.draw();
        layout.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                new CornerRadii(4),
                BorderWidths.DEFAULT
        )));
        return layout;
    }

    @Override
    public Task getTask(){
        return component.getTask();
    }
}
