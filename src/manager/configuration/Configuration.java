package manager.configuration;

import manager.components.ContentContainer;
import manager.components.TaskIdentitySupervisor;
import manager.controller.MainEventsController;
import manager.database.ConcurrencyProxyDbConnection;
import manager.database.DbConnection;
import manager.database.RethinkDbAdapter;
import manager.ui.TableUserInterface;
import manager.ui.UserInterface;

// Design Pattern Dependency injection
// Design Pattern Singleton
public class Configuration {

    private static final Configuration instance = new Configuration();
    private final DbConnection dbConnection;
    private final MainEventsController mainEventsController;
    private final UserInterface userInterface;
    private final TaskIdentitySupervisor taskIdentitySupervisor;

    private final int windowWidth = 1200;
    private final int windowHeight = 900;
    private final ContentContainer contentContainer;

    private Configuration(){
        dbConnection = new ConcurrencyProxyDbConnection(new RethinkDbAdapter());
        userInterface = new TableUserInterface(windowWidth, windowHeight);
        contentContainer = new ContentContainer();
        mainEventsController = new MainEventsController(
                dbConnection,
                userInterface,
                contentContainer.getViewComponents()
        );
        taskIdentitySupervisor = new TaskIdentitySupervisor(contentContainer.getViewComponents());
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

    public ContentContainer getContentContainer() {
        return contentContainer;
    }

    public MainEventsController getMainEventsController() {
        return mainEventsController;
    }

    public TaskIdentitySupervisor getTaskIdentitySupervisor() {
        return taskIdentitySupervisor;
    }
}
