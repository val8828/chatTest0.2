package javaFXTests;

import javaFXTests.chat.Message;
import javaFXTests.chat.User;
import javaFXTests.chat.UserStateChangeHandler;
import javaFXTests.chat.noimpl.IoManger;

import java.util.function.Consumer;

/**
 * Created by Valeee on 03.06.2016.
 */
public class IOmanagerImpl implements IoManger {
    @Override
    public void sendMessage(User receiver, String text) throws Exception {
        System.out.println("Send Message"+text);
    }

    @Override
    public void setRecieveMessageHandler(Consumer<Message> handler) {

    }

    @Override
    public void setUserStateChangeHandler(UserStateChangeHandler handler) {

    }

    @Override
    public void setUserAddedHandler(Consumer<User> handler) {

    }

    @Override
    public void setUserRemovedHandler(int userId) {

    }

    public void updateUser(int index, User user){

    }
}
