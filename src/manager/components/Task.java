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

    private long identity;
    private SubTask subComponents;

    public Task(long identity, String title, String description, String type, State state){
        super(null);
        this.identity = identity;
        this.title = title;
        this.description = description;
        this.type = type;
        this.progressState = state;
    }

    public Task(long identity, String title){
        super(null);
        this.identity = identity;
        this.title = title;
        this.description = "";
        this.type = "";
        this.progressState = State.TODO;
    }

    public long getIdentity() {
        return identity;
    }

    public TaskIterator iterator(){
        return new TaskIterator(subComponents);
    }

    public TaskMemento save(){
        return new TaskMemento(this, title, description, type, progressState);
    }

    public void restoreFrom(TaskMemento memento){
        title = memento.getTitle();
        description = memento.getDescription();
        type = memento.getType();
        progressState = memento.getProgressState();
//        System.out.println("RESTORED: " + getIdentity());
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

