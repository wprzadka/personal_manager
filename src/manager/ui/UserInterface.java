package manager.ui;

import javafx.scene.Scene;
import manager.components.TaskIterator;
import manager.components.task_visualize.ViewComponentIterator;

// DesignPattern Bridge
public interface UserInterface {

    Scene getScene();

    void displayTasks(ViewComponentIterator iterator);
}
