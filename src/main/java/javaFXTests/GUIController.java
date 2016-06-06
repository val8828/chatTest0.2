package javaFXTests;

import javaFXTests.chat.Message;
import javaFXTests.chat.User;
import javaFXTests.chat.noimpl.IoManger;
import javafx.fxml.Initializable;

/**
 * Main controller of application
 */
public interface GUIController extends Initializable {

    /**
     * Adding corresponding message to list of other messages
     * @param message corresponding message
     */
    void addMessageToMessageList(Message message);

    /**
     * Send text message
     * @param text corresponding text
     */
    void sendMessage(String text);

    /**
     * Updating user info
     * @param userId id of user need to update
     * @param newValue new value of user info
     */
    void updateUser(int userId, User newValue);

    /**
     * Adding new user to userslist
     * @param user corresponding user
     */
    void addUserToList(User user);

    /**
     * Delete User from usersList
     * @param id id of user
     */
    void deleteUserFromList(int id);
}
