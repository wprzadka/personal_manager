package manager.components.iteration;

import manager.components.Task;

import java.util.Collection;
import java.util.List;

public class TaskTypeFilter implements TaskFilter{

    private final List<String> acceptedList;

    public TaskTypeFilter(Collection<? extends String> accepted){
        acceptedList = List.copyOf(accepted);
    }

    @Override
    public boolean satisfiesPredicate(Task task) {
        for(var type : acceptedList){
            if(task.type.equals(type)){
                return true;
            }
        }
        return false;
    }
}
