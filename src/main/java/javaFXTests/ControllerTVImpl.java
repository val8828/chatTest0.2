package javaFXTests;

import javaFXTests.chat.User;
import javaFXTests.chat.UserDataModel;
import javaFXTests.music.MusicTable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTVImpl implements GUIController {

    @FXML
    TableView<User> usersTableView;
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
        getTableView();

        usersTableView.setStyle("-fx-table-cell-border-color: transparent;");

    }

public void getTableView(){

    usersTableView.setTableMenuButtonVisible(true);

    TableColumn<User,Image> icon = new TableColumn<>("Icon");
    icon.setCellValueFactory(new PropertyValueFactory("icon"));
    icon.setPrefWidth(60);

    TableColumn<User,String> name = new TableColumn<>("Name");
    name.setCellValueFactory(new PropertyValueFactory("name"));
    name.setPrefWidth(50);


    TableColumn<User,Integer> id = new TableColumn<>("Id");
    id.setCellValueFactory(new PropertyValueFactory("id"));
    id.setPrefWidth(5);

    TableColumn<User,Boolean> online = new TableColumn<>("Online");
    online.setCellValueFactory(new PropertyValueFactory("online"));
    online.setPrefWidth(60);

    icon.setCellFactory(param -> {
        TableCell<User, Image> cell = new TableCell<User, Image>(){
            @Override
            public void updateItem(Image item, boolean empty) {
                if(item!=null){
                    ImageView imageview = new ImageView();
                    imageview.setFitHeight(50);
                    imageview.setFitWidth(50);
                    imageview.setImage(item);

                    setGraphic(imageview);
                }
            }
        };
        return cell;
    });

    online.setCellFactory(param -> {
        TableCell<User, Boolean> cell = new TableCell<User, Boolean>(){
            @Override
            public void updateItem(Boolean item, boolean empty) {
                if(item!=null){
                    ImageView imageview = new ImageView();
                    imageview.setFitHeight(50);
                    imageview.setFitWidth(50);
                    if(item){
                        imageview.setImage(new Image("online.png"));
                    }else {
                        imageview.setImage(new Image("offline.png"));
                    }
                    setGraphic(imageview);
                }
            }
        };
        return cell;
    });

    usersTableView.getColumns().clear();

    usersTableView.getColumns().addAll(icon,name,online);

    usersTableView.setItems(App.getUserListModel().getUserList());

}

}