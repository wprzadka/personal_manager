package manager.database;


import manager.components.Task;

import java.util.List;

// DesignPattern Adapter
public class RethinkDbAdapter implements DbConnection {
    @Override
    public List<Task> getTasks() {
        return null;
    }

    @Override
    public void addTask() {

    }
}
