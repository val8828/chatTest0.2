package javaFXTests;

import javaFXTests.chat.Message;
import javaFXTests.chat.MessageImpl;
import javaFXTests.chat.User;
import javaFXTests.chat.UserImpl;
import javaFXTests.chat.noimpl.IoManger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.lang.reflect.Field;
import java.net.URL;
import java.text.DateFormat;
import java.util.*;
import java.util.function.Consumer;

public class ControllerTVImpl implements GUIController {
    private static final long ONE_DAY_IN_MILLIS = 60 * 60 * 24 * 1000;

    public ControllerTVImpl(IoManger ioManger, ControllerDAO controllerDAO) {

        this.controllerDAO = controllerDAO;
        this.ioManger = ioManger;

        //Setting parameters for ioManager
        Consumer<Message> receiveMessageHandler = this::addMessageToMessageList;

        ioManger.setRecieveMessageHandler(receiveMessageHandler);

        Consumer<User> userAdded = this::addUserToList;

        ioManger.setUserAddedHandler(userAdded);

        ioManger.setUserStateChangeHandler(this::updateUser);
    }

    private ControllerDAO controllerDAO;

    IoManger ioManger;

    User currentUser;

    User currentOpponent;

    @FXML
    TextField messageTextField;

    @FXML
    Button sendMessageButton;

    @FXML
    TableView<User> usersTableView;

    @FXML
    ListView<Message> messageListView;

    @FXML
    TableColumn<User, Image> icon;

    @FXML
    TableColumn<User, String> name;

    @FXML
    TableColumn<User, Boolean> online;

    private ObservableList<User> userObservableList;

    private ObservableList<Message> currentMessageList;

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

        currentMessageList = FXCollections.observableArrayList();

        showChooseUserDialog();

        initializeUserList();

        initializeMessageList();

        addActionListenerForUserList();

        addHandleToButton(sendMessageButton);
    }

    public void initializeUserList() {

        //Setting table initial
        icon.setCellValueFactory(new PropertyValueFactory<>("icon"));

        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        online.setCellValueFactory(new PropertyValueFactory<>("online"));

        //Setting fabrics
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

        name.setCellFactory(param -> new TableCell<User, String>() {
            @Override
            public void updateItem(String item, boolean empty) {
                if (item != null) {
                    setText(item);
                    setAlignment(Pos.CENTER_LEFT);
                }
            }
        });

        online.setCellFactory(param -> new TableCell<User, Boolean>() {
            @Override
            public void updateItem(Boolean item, boolean empty) {
                if (item != null) {
                    ImageView imageview = new ImageView();
                    imageview.setFitHeight(12);
                    imageview.setFitWidth(12);
                    if (item) {
                        imageview.setImage(new Image("online.png"));
                    } else {
                        imageview.setImage(new Image("offline.png"));
                    }
                    setAlignment(Pos.CENTER);

                    setGraphic(imageview);
                }
            }
        });

        //Adding data
        usersTableView.setItems(userObservableList);
    }

    private void initializeMessageList() {

        messageListView.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {
            @Override
            public ListCell<Message> call(ListView<Message> arg0) {
                return new ListCell<Message>() {
                    @Override
                    protected void updateItem(Message item, boolean bln) {
                        super.updateItem(item, bln);
                        if (item != null) {
                            Text label = new Text(item.getSender().getName() + "  ( " + getShortDateString(item.getDate()) + " )  ");
                            label.setFont(Font.font("Comic", 10));
                            VBox vBox = new VBox((label), new Text(item.getText()));
                            vBox.setSpacing(10);
                            setGraphic(vBox);
                        }
                    }
                };
            }
        });
        messageListView.setItems(currentMessageList);
    }

    /**
     * Returns date in short or long format if current date differ more than day
     *
     * @param date other date
     * @return date in short or long format
     */
    public String getShortDateString(Date date) {

        Date currentDate = new Date();

        Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        int day = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(currentDate);

        int currentDay = cal.get(Calendar.DAY_OF_MONTH);

        Locale local = new Locale("ru", "RU");

        if (currentDate.getTime() > (date.getTime() + ONE_DAY_IN_MILLIS) ||
                currentDay != day) {

            DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, local);

            return df.format(date);
        } else {
            DateFormat df = DateFormat.getTimeInstance(DateFormat.DEFAULT, local);

            return df.format(date);
        }
    }

    /**
     * Adding corresponding message to list of other messages
     *
     * @param message corresponding message
     */
    @Override
    public void addMessageToMessageList(Message message) {
        assert currentMessageList != null;
        assert message != null;
        currentMessageList.add(message);
    }

    /**
     * Send text message
     *
     * @param text corresponding text
     */
    @Override
    public void sendMessage(String text) {
        try {
            ioManger.sendMessage(currentOpponent, text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updating user info
     *
     * @param userId   id of user need to update
     * @param newValue new value of user info
     */
    @Override
    public void updateUser(int userId, User newValue) {
        for (User user : userObservableList) {
            if (user.getId() == userId) {
                Class ftClass = user.getClass();
                Field[] fields = ftClass.getDeclaredFields();
                for(Field field : fields) {
                    try {
                        field.setAccessible(true);
                        field.set(user, field.get(newValue));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        usersTableView.refresh();
    }

    /**
     * Adding new user to userslist
     *
     * @param user corresponding user
     */
    @Override
    public void addUserToList(User user) {
        assert user != null;
        userObservableList.add(user);
    }

    /**
     * Delete User from usersList
     *
     * @param id id of user
     */
    @Override
    public void deleteUserFromList(int id) {
        ioManger.setUserRemovedHandler(id);//TODO Why ioManager not handling remove Handler??
    }

    private void addHandleToButton(Button button) {
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            try {
                ioManger.sendMessage(currentOpponent, messageTextField.getText());
            } catch (Exception e1) {
                sendMessage(messageTextField.getText());
            }
        });
    }

    public void showChooseUserDialog() {
        userObservableList = controllerDAO.getUserListModel().getUserList();

        List<String> choices = new ArrayList<>();

        for (User user : userObservableList) {
            choices.add(user.getName());
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Choice Dialog");
        dialog.setHeaderText("Choice user name dialog");
        dialog.setContentText("Choose your name:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(letter -> {
            for (User user : userObservableList) {
                if (letter.equals(user.getName())) {
                    currentUser = user;
                }
            }
        });

        userObservableList.remove(currentUser);
    }

    private void addActionListenerForUserList() {
        usersTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                currentOpponent = usersTableView.getSelectionModel().getSelectedItem();
                //Refresh message list
                currentMessageList.clear();
                currentMessageList.addAll(controllerDAO.getMessageHistory(currentUser, currentOpponent).getMessageList());
                setMessagesRead(currentMessageList);
            }
        });
    }

    private void setInitialFabric() {

    }

    private void setMessagesRead( ObservableList<Message> messageList){
        for(Message message : messageList){
            if(message instanceof MessageImpl){
                MessageImpl message1 = (MessageImpl)message;
                message1.setMessageWasRead(true);
            }
        }
    }
}
