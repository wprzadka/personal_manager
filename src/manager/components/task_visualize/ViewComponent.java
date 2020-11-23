package manager.components.task_visualize;

import javafx.scene.layout.Pane;
import manager.components.Task;

public abstract class ViewComponent {

    protected ViewComponent component;

    public ViewComponent(ViewComponent component){
        this.component = component;
    }

    abstract public Pane draw();

    public Task getTask(){
        return component.getTask();
    }
}
