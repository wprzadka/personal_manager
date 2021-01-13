package manager.components;

//Design Pattern Memento
public class TaskMemento {

    private final Task target;
    private final String title;
    private final String description;
    private final String type;
    private final State progressState;

    public TaskMemento(
            Task target,
            String title,
            String description,
            String type,
            State progressState
    ){
        this.target = target;
        this.title = title;
        this.description = description;
        this.type = type;
        this.progressState = progressState;
    }

    public void restore(){
        target.restoreFrom(this);
    }

    public String getTitle(){
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public State getProgressState() {
        return progressState;
    }
}
