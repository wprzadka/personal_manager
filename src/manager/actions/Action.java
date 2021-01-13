package manager.actions;

// Design Pattern Command
public interface Action {

    void execute();

    void revert();
}
