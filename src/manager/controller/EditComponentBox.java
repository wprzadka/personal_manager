package manager.controller;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manager.components.State;
import manager.components.Task;
import manager.components.task_visualize.BackgroundViewDecorator;
import manager.components.task_visualize.ViewComponent;

import java.util.concurrent.atomic.AtomicBoolean;

public class EditComponentBox {

    //Design Pattern Builder
    public static ViewComponent CreateViewComponent(){

        AtomicBoolean isAccepted = new AtomicBoolean(false);

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit Component");
        var layout = new GridPane();
        layout.setHgap(10);
        layout.setVgap(20);
        layout.setPadding(new Insets(20, 20, 0, 0));

        var titleLabel = new Label("title:");
        var titleField = new TextField();
        layout.add(titleLabel, 1, 0);
        layout.add(titleField, 2, 0, 2, 1);

        var descriptionLabel = new Label("description:");
        var descriptionField = new TextArea();
        layout.add(descriptionLabel, 1, 1);
        layout.add(descriptionField, 2, 1, 2, 1);

        var typeLabel = new Label("type:");
        var typeField = new TextField();
        layout.add(typeLabel, 1, 2);
        layout.add(typeField, 2, 2, 2, 1);

        var stateList = new ComboBox<State>();
        stateList.getItems().addAll(State.values());
        stateList.setValue(State.TODO);
        layout.add(stateList, 1, 3);

        var cancelButton = new Button("cancel");
        cancelButton.setOnAction(
                e -> window.close()
        );
        layout.add(cancelButton, 1, 5);

        var acceptButton = new Button("accept");
        acceptButton.setOnAction(
                e -> {
                    isAccepted.set(true);
                    window.close();
                }
        );
        layout.add(acceptButton, 2, 5);

        var scene = new Scene(layout, 380, 600);
        window.setScene(scene);

        window.showAndWait();
        if(isAccepted.get()){
            return new BackgroundViewDecorator(new Task(
                    titleField.getText(),
                    descriptionField.getText(),
                    typeField.getText(),
                    stateList.getValue()
            ));
        }else{
            return null;
        }
    }
}
