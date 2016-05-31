package javaFXTests;

import javaFXTests.chat.User;
import javaFXTests.chat.UserDataModel;
import javaFXTests.chat.UserImpl;
import javaFXTests.chat.UserListModelImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 *
 */
public class App extends Application {

    static UserListModelImpl userListModel;

    @Override
    public void start(Stage primaryStage) throws Exception{
        ObservableList<User> userObservableList = FXCollections.observableArrayList();
        userListModel = new UserListModelImpl(userObservableList);

        userObservableList.add(new UserImpl("1 name",1,true,new Image("file:D:\\1.jpg")));
        userObservableList.add(new UserImpl("2 name",2,true,new Image("file:D:\\2.jpg")));
        userObservableList.add(new UserImpl("3 name",3,false,new Image("file:D:\\3.jpg")));
        userObservableList.add(new UserImpl("4 name",4,true,new Image("file:D:\\4.jpg")));

//        userObservableList.add(new UserDataModel("1 name",1,true));
//        userObservableList.add(new UserDataModel("2 name",2,true));
//        userObservableList.add(new UserDataModel("3 name",3,false));
//        userObservableList.add(new UserDataModel("4 name",4,true));

        Parent root = FXMLLoader.load(getClass().getResource("/sampleTableView.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        userObservableList.set(1, new UserImpl("2 name",2,false,new Image("file:D:\\2.jpg")));
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static UserListModelImpl getUserListModel() {
        return userListModel;
    }
}