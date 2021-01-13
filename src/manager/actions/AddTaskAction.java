package manager.actions;

import manager.components.Task;
import manager.configuration.Configuration;

// Design Patten Command
public class AddTaskAction implements Action{

    private final Task target;

    public AddTaskAction(Task target){
        this.target = target;
    }

    @Override
    public void execute() {
//        System.out.println("AddTask -> " + target.getIdentity());
        Configuration.getInstance().getContentContainer().addTask(target);
        Configuration.getInstance().getDbConnection().addTask(target);
    }

    @Override
    public void revert() {
        if(Configuration.getInstance().getContentContainer().removeTask(target)) {
            Configuration.getInstance().getDbConnection().deleteTask(target);
        }
    }
}
