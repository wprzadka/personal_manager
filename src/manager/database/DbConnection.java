package manager.database;

import manager.components.Task;

import java.util.List;


public interface DbConnection {

    List<Task> getTasks();

    void addTask(Task taskToAdd);

    void updateTask(Task taskToUpdate);

    void close();
}
