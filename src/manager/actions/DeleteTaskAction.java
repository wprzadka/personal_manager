package manager.actions;

import manager.components.Task;
import manager.components.TaskMemento;
import manager.configuration.Configuration;

// Design Patten Command
public class DeleteTaskAction implements Action {

    private final Task target;
    private final TaskMemento beforeDelete;

    public DeleteTaskAction(Task target){
        this.target = target;
        beforeDelete = target.save();
    }

    @Override
    public void execute() {
        if(Configuration.getInstance().getContentContainer().removeTask(target)) {
            Configuration.getInstance().getDbConnection().deleteTask(target);
        }else{
            System.out.println("Can't remove task");
        }
    }

    @Override
    public void revert() {
        // we can't restore memento directly because we don't know if source task still exist
        // We also want to take new identity number
        var task = new Task(
                Configuration.getInstance().getTaskIdentitySupervisor().nextIdentity(),
                beforeDelete.getTitle(),
                beforeDelete.getDescription(),
                beforeDelete.getType(),
                beforeDelete.getProgressState()
        );
        Configuration.getInstance().getContentContainer().addTask(task);
        Configuration.getInstance().getDbConnection().addTask(task);
    }
}
