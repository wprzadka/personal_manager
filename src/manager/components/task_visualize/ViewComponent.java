package manager.components.task_visualize;

import javafx.scene.layout.Pane;
import manager.components.Task;

public interface ViewComponent {
    ViewComponent component = null;

    Pane draw();

    Task getTask();
}
