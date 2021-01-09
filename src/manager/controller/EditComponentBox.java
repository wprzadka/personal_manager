package manager.controller;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manager.components.State;
import manager.components.Task;
import manager.components.task_visualize.*;

import java.util.concurrent.atomic.AtomicBoolean;

public class EditComponentBox {

    public static boolean editTask(Task task){
        AtomicBoolean isAccepted = new AtomicBoolean(false);

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit Component");
        var layout = new GridPane();
        layout.setHgap(10);
        layout.setVgap(20);
        layout.setPadding(new Insets(20, 20, 0, 0));

        var titleLabel = new Label("title:");
        var titleField = new TextField(task.title);
        layout.add(titleLabel, 1, 0);
        layout.add(titleField, 2, 0, 2, 1);

        var descriptionLabel = new Label("description:");
        var descriptionField = new TextArea(task.description);
        layout.add(descriptionLabel, 1, 1);
        layout.add(descriptionField, 2, 1, 2, 1);

        var typeLabel = new Label("type:");
        var typeField = new TextField(task.type);
        layout.add(typeLabel, 1, 2);
        layout.add(typeField, 2, 2, 2, 1);

        var stateList = new ComboBox<State>();
        stateList.getItems().addAll(State.values());
        stateList.setValue(task.progressState);
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
            task.setTitle(titleField.getText());
            task.setDescription(descriptionField.getText());
            task.setType(typeField.getText());
            task.progressState = stateList.getValue();
        }
        return isAccepted.get();
    }

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
            // TODO add DragAndDropViewDecorator
            return new EditorViewDecorator(new BackgroundViewDecorator(new BorderViewDecorator(new Task(
                    titleField.getText(),
                    descriptionField.getText(),
                    typeField.getText(),
                    stateList.getValue()
            ))));
        }else{
            return null;
        }
    }
}
