package manager.components;

import manager.components.task_visualize.*;
import manager.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ContentContainer {
    private final List<ViewComponent> viewComponents = new ArrayList<>();

    public void loadComponents(){
        List<Task> tasksData = Configuration.getInstance().getDbConnection().getTasks();
        for(var data : tasksData){
            viewComponents.add(
                    new EditorViewDecorator(
                            new DragViewDecorator(
                                    new BorderViewDecorator(
                                            new BackgroundViewDecorator(
                                                    data
                                            )))));
        }
    }

    public List<ViewComponent> getViewComponents(){
        return viewComponents;
    }

}
