package javaFXTests.chat;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

/**
 * Created by Valeee on 31.05.2016.
 */
public class UserDataModel implements User {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty id;
    private final SimpleBooleanProperty online;

    public UserDataModel(String name, int id, boolean online) {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(id);
        this.online = new SimpleBooleanProperty(online);
    }

    /**
     * @return User's name
     */
    @Override
    public String getName() {
        return name.get();
    }

    /**
     * @return User's unique ID
     */
    @Override
    public int getId() {
        return id.get();
    }

    /**
     * @return True if user is online
     */
    @Override
    public boolean isOnline() {
        return online.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setOnline(boolean online) {
        this.online.set(online);
    }

    /**
     * @return User's avatar
     */
    @Override
    public Image getIcon() {
        return null;
    }
}
