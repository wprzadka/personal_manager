package manager.configuration;

import manager.components.task_visualize.ViewComponent;
import manager.controller.MainEventsController;
import manager.database.DbConnection;
import manager.database.RethinkDbAdapter;
import manager.ui.TableUserInterface;
import manager.ui.UserInterface;

import java.util.ArrayList;
import java.util.List;

// Design Pattern Dependency injection
// Design Pattern Singleton
public class Configuration {

    private static final Configuration instance = new Configuration();
    private DbConnection dbConnection;
    private MainEventsController mainEventsController;
    private UserInterface userInterface;

    private int windowWidth = 1200;
    private int windowHeight = 900;
    private List<ViewComponent> componentsList;

    private Configuration(){
        dbConnection = new RethinkDbAdapter();
        userInterface = new TableUserInterface(windowWidth, windowHeight);
        componentsList = new ArrayList<ViewComponent>();
        mainEventsController = new MainEventsController(dbConnection, userInterface, componentsList);
    }

    public static Configuration getInstance(){
        return instance;
    }

    public DbConnection getDbConnection() {
        return dbConnection;
    }

    public UserInterface getUserInterface() {
        return userInterface;
    }

    public List<ViewComponent> getComponentsList() {
        return componentsList;
    }

    public MainEventsController getMainEventsController() {
        return mainEventsController;
    }
}
