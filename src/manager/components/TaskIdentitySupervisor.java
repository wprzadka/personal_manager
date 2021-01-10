package manager.components;

import manager.components.task_visualize.ViewComponent;

import java.util.List;

// Design Pattern Singleton
public class TaskIdentitySupervisor {

    private List<ViewComponent> componentsList;

    public TaskIdentitySupervisor(List<ViewComponent> componentsList){
        this.componentsList = componentsList;
    }

    private long findMaxIdentity(){
        long maxIdentity = -1;
        for(ViewComponent component : componentsList){
            long taskIdentity = component.getTask().getIdentity();
            if(taskIdentity > maxIdentity){
                maxIdentity = taskIdentity;
            }
        }
        return maxIdentity;
    }

    public long nextIdentity(){
        return findMaxIdentity() + 1;
    }


}
