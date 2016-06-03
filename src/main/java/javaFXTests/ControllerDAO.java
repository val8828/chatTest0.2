package javaFXTests;

import javaFXTests.chat.MessageHistory;
import javaFXTests.chat.User;
import javaFXTests.chat.UserListModel;

/**
 * DAO for accessing multiple controllers to data
 */
public interface ControllerDAO {
    /**
     * Returns Message List for two users - receiver and sender
     * @return  message list
     */
    MessageHistory getMessageHistory(User receiver, User sender);

    /**
     * returns model with users
     * @return model with users
     */
    UserListModel getUserListModel();


}
