package javaFXTests;

import javaFXTests.chat.*;
import javaFXTests.chat.noimpl.IoManger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Date;

/**
 *
 *
 */
public class App extends Application {

    static UserListModelImpl userListModel;
    static MessageHistory messageHistory;

    @Override
    public void start(Stage stage) throws Exception{

        IoManger ioManger = new IOmanagerImpl();

        GUIController guiController =
                new ControllerTVImpl(ioManger,getTestData());

        NewThread newThread = new NewThread(guiController);

        newThread.start();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/sampleTableView.fxml" ));

        loader.setController(guiController);

        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Hello World");
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);

    }

//    public static UserListModelImpl getUserListModel() {
//        return userListModel;
//    }

    public static ControllerDAO getTestData(){
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
        messages.add(new MessageImpl(new Date(),"Hi all",user1,user2,false));
        messages.add(new MessageImpl(new Date(),"Hi hi",user2,user1,false));

        return new ControllerDAOImpl(messageHistory,userListModel);
    }
    class NewThread extends Thread{

        GUIController guiController ;

        public NewThread(GUIController guiController) {
            this.guiController = guiController;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            try {
                sleep(10000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            guiController.updateUser(1,new UserImpl("New Name",1,false,new Image("avatar4.jpg")));
            System.out.println("");
        }
    }
}