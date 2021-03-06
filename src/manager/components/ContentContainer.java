package manager.components;

import manager.components.iteration.AcceptAllFilter;
import manager.components.iteration.TaskFilter;
import manager.components.iteration.ViewComponentIterator;
import manager.components.task_visualize.*;
import manager.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ContentContainer {
    private final List<ViewComponent> viewComponents = new ArrayList<>();

    public void loadComponents(){
        List<Task> tasksData = Configuration.getInstance().getDbConnection().getTasks();
        for(var data : tasksData){
            viewComponents.add(createViewOfTask(data));
        }
    }

    public List<ViewComponent> getViewComponents(){
        return viewComponents;
    }

    public void addTask(Task task){
        viewComponents.add(createViewOfTask(task));
    }

    public boolean removeTask(Task task){
        for(ViewComponent view : viewComponents){
            if(view.getTask() == task){
                viewComponents.remove(view);
                return true;
            }
        }
        return false;
    }

    public ViewComponentIterator iterator(TaskFilter filter){
        return new ViewComponentIterator(viewComponents, filter);
    }

    public ViewComponentIterator iterator(){
        return new ViewComponentIterator(viewComponents, null);
    }

    private ViewComponent createViewOfTask(Task task){
        return new DeleteViewDecorator(new EditorViewDecorator(new DragViewDecorator(
                        new BorderViewDecorator(new BackgroundViewDecorator(task)))));
    }

}
