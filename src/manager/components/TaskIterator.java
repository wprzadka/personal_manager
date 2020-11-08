package manager.components;

import java.util.ListIterator;

public class TaskIterator {

    private ListIterator<Task> innerIterator;

    //TODO get strategy to iterate over given state/type (?)
    TaskIterator(Task contener){
        innerIterator = contener.components.listIterator();
    }

    boolean hasNext(){
        return innerIterator.hasNext();
    }

    Task next(){
        return innerIterator.next();
    }

}
