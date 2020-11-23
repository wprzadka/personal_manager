package manager.components.task_visualize;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class BorderViewDecorator extends ViewComponent {

    public BorderViewDecorator(ViewComponent component) {
        super(component);
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
}
