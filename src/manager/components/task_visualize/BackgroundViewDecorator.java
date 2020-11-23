package manager.components.task_visualize;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BackgroundViewDecorator extends ViewComponent {

    public BackgroundViewDecorator(ViewComponent component) {
        super(component);
    }

    @Override
    public Pane draw() {
        var layout = component.draw();
        /*
        layout.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.rgb(60, 100, 120),
                                new CornerRadii(4),
                                Insets.EMPTY)
        ));
        */
        layout.setStyle("-fx-background-color: linear-gradient(#3c6478, #2c5468);");
        return layout;
    }

}
