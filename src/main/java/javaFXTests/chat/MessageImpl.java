package javaFXTests.chat;

import java.util.Date;

/**
 * Created by Valeee on 31.05.2016.
 */
public class MessageImpl implements Message {
    private Date date;
    private String text;
    private User sender;
    private User receiver;
    private boolean messageWasRead;
    /**
     * @return date when the message was sent
     */
    @Override
    public Date getDate() {
        return this.date;
    }

    /**
     * @return The text of the message
     */
    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public User getSender() {
        return this.sender;
    }

    @Override
    public User getReceiver() {
        return this.receiver;
    }

    public boolean isMessageWasRead() {
        return messageWasRead;
    }

    public void setMessageWasRead(boolean messageWasRead) {
        this.messageWasRead = messageWasRead;
    }

    public MessageImpl(Date date, String text, User sender, User receiver, boolean messageWasRead) {
        this.date = date;
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
        this.messageWasRead = messageWasRead;
    }
}
