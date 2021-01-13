package manager.controller;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import manager.actions.Action;
import manager.actions.AddTaskAction;
import manager.actions.EditTaskAction;
import manager.components.State;
import manager.components.Task;
import manager.configuration.Configuration;

import java.util.concurrent.atomic.AtomicBoolean;

public class EditComponentBox {

    private final TextField titleField;
    private final TextArea descriptionField;
    private final TextField typeField;
    private final ComboBox<State> stateList;

    private final AtomicBoolean isAccepted;
    private final Stage window;

    public EditComponentBox(){
        titleField = new TextField();
        descriptionField = new TextArea();
        typeField = new TextField();

        stateList = new ComboBox<State>();
        stateList.getItems().addAll(State.values());

        isAccepted = new AtomicBoolean();
        window = createWindow();
    }

    public Action editTask(Task task){

        titleField.setText(task.title);
        descriptionField.setText(task.description);
        typeField.setText(task.type);
        stateList.setValue(task.progressState);

        window.showAndWait();
        if(isAccepted.get()){
            return new EditTaskAction(
                    task,
                    titleField.getText(),
                    descriptionField.getText(),
                    typeField.getText(),
                    stateList.getValue()
            );
        }
        clearFields();
        return null;
    }

    public Action createTask(){

        window.showAndWait();
        if(isAccepted.get()){
            Action result = new AddTaskAction(
                    new Task(
                        Configuration.getInstance().getTaskIdentitySupervisor().nextIdentity(),
                        titleField.getText(),
                        descriptionField.getText(),
                        typeField.getText(),
                        stateList.getValue()
                    )
            );
            clearFields();
            return result;
        }else{
            return null;
        }
    }

    private void clearFields(){
        titleField.clear();
        descriptionField.clear();
        typeField.clear();
        stateList.setValue(State.TODO);
    }

    private Stage createWindow(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit Component");
        var layout = new GridPane();
        layout.setHgap(10);
        layout.setVgap(20);
        layout.setPadding(new Insets(20, 20, 0, 0));

        var titleLabel = new Label("title:");
        layout.add(titleLabel, 1, 0);
        layout.add(titleField, 2, 0, 2, 1);

        var descriptionLabel = new Label("description:");
        layout.add(descriptionLabel, 1, 1);
        layout.add(descriptionField, 2, 1, 2, 1);

        var typeLabel = new Label("type:");
        layout.add(typeLabel, 1, 2);
        layout.add(typeField, 2, 2, 2, 1);

        stateList.setValue(State.TODO);
        layout.add(stateList, 1, 3);

        var cancelButton = new Button("cancel");
        cancelButton.setOnAction(
                e -> {
                    isAccepted.set(false);
                    window.close();
                }
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
        return window;
    }
}
