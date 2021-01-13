package manager.actions;

import manager.components.Task;
import manager.components.TaskMemento;
import manager.configuration.Configuration;

public class EditTaskAction implements Action{

    private final Task target;
    private final TaskMemento beforeEdit;

    // Design Patten Command
    public EditTaskAction(Task target){
        this.target = target;
        beforeEdit = target.save();
    }

    @Override
    public void execute() {
        Configuration.getInstance().getDbConnection().updateTask(target);
    }

    @Override
    public void revert() {
//        System.out.println("REVERT EDIT");
        beforeEdit.restore();
        Configuration.getInstance().getDbConnection().updateTask(target);
    }
}
