package manager.components.iteration;

import manager.components.Task;

//Design Pattern Strategy
public interface TaskFilter {

    boolean satisfiesPredicate(Task task);
}
