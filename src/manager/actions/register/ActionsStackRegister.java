package manager.actions.register;

import manager.actions.Action;

import java.util.Stack;

public class ActionsStackRegister implements ActionsRegister {
    private final Stack<Action> register = new Stack<>();

    @Override
    public void consumeAction(Action action){
        action.execute();
        register.add(action);
    }

    @Override
    public void revertLastAction(){
        if(!register.isEmpty()) {
            Action last = register.pop();
            last.revert();
        }
    }
}
