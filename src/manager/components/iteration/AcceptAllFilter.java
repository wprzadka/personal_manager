package manager.components.iteration;

import manager.components.Task;

public class AcceptAllFilter implements TaskFilter{
    @Override
    public boolean satisfiesPredicate(Task task) {
        return true;
    }
}
