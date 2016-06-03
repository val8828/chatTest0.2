package javaFXTests;

import javaFXTests.chat.MessageHistory;
import javaFXTests.chat.User;
import javaFXTests.chat.UserListModel;
import javaFXTests.chat.UserListModelImpl;

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
        return messageHistory;
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
