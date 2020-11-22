package manager.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import manager.components.task_visualize.ViewComponentIterator;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class TableUserInterface implements UserInterface {

    private int width;
    private int height;
    private List<Pane> columns;

    public TableUserInterface(int width, int height){
        this.width = width;
        this.height = height;
        columns = new LinkedList<>();
    }

    @Override
    public Scene getScene(){

        Pane columnsLayout = new HBox();
        columnsLayout.setPrefWidth(height);
        columnsLayout.setMinHeight(height - 25);
        String[] colNames = new String[]{"ToDo", "InProgress", "Done"};
        for(var name : colNames){
            columns.add(createColumn(name));
        }
        columnsLayout.getChildren().addAll(columns);

//        var synchronizeButton = new Button("Synchronize");

        Pane operationBar = new HBox();

        var add = new Text("Add Task");
        operationBar.getChildren().add(add);
        operationBar.setPrefHeight(25);
        operationBar.setBackground(
                new Background(
                        new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, Insets.EMPTY)
                )
        );

        Pane mainLayout = new VBox();
        mainLayout.getChildren().addAll(columnsLayout, operationBar);
        mainLayout.setBackground(
                new Background(
                        new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)
                )
        );
        return new Scene(mainLayout, width, height);
    }

    private Pane createColumn(String name){
        var column = new VBox();

        var title = new Text(name);
        title.setFont(Font.font(null, FontWeight.BOLD, 26));

        column.getChildren().add(title);
        column.setStyle("-fx-border-style: solid;");
        column.setPrefWidth(width);
        column.setPadding(new Insets(8));
        column.setAlignment(Pos.TOP_CENTER);
        return column;
    }

    public void displayTasks(ViewComponentIterator iter){
        while(iter.hasNext()){
            var component = iter.next();
            int columnNum = component.getTask().progressState.getStateLevel();
            if(columns.get(columnNum).getChildren().size() < 5) {
                Pane view = component.draw();
                columns.get(columnNum).getChildren().add(view);
            }
        }
    }
}
