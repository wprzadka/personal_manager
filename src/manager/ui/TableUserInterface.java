package manager.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import manager.components.task_visualize.ViewComponentIterator;

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

        Pane layout = new HBox();
        String[] colNames = new String[]{"ToDo", "InProgress", "Done"};

        for(var name : colNames){
            columns.add(createColumn(name));
        }
        layout.getChildren().addAll(columns);

        return new Scene(layout, width, height);
    }

    private VBox createColumn(String name){
        var column = new VBox();

        column.setBackground(
                new Background(
                        new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)
                )
        );
        var title = new Text(name);
        title.setFont(Font.font(null, FontWeight.BOLD, 26));

        column.getChildren().add(title);
        column.setPrefWidth(width);
        column.setPadding(new Insets(8));
        column.setAlignment(Pos.TOP_CENTER);
        column.setStyle("-fx-border-style: solid;");
        return column;
    }

    public void displayTasks(ViewComponentIterator iter){
        while(iter.hasNext()){
            var block = iter.next().draw();
            columns.get(0).getChildren().add(block);
        }
    }
}
