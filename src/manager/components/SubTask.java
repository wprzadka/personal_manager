package manager.components;

import java.util.LinkedList;
import java.util.List;

// TODO not implemented
public class SubTask extends Task{
    public List<SubTask> components;

    public SubTask(long identity, String title){
        super(identity, title);
        components = new LinkedList<>();
    }

}
