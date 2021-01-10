package manager;

import javafx.application.Application;
import javafx.stage.Stage;

import manager.configuration.Configuration;


public class ManagerApp extends Application {

    @Override
    public void start(Stage stage) {
        Configuration.getInstance().getContentContainer().loadComponents();

        stage.setTitle("Task Manager");
        stage.setScene(Configuration.getInstance().getUserInterface().getScene());
        Configuration.getInstance().getMainEventsController().refresh();
        stage.show();
    }

    @Override
    public void stop(){

        Configuration.getInstance().getDbConnection().close();
    }

    public static void main(String[] args){
        launch(args);
    }
}
