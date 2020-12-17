package manager.ui;

import javafx.scene.Scene;
import manager.components.task_visualize.ViewComponentIterator;

// DesignPattern Bridge
public interface UserInterface {

    Scene getScene();

    void refreshTasks(ViewComponentIterator iterator);
}
