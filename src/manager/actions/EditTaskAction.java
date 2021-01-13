package manager.actions;

import manager.components.State;
import manager.components.Task;
import manager.components.TaskMemento;
import manager.configuration.Configuration;

public class EditTaskAction implements Action{

    private final Task target;
    private final TaskMemento beforeEdit;

    private final String newTitle;
    private final String newDescription;
    private final String newType;
    private final State newProgressState;

    // Design Patten Command
    public EditTaskAction(
            Task target,
            String title,
            String description,
            String type,
            State state
    ){
        this.target = target;
        beforeEdit = target.save();

        newTitle = title;
        newDescription = description;
        newType = type;
        newProgressState = state;
    }

    @Override
    public void execute() {

        target.title = newTitle;
        target.description = newDescription;
        target.type = newType;
        target.progressState = newProgressState;
        Configuration.getInstance().getDbConnection().updateTask(target);
    }

    @Override
    public void revert() {
//        System.out.println("REVERT EDIT");
        beforeEdit.restore();
        Configuration.getInstance().getDbConnection().updateTask(target);
    }
}
