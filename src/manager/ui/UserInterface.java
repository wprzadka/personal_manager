package manager.ui;

import javafx.scene.Scene;
import manager.components.iteration.ViewComponentIterator;

// DesignPattern Bridge
public interface UserInterface {

    Scene getScene();

    void refreshTasks(ViewComponentIterator iterator);
}
