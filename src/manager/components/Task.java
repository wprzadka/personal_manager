package manager.components;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import manager.components.task_visualize.ViewComponent;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class Task implements ViewComponent {

    private String title;
    private String description;
    private String type;
    private State progressState;

    private SubTask subComponents;

    public Task(String title){
        this.title = title;
        this.description = "";
        this.type = "";
        this.progressState = State.TODO;
    }

    public Task(){
        this.title = "";
        this.description = "";
        this.type = "";
        this.progressState = State.TODO;
    }

    public TaskIterator iterator(){
        return new TaskIterator(subComponents);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean moveStateTo(State next){
        return progressState.changeStateIfAvailable(next);
    }

    public void setNeedsReview(boolean isNeeded){
        progressState.setNeedsReview(isNeeded);
    }

    public boolean needsReview(){
        return progressState.needsReview;
    }

    @Override
    public Pane draw() {
        var pane = new VBox();
        var titleText = new Text(title);
        titleText.setTextAlignment(TextAlignment.CENTER);

        pane.getChildren().addAll(
                new Text(type),
                titleText,
                new Text(description)
        );
        return pane;
    }
}

