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
import javafx.scene.Cursor;

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

        int opBarHeight = 35;

        Pane columnsLayout = new HBox();
        columnsLayout.setPrefWidth(height);
        columnsLayout.setMinHeight(height - opBarHeight);
        String[] colNames = new String[]{"ToDo", "InProgress", "Done"};
        for(var name : colNames){
            columns.add(createColumn(name));
        }
        columnsLayout.getChildren().addAll(columns);

//        var synchronizeButton = new Button("Synchronize");


        HBox operationBar = new HBox();
//        --module-path javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml
        operationBar.getChildren().addAll(
                createOperationBarButton("Add", 262),
                createOperationBarButton("Sync", 642)
        );
//        operationBar.setPrefHeight(opBarHeight);
        operationBar.setAlignment(Pos.CENTER_RIGHT);
        operationBar.setPadding(new Insets(5, 10, 5, 10));
        operationBar.setSpacing(10);
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

    private Button createOperationBarButton(String text, int colorRgbCode){
        var button = new Button(text);

        int endColorVal = Math.max(colorRgbCode - 222, 0);
        String endColor = String.format("%03d", endColorVal);
        String begColor = String.format("%03d", colorRgbCode);

        int onHoverEndColorVal = Math.min(colorRgbCode + 111, 999);
        String onHoverEndColor = String.format("%03d", onHoverEndColorVal);
        int onHoverBegColorVal = Math.min(colorRgbCode + 111, 999);
        String onHoverBegColor = String.format("%03d", onHoverBegColorVal);

        button.setStyle(
                "-fx-background-color: linear-gradient(#" + begColor + ", #" + endColor + ");"
                + "-fx-text-fill: #aaa;"
        );
        button.setOnMouseEntered(
                event -> button.setStyle(
                        "-fx-background-color: linear-gradient(#" + onHoverBegColor + ", #" + onHoverEndColor + ");"
                        + "-fx-text-fill: #aaa;"
                )
        );
        button.setOnMouseExited(
                event -> button.setStyle(
                        "-fx-background-color: linear-gradient(#" + begColor + ", #" + endColor + ");"
                                + "-fx-text-fill: #aaa;"
                )
        );
        button.setCursor(Cursor.CLOSED_HAND);
        return button;
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
