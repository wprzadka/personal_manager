package manager.components.task_visualize;

import javax.swing.text.View;
import java.util.List;
import java.util.ListIterator;

public class ViewComponentIterator {

    ListIterator<ViewComponent> innerIterator;

    //TODO get strategy to iterate over given state/type (?)
    public ViewComponentIterator(List<ViewComponent> container){
        innerIterator = container.listIterator();
    }

    public boolean hasNext(){
        return innerIterator.hasNext();
    }

    public ViewComponent next(){
        return innerIterator.next();
    }
}
