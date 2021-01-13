package manager.components.task_visualize;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import manager.configuration.Configuration;

public class DeleteViewDecorator extends ViewComponent{

    private final HBox deleteBox;

    public DeleteViewDecorator(ViewComponent component) {
        super(component);

        deleteBox = new HBox();
        deleteBox.getChildren().add(createXButton());
        deleteBox.setAlignment(Pos.BASELINE_RIGHT);
        deleteBox.setPadding(new Insets(5));
    }

    @Override
    public Pane draw() {
        Pane pane = component.draw();

        ObservableList<Node> elements = FXCollections.observableArrayList(pane.getChildren());
        pane.getChildren().clear();

        pane.getChildren().add(deleteBox);
        pane.getChildren().addAll(elements);

        return pane;
    }

    private Button createXButton(){
        var xButton = new Button("X");
        xButton.setOnAction(
                e -> Configuration.getInstance().getMainEventsController().deleteTask(getTask())
        );
        xButton.setStyle(
                "-fx-background-color: linear-gradient(#922, #700);"
                        + "-fx-text-fill: #aaa;"
        );
        xButton.setCursor(Cursor.OPEN_HAND);
        return xButton;
    }
}
