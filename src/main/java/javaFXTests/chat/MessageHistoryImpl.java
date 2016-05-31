package javaFXTests.chat;

import javafx.collections.ObservableList;

/**
 *
 */
public class MessageHistoryImpl implements MessageHistory {
    ObservableList<Message> messages;

    @Override
    public ObservableList<Message> getMessageList() {
        return messages;
    }

    public MessageHistoryImpl(ObservableList<Message> messages) {
        this.messages = messages;
    }
}
