package javaFXTests;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javaFXTests.chat.User;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerImpl implements GUIController {

    ObservableList<User> usersList ;

    @FXML
    ListView<User> userListView = new ListView<>();

    @FXML
    TextArea messageHistoryTextArea;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        usersList = App.getUserListModel().getUserList();

        VBox.setVgrow(userListView, Priority.ALWAYS);

        userListView.setCellFactory(list -> new userListItem()  );

        userListView.setItems(usersList);

        userListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            usersList.set(1, new UserImpl("222 name",2,false,new Image("file:D:\\2.jpg")));
            messageHistoryTextArea.appendText("11232\n");
        });
    }


    static class userListItem extends ListCell<User> {
        @Override
        public void updateItem(User item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                ImageView userAvatar = new ImageView();
                userAvatar.setFitHeight(50);
                userAvatar.setFitWidth(50);
                userAvatar.setPreserveRatio(true);
                userAvatar.setImage(item.getIcon());
                setGraphic(userAvatar);

                setText(item.getName());

                if (item.isOnline()){
                    setTextFill(Color.GREEN);
                }else {
                    setTextFill(Color.RED);
                }
            }
        }
    }


}
