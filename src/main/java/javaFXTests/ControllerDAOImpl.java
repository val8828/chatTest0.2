package javaFXTests;

import javaFXTests.chat.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 */
public class ControllerDAOImpl implements ControllerDAO {
    private MessageHistory messageHistory;
    private UserListModel userListModel;

    public ControllerDAOImpl(MessageHistory messageHistory, UserListModel userListModel) {
        this.messageHistory = messageHistory;
        this.userListModel = userListModel;
    }

    /**
     * Returns Message List for two users - receiver and sender
     *
     * @param receiver
     * @param sender
     * @return message list
     */
    @Override
    public MessageHistory getMessageHistory(User receiver, User sender) {
        ObservableList<Message> messages = FXCollections.observableArrayList(messageHistory.getMessageList());
        ObservableList<Message> toRemove = FXCollections.observableArrayList();
        for(Message message : messages){
            User currentReceiver = message.getReceiver();
            User currentSender = message.getSender();
            if(!(currentReceiver == receiver && currentSender == sender ||
                    currentReceiver == sender && currentSender == receiver)){
                toRemove.add(message);
            }
        }
        messages.removeAll(toRemove);
        return new MessageHistoryImpl(messages);
    }

    /**
     * returns list with users
     *
     * @return list with users
     */
    @Override
    public UserListModel getUserListModel() {
        return userListModel;
    }
}
