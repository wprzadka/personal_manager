package manager.database;


import com.rethinkdb.gen.exc.ReqlDriverError;
import com.rethinkdb.gen.exc.ReqlOpFailedError;
import com.rethinkdb.gen.exc.ReqlQueryLogicError;
import com.rethinkdb.net.Result;
import manager.components.State;
import manager.components.Task;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

// DesignPattern Adapter
public class RethinkDbAdapter implements DbConnection {
    private final RethinkDB r = RethinkDB.r;
    private Connection conn = null;

    public RethinkDbAdapter() {
        try {
            conn = r.connection().hostname("localhost").port(28015).connect();
        }catch (ReqlDriverError err){
            err.printStackTrace();
        }
        if(conn != null){
            createTableIfNotExists("Content", "tasks");
        }

    }

    @Override
    public List<Task> getTasks() {
        Result rows;
        if(conn == null){
            return Collections.emptyList();
        }
        try {
            rows = r.db("Content").table("tasks").run(conn);
        }catch (ReqlOpFailedError err){
            // try to create table
            err.printStackTrace();
            return Collections.emptyList();
        }
        catch (ReqlDriverError err){
            err.printStackTrace();
            return Collections.emptyList();
        }

        List<Task> data = new LinkedList<>();
        for(var row : rows){
            Map<String, Object> row_data = (Map) row;

            var task = new Task(
                    (Long)row_data.get("id"),
                    (String)row_data.get("title"),
                    (String)row_data.get("description"),
                    (String)row_data.get("type"),
                    State.valueOf((String)row_data.get("state"))
            );
            data.add(task);
        }
        rows.close();
        return data;
    }

    @Override
    public void addTask(Task taskToAdd) {
        if(conn != null) {
            try {
                r.db("Content").table("tasks").insert(
                        r.hashMap("title", taskToAdd.title)
                                .with("description", taskToAdd.description)
                                .with("type", taskToAdd.type)
                                .with("state", taskToAdd.progressState)
                                .with("id", taskToAdd.getIdentity())
                ).run(conn);
            }catch (ReqlOpFailedError err){
                // try to create table
                err.printStackTrace();
            } catch (ReqlQueryLogicError err) {
                err.printStackTrace();
            }
        }
    }

    @Override
    public void updateTask(Task taskToUpdate){
        if(conn != null) {
            try {
                r.db("Content").table("tasks").get(taskToUpdate.getIdentity()).update(
                        r.hashMap("title", taskToUpdate.title)
                                .with("description", taskToUpdate.description)
                                .with("type", taskToUpdate.type)
                                .with("state", taskToUpdate.progressState)
                ).run(conn);
            } catch (ReqlOpFailedError err) {
                // try to create table
                err.printStackTrace();
            } catch (ReqlQueryLogicError err) {
                err.printStackTrace();
            }
        }
    }

    @Override
    public void deleteTask(Task taskToDelete) {
        if(conn != null) {
            try {
                r.db("Content").table("tasks").get(taskToDelete.getIdentity()).delete().run(conn);
            } catch (ReqlOpFailedError err) {
                // try to create table
                err.printStackTrace();
            } catch (ReqlQueryLogicError err) {
                err.printStackTrace();
            }
        }
    }

    @Override
    public void close(){
        if(conn != null) {
            conn.close();
        }
    }

    private void createTableIfNotExists(String dbName, String tableName){
        Boolean dbAlreadyExists = (Boolean) r.dbList().contains("Content").run(conn).first();
        if(!dbAlreadyExists){
            r.dbCreate("Content").run(conn);
        }
        Boolean tableAlreadyExists = (Boolean) r.db("Content").tableList().contains("tasks").run(conn).first();
//        System.out.println(r.db("Content").tableList().contains("tasks").run(conn));
        if(!tableAlreadyExists) {
            r.db("Content").tableCreate("tasks").run(conn);
        }
    }
}
