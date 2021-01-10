package manager.components;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import manager.components.task_visualize.ViewComponent;


public class Task extends ViewComponent {

    public String title;
    public String description;
    public String type;
    public State progressState;

    private SubTask subComponents;

    public Task(String title, String description, String type, State state){
        super(null);
        this.title = title;
        this.description = description;
        this.type = type;
        this.progressState = state;
    }

    public Task(String title){
        super(null);
        this.title = title;
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

    public void moveStateToNext(){
        progressState = progressState.next(progressState);
    }

    public void moveStateToPrev(){
        progressState = progressState.prev(progressState);
    }

    public void setNeedsReview(boolean isNeeded){
        progressState.setNeedsReview(isNeeded);
    }

    public boolean needsReview(){
        return progressState.needsReview;
    }

    @Override
    public Task getTask(){
        return this;
    }

    @Override
    public Pane draw() {
        var pane = new VBox();

        var typeText = new Text(type);
        typeText.setFont(Font.font(null, 20));
        var titleText = new Text(title);
        titleText.setFont(Font.font(null, FontWeight.BOLD, 24));
        var descriptionText = new Text(description);
        descriptionText.setFont(Font.font(null, 20));

        pane.getChildren().addAll(
                typeText,
                titleText,
                descriptionText
        );
        return pane;
    }
}

