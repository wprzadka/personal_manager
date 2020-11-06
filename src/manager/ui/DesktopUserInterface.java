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

    int width;
    int height;

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

            var column = new VBox();
//            column.setStyle("-fx-background-color: #AABBCC;");

            column.setBackground(
                    new Background(
                            new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)
                    )
            );

            var title = new Text(name);
            title.setFont(Font.font(null, FontWeight.BOLD, 26));
            title.setCaretBias(true);

            column.getChildren().add(title);
            column.setPrefWidth(width / 3);
            column.setAlignment(Pos.TOP_CENTER);
            column.setStyle("-fx-border-style: dotted;");
            columns.add(column);
        }

        layout.getChildren().addAll(columns);

        return layout;
    }
}
