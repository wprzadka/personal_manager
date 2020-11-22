package manager.database;


import com.rethinkdb.gen.exc.ReqlDriverError;
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
    }

    @Override
    public List<Task> getTasks() {
        Result rows;
        if(conn == null){
            return Collections.emptyList();
        }
        try {
            rows = r.db("Content").table("tasks").run(conn);
        }catch (ReqlDriverError err){
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
                                .with("content", taskToAdd.type)
                ).run(conn);
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
}
