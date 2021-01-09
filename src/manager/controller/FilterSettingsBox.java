package manager.controller;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import manager.components.iteration.TaskFilter;
import manager.components.iteration.TaskTypeFilter;


import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

public class FilterSettingsBox {

    String typeFieldContent;

    public TaskFilter getNewFilter(){

        AtomicBoolean isAccepted = new AtomicBoolean(false);

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Filter settings");
        var layout = new GridPane();
        layout.setHgap(10);
        layout.setVgap(20);
        layout.setPadding(new Insets(20, 20, 0, 0));

        var titleLabel = new Label("regex filter:");
        var titleField = new TextField("-");
        titleField.setDisable(true);
        layout.add(titleLabel, 1, 0);
        layout.add(titleField, 2, 0, 2, 1);

        var typeLabel = new Label("type filter:");
        var typeField = new TextField(typeFieldContent);
        layout.add(typeLabel, 1, 1);
        layout.add(typeField, 2, 1, 2, 1);

        var cancelButton = new Button("cancel");
        cancelButton.setOnAction(
                e -> window.close()
        );
        layout.add(cancelButton, 1, 3);

        var acceptButton = new Button("accept");
        acceptButton.setOnAction(
                e -> {
                    isAccepted.set(true);
                    window.close();
                }
        );
        layout.add(acceptButton, 2, 3);

        var scene = new Scene(layout, 380, 600);
        window.setScene(scene);

        window.showAndWait();

        saveFieldsContent(typeField.getText());
        if(isAccepted.get()){
            return new TaskTypeFilter(Collections.singleton(typeField.getText()));
        }else{
            return null;
        }
    }

    private void saveFieldsContent(String typeFieldContent){
        this.typeFieldContent = typeFieldContent;
    };
}
