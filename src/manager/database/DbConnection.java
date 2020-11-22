package manager.database;

import manager.components.Task;

import java.util.List;
import java.util.Map;


public interface DbConnection {

    List<Task> getTasks();

    void addTask(Task taskToAdd);

    void close();
}
