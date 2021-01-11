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
import manager.configuration.Configuration;

import java.util.concurrent.atomic.AtomicBoolean;

public class EditComponentBox {

    private TextField titleField;
    private TextArea descriptionField;
    private TextField typeField;
    private ComboBox<State> stateList;

    private AtomicBoolean isAccepted;
    private Stage window;

    public EditComponentBox(){
        titleField = new TextField();
        descriptionField = new TextArea();
        typeField = new TextField();

        stateList = new ComboBox<State>();
        stateList.getItems().addAll(State.values());

        isAccepted = new AtomicBoolean();
        window = createWindow();
    }

    public boolean editTask(Task task){

        titleField.setText(task.title);
        descriptionField.setText(task.description);
        typeField.setText(task.type);
        stateList.setValue(task.progressState);

        window.showAndWait();
        if(isAccepted.get()){
            task.setTitle(titleField.getText());
            task.setDescription(descriptionField.getText());
            task.setType(typeField.getText());
            task.progressState = stateList.getValue();
        }
        clearFields();
        return isAccepted.get();
    }

    public ViewComponent createViewComponent(){

        window.showAndWait();
        if(isAccepted.get()){
            ViewComponent result = new EditorViewDecorator(new DragViewDecorator(new BackgroundViewDecorator(new BorderViewDecorator(new Task(
                    Configuration.getInstance().getTaskIdentitySupervisor().nextIdentity(),
                    titleField.getText(),
                    descriptionField.getText(),
                    typeField.getText(),
                    stateList.getValue()
            )))));
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
        stateList.setValue(State.DONE);
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
