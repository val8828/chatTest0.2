package javaFXTests;

import javaFXTests.chat.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Date;

/**
 *
 *
 */
public class App extends Application {

    static UserListModelImpl userListModel;
    static MessageHistory messageHistory;

    @Override
    public void start(Stage primaryStage) throws Exception{
        ObservableList<User> userObservableList = FXCollections.observableArrayList();
        userListModel = new UserListModelImpl(userObservableList);
        User user1 = new UserImpl("1 name",1,true,new Image("avatar1.jpg"));
        User user2 = new UserImpl("2 name",2,false,new Image("avatar2.jpg"));
        User user3 = new UserImpl("3 name",3,true,new Image("avatar3.jpg"));
        User user4 = new UserImpl("4 name",4,false,new Image("avatar4.jpg"));
        userObservableList.add(user1);
        userObservableList.add(user2);
        userObservableList.add(user3);
        userObservableList.add(user4);

        ObservableList<Message> messages = FXCollections.observableArrayList();
        messageHistory = new MessageHistoryImpl(messages);
        messages.add(new MessageImpl(new Date(),"Hi all",user1,user2));
        messages.add(new MessageImpl(new Date(),"Hi hi",user2,user1));

        Parent root = FXMLLoader.load(getClass().getResource("/sampleTableView.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        userObservableList.set(1, new UserImpl("2 name",2,false,new Image("avatar2.jpg")));
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static UserListModelImpl getUserListModel() {
        return userListModel;
    }

    public static MessageHistory getMessageHistory() {
        return messageHistory;
    }
}