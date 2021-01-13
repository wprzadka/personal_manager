package manager.actions.history;

import manager.actions.Action;

public interface ActionsRegister {

    void consumeAction(Action action);

    void revertLastAction();
}
