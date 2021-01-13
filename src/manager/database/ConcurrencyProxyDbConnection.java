package manager.database;

import manager.components.Task;

import java.util.List;

// Design Pattern Proxy
public class ConcurrencyProxyDbConnection implements DbConnection{

    private DbConnection connection;

    public ConcurrencyProxyDbConnection(DbConnection connection){
        this.connection = connection;
    }

    @Override
    public List<Task> getTasks() {
        return connection.getTasks();
    }

    @Override
    public void addTask(Task taskToAdd) {
        var thread = new Thread(() -> connection.addTask(taskToAdd));
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void updateTask(Task taskToUpdate) {
        var thread = new Thread(() -> connection.updateTask(taskToUpdate));
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void deleteTask(Task taskToDelete) {
        var thread = new Thread(() -> connection.deleteTask(taskToDelete));
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void close() {
        connection.close();
    }
}
