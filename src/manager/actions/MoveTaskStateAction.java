package manager.actions;

import manager.components.State;
import manager.components.Task;

public class MoveTaskStateAction extends EditTaskAction{
    public MoveTaskStateAction(Task target, State state) {
        super(target, target.title, target.description, target.type, state);
    }
}
