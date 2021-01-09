package manager.components.iteration;

import manager.components.task_visualize.ViewComponent;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

// Design Pattern Iterator
public class ViewComponentIterator implements Iterator{

    ListIterator<ViewComponent> innerIterator;
    TaskFilter filter;
    ViewComponent nextComponent;

    // Design Pattern Strategy
    public ViewComponentIterator(List<ViewComponent> container, TaskFilter componentsFilter){
        innerIterator = container.listIterator();
        filter = componentsFilter;
    }

    public ViewComponentIterator(List<ViewComponent> container){
        innerIterator = container.listIterator();
        filter = null;
    }

    public boolean hasNext(){
        if(filter == null){
            return innerIterator.hasNext();
        }
        if(nextComponent == null){
            ViewComponent next = innerIterator.next();
            while (innerIterator.hasNext() && !filter.satisfiesPredicate(next.getTask())) {
                next = innerIterator.next();
            }
            nextComponent = next;
        }
        return filter.satisfiesPredicate(nextComponent.getTask());
    }

    public ViewComponent next(){
        if(filter == null){
            return innerIterator.next();
        }
        if(nextComponent == null) {
            ViewComponent next = innerIterator.next();
            while (innerIterator.hasNext() && !filter.satisfiesPredicate(next.getTask())) {
                next = innerIterator.next();
            }
            return next;
        }else{
            ViewComponent buffor = nextComponent;
            nextComponent = null;
            return buffor;

        }
    }
}
