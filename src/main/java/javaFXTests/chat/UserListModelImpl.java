package javaFXTests.chat;

import javafx.collections.ObservableList;

/**
 *
 */
public class UserListModelImpl implements UserListModel {
    ObservableList<User> userObservableList;

    public UserListModelImpl(ObservableList<User> userObservableList) {
        this.userObservableList = userObservableList;
    }

    @Override
    public ObservableList<User> getUserList() {
        return userObservableList;
    }

}
