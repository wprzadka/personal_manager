package manager.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.LinkedList;
import java.util.List;

public class DesktopUserInterface implements UserInterface {

    private int width;
    private int height;

    public DesktopUserInterface(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public Pane getLayout(){

        Pane layout = new HBox();
        String[] colNames = new String[]{"ToDo", "InProgress", "Done"};

        List<Pane> columns = new LinkedList<>();
        for(var name : colNames){

            columns.add(createColumn(name));
        }
        layout.getChildren().addAll(columns);

        return layout;
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
        column.setAlignment(Pos.TOP_CENTER);
        column.setStyle("-fx-border-style: solid;");
        return column;
    }
}
