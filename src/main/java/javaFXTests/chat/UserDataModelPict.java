package javaFXTests.chat;

import javafx.beans.property.*;
import javafx.scene.image.Image;

/**
 * Created by Valeee on 31.05.2016.
 */
public class UserDataModelPict implements User {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty id;
    private final SimpleBooleanProperty online;
    private ObjectProperty<Image> icon;

    public UserDataModelPict(String name, int id, boolean online , Image icon) {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(id);
        this.online = new SimpleBooleanProperty(online);
        this.icon = new SimpleObjectProperty<>(icon);
    }

    /**
     * @return User's name
     */
    @Override
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * @return User's unique ID
     */
    @Override
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    /**
     * @return True if user is online
     */
    @Override
    public boolean isOnline() {
        return online.get();
    }

    public void setOnline(boolean online) {
        this.online.set(online);
    }

    /**
     * @return User's avatar
     */
    @Override
    public Image getIcon() {
        return icon.get();
    }

    public void setIcon(Image icon){
        this.icon.set(icon);
    }
}
