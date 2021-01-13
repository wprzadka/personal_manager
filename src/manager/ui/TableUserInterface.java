package manager.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

import manager.components.iteration.ViewComponentIterator;
import manager.configuration.Configuration;

import java.util.LinkedList;
import java.util.List;

public class TableUserInterface implements UserInterface {

    private int width;
    private int height;
    private final List<Pane> columns;

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
                createOperationBarButton("Add", 262,
                        mouseEvent -> Configuration.getInstance().getMainEventsController().addNewComponent()
                ),
                createOperationBarButton("Sync", 642, null),
                createOperationBarButton("Filter", 333,
                        mouseEvent -> Configuration.getInstance().getMainEventsController().editFilterSettings()
                ),
                createOperationBarButton("Revert", 842,
                        mouseEvent -> Configuration.getInstance().getMainEventsController().revertLastAction()
                )
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
        column.setSpacing(5);
        column.setAlignment(Pos.TOP_CENTER);
        return column;
    }

    private Button createOperationBarButton(String text, int colorRgbCode, EventHandler<ActionEvent> onClickHandler){
        var button = new Button(text);

        int endColorVal = Math.max(colorRgbCode - 222, 0);
        String endColor = String.format("%03d", endColorVal);
        String begColor = String.format("%03d", colorRgbCode);

        int onHoverEndColorVal = Math.min(endColorVal + 111, 999);
        String onHoverEndColor = String.format("%03d", onHoverEndColorVal);
        int onHoverBegColorVal = Math.min(colorRgbCode + 111, 999);
        String onHoverBegColor = String.format("%03d", onHoverBegColorVal);

        button.setStyle(
                "-fx-background-color: linear-gradient(#" + begColor + ", #" + endColor + ");"
                + "-fx-text-fill: #aaa;"
        );
        button.setOnMouseEntered(
                event -> button.setStyle(
                        "-fx-background-color: linear-gradient(#" + onHoverEndColor + ", #" + onHoverBegColor + ");"
                        + "-fx-text-fill: #aaa;"
                )
        );
        button.setOnMouseExited(
                event -> button.setStyle(
                        "-fx-background-color: linear-gradient(#" + begColor + ", #" + endColor + ");"
                                + "-fx-text-fill: #aaa;"
                )
        );
        button.setOnAction(onClickHandler);
        button.setCursor(Cursor.CLOSED_HAND);
        return button;
    }

    public void refreshTasks(ViewComponentIterator iter){
        for(var col : columns){
            var name = col.getChildren().get(0);
            col.getChildren().clear();
            col.getChildren().add(name);
        }
        displayTasks(iter);
    }

    private void displayTasks(ViewComponentIterator iter){
        while(iter.hasNext()){
            var component = iter.next();
            int columnNum = component.getTask().progressState.getStateLevel();
            // TODO create special column for unmatched states?
            if (columnNum >= columns.size()){
                columnNum = columns.size() - 1;
            }
//            if(columns.get(columnNum).getChildren().size() < 9) {
            Pane view = component.draw();
            columns.get(columnNum).getChildren().add(view);
//            }
        }
    }
}
