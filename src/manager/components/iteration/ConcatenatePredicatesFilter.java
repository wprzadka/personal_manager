package manager.components.iteration;

import manager.components.Task;

import java.util.Collection;
import java.util.List;

public class ConcatenatePredicatesFilter implements TaskFilter{

    private List<TaskFilter> filtersList;

    public ConcatenatePredicatesFilter(Collection<? extends TaskFilter> filters){
        filtersList = List.copyOf(filters);
    }

    @Override
    public boolean satisfiesPredicate(Task task) {
        for(TaskFilter filter : filtersList){
            if(!filter.satisfiesPredicate(task)){
                return false;
            }
        }
        return true;
    }
}
