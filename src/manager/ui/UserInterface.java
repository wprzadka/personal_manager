package manager.ui;

import javafx.scene.Scene;
import manager.components.TaskIterator;

// DesignPattern Bridge
public interface UserInterface {

    Scene getScene();

    void displayTasks(TaskIterator iterator);
}
