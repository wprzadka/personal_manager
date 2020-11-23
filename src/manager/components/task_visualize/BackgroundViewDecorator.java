package manager.components.task_visualize;


import javafx.scene.layout.Pane;

public class BackgroundViewDecorator extends ViewComponent {

    public BackgroundViewDecorator(ViewComponent component) {
        super(component);
    }

    @Override
    public Pane draw() {
        var layout = component.draw();

        layout.setStyle("-fx-background-color: linear-gradient(#3c6478, #2c5468);");
        return layout;
    }

}
