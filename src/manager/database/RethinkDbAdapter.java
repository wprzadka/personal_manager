package manager.database;


import com.rethinkdb.gen.exc.ReqlDriverError;
import com.rethinkdb.gen.exc.ReqlOpFailedError;
import com.rethinkdb.gen.exc.ReqlQueryLogicError;
import com.rethinkdb.net.Result;
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
            Map row_data = (Map) row;
            var task = new Task((String)row_data.get("title"));
            task.setDescription((String)row_data.get("description"));
            task.setType((String)row_data.get("type"));
            data.add(task);
        }
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
