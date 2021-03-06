package manager.actions.register;

import manager.actions.Action;

public interface ActionsRegister {

    void consumeAction(Action action);

    void revertLastAction();
}
