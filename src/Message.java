/*
    Name: Edwin Tse
    PID:  A16616338
 */

import java.time.LocalDate;

/**
 * This is the abstract class Message, which will have three instance variables: sender of this
 * message, the time this message is created, and the contents of this message.
 */
public abstract class Message {

    // Error message to use in OperationDeniedException
    protected static final String DENIED_USER_GROUP =
            "This operation is disabled in your user group.";

    // instance variables
    private LocalDate date;
    private User sender;
    protected String contents;

    /**
     * Constructor will set the sender and date fields.
     * The date will record the local time when you create this message instance.
     * @param sender User a user object
     */
    public Message(User sender) {
        this.date = LocalDate.now();
        if (sender == null) {
            throw new IllegalArgumentException("sender is null");
        } else {
            this.sender = sender;
        }
    }

    /**
     * Method will return the date of this message.
     * @return LocalDate the date of this message
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Method will return the sender of this message.
     * @return User the sender of this message
     */
    public User getSender() {
        return this.sender;
    }

    /**
     * All subclasses of Message must implement this getContents method with an identical method
     * signature
     * @return String of content
     */
    public abstract String getContents();

}
