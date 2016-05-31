package javaFXTests.chat;

import javafx.scene.image.Image;

/**
 * Created by Valeee on 30.05.2016.
 */
public class UserImpl implements User {
    String name;
    int id;
    boolean online;
    Image icon;

    public UserImpl(String name, int id, boolean online, Image icon) {
        this.name = name;
        this.id = id;
        this.online = online;
        this.icon = icon;
    }

    /**
     * @return User's name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return User's unique ID
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * @return True if user is online
     */
    @Override
    public boolean isOnline() {
        return online;
    }

    /**
     * @return User's avatar
     */
    @Override
    public Image getIcon() {
        return icon;
    }

}
