package manager.actions;

import manager.components.Task;
import manager.configuration.Configuration;

public class EditTaskAction implements Action{

    private final Task target;

    // Design Patten Command
    public EditTaskAction(Task target){
        this.target = target;
    }

    @Override
    public void execute() {
//        System.out.println("EditTask -> " + target.getIdentity());
        Configuration.getInstance().getDbConnection().updateTask(target);
    }

    @Override
    public void revert() {

    }
}
