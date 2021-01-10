package manager;

import javafx.application.Application;
import javafx.stage.Stage;

import manager.configuration.Configuration;
import manager.ui.UserInterface;
import manager.database.DbConnection;
import manager.controller.MainEventsController;


public class ManagerApp extends Application {

    private final DbConnection dbConn = Configuration.getInstance().getDbConnection();
    private final UserInterface ui = Configuration.getInstance().getUserInterface();
    private final MainEventsController eventController = Configuration.getInstance().getMainEventsController();

    @Override
    public void start(Stage stage) {
        Configuration.getInstance().getContentContainer().loadComponents();

        stage.setTitle("Task Manager");
        stage.setScene(ui.getScene());
        eventController.refresh();
        stage.show();
    }

    @Override
    public void stop(){

        dbConn.close();
    }

    public static void main(String[] args){
        launch(args);
    }
}
