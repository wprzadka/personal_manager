package manager.components;

import java.util.Iterator;

class TaskIterator {

//    private Task contener;
    private Iterator<Task> innerIterator;

    //TODO get strategy to iterate over given state/type (?)
    TaskIterator(Task contener){
//        this.contener = contener;
        innerIterator = contener.components.iterator();
    }

    boolean hasNext(){
        return innerIterator.hasNext();
    }

    Task next(){
        return innerIterator.next();
    }

}
