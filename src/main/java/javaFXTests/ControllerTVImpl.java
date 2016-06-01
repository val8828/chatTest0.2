package javaFXTests;

import javaFXTests.chat.Message;
import javaFXTests.chat.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.util.Callback;

import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class ControllerTVImpl implements GUIController {

    @FXML
    TableView<User> usersTableView;

    @FXML
    ListView<Message> messageListView;

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
        drawUserList();

        drawMessageList();

        usersTableView.setStyle("-fx-table-cell-border-color: transparent;");

    }

    public void drawUserList() {

        TableColumn<User, Image> icon = new TableColumn<>("Icon");
        icon.setCellValueFactory(new PropertyValueFactory<User, Image>("icon"));
        icon.setPrefWidth(60);

        TableColumn<User, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        name.setPrefWidth(50);

        TableColumn<User, Integer> id = new TableColumn<>("Id");
        id.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        id.setPrefWidth(5);

        TableColumn<User, Boolean> online = new TableColumn<>("Online");
        online.setCellValueFactory(new PropertyValueFactory<User, Boolean>("online"));
        online.setPrefWidth(60);

        icon.setCellFactory(param -> new TableCell<User, Image>() {
            @Override
            public void updateItem(Image item, boolean empty) {
                if (item != null) {
                    ImageView imageview = new ImageView();
                    imageview.setFitHeight(50);
                    imageview.setFitWidth(50);
                    imageview.setImage(item);

                    setGraphic(imageview);
                }
            }
        });

        online.setCellFactory(param -> new TableCell<User, Boolean>() {
            @Override
            public void updateItem(Boolean item, boolean empty) {
                if (item != null) {
                    ImageView imageview = new ImageView();
                    imageview.setFitHeight(50);
                    imageview.setFitWidth(50);
                    if (item) {
                        imageview.setImage(new Image("online.png"));
                    } else {
                        imageview.setImage(new Image("offline.png"));
                    }
                    setGraphic(imageview);
                }
            }
        });

        usersTableView.getColumns().clear();

        usersTableView.getColumns().addAll(icon, name, online);

        usersTableView.setItems(App.getUserListModel().getUserList());
    }

    public void drawMessageList() {

        messageListView.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {

            @Override
            public ListCell<Message> call(ListView<Message> arg0) {
                return new ListCell<Message>() {
                    @Override
                    protected void updateItem(Message item, boolean bln) {
                        super.updateItem(item, bln);
                        if (item != null) {
                            Text label = new Text(item.getSender().getName() + "  ( " + getShortDateString(item.getDate()) + " )  ");
                            label.setFont(Font.font ("Comic", 10));
                            VBox vBox = new VBox((label), new Text(item.getText()));
                            vBox.setSpacing(10);
                            setGraphic(vBox);
                        }
                    }
                };
            }
        });

        messageListView.setItems(App.getMessageHistory().getMessageList());
    }

    /**
     * Returns date in short or long format if current date differ more than day
     *
     * @param date other date
     * @return date in short or long format
     */
    public String getShortDateString(Date date) {
        String result;

        Date currentDate = new Date();

        Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        int day = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(currentDate);

        int currentDay = cal.get(Calendar.DAY_OF_MONTH);

        long anotherDate = 1;
        Locale local = new Locale("ru", "RU");

        if (currentDate.getTime() > (date.getTime() + 60 * 60 * 24 * 1000 * anotherDate) ||
                currentDay != day) {

            DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, local);

            result = df.format(date);
        } else {
            DateFormat df = DateFormat.getTimeInstance(DateFormat.DEFAULT, local);
            result = df.format(date);
        }
        return result;
    }


}
