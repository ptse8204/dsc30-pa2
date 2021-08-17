/*
  Name: Edwin Tse
  PID:  A16616338
 */

import java.util.ArrayList;

/**
 * The User class is the abstract class that defines the functionality of a user in our messaging
 * app
 */
public abstract class User {

    // Error message to use in OperationDeniedException
    protected static final String JOIN_ROOM_FAILED =
            "Failed to join the chat room.";
    protected static final String INVALID_MSG_TYPE =
            "Cannot send this type of message to the specified room.";

    // instance variables
    protected String username;
    protected String bio;
    protected ArrayList<MessageExchange> rooms = new ArrayList<MessageExchange>();

    /**
     * The constructor of the User class
     * @param username String name of the user
     * @param bio Strng bio of the user
     * @throws IllegalArgumentException if username or bio is null
     */
    public User(String username, String bio) {
        if (username == null || bio == null) {
            throw new IllegalArgumentException("username or bio is null.");
        }
        this.username = username;
        this.bio = bio;
    }

    /**
     * This method updates the class variable bio with a new one.
     * @param newBio String of the newBio
     * @throws IllegalArgumentException if newBio is null
     */
    public void setBio(String newBio) {
        if (newBio == null) {
            throw new IllegalArgumentException("newBio is null");
        }
        this.bio = newBio;
    }

    /**
     * Returns the bio.
     * @return String bio
     */
    public String displayBio() {
        return this.bio;
    }

    /**
     * This method attempts to join this user to the MessageExchange me.
     * @param me MessageExchange the room that the user wanted to join
     * @throws OperationDeniedException if the room is already in the list of rooms of this user,
     * or the joining failed as the addUser()suggests
     * @throws IllegalArgumentException if me is null
     */
    public void joinRoom(MessageExchange me) throws OperationDeniedException {
        if (me == null) {
            throw new IllegalArgumentException("the me is null");
        }
        for (int roomIndex = 0; roomIndex < this.rooms.size(); roomIndex++) {
            MessageExchange currentRoom = this.rooms.get(roomIndex);
            if (currentRoom == me) {
                throw new OperationDeniedException(JOIN_ROOM_FAILED);
            }
        }
        boolean joinRoomChecker = this.rooms.add(me);
        if (!joinRoomChecker) {
            throw new OperationDeniedException(JOIN_ROOM_FAILED);
        }
        this.rooms.add(me);
    }

    /**
     * Removes the message exchange platform from the list of rooms that this user is a member of
     * and removes the user from the list of users recorded in the MessageExchange object.
     *
     * If the user is not in the list of users, this operation does nothing.
     * @param me MessageExchange the room that the user wants to exit
     * @throws IllegalArgumentException if me is null
     */
    public void quitRoom(MessageExchange me) {
        if (me == null) {
            throw new IllegalArgumentException("the me is null");
        }
        boolean quitRoomChecker = false;
        for (int roomIndex = 0; roomIndex < this.rooms.size(); roomIndex++) {
            MessageExchange currentRoom = this.rooms.get(roomIndex);
            if (currentRoom == me) {
                quitRoomChecker = true;
            }
        }
        if (quitRoomChecker) {
            me.removeUser(User.this, User.this);
            int removeIndex = this.rooms.indexOf(me);
            this.rooms.remove(removeIndex);
        }
    }

    /**
     * Create an instance of a message and then send this message to the MessageExchange room.
     * @param me the room that user wanted to send message to
     * @param contents the message string
     * @param lines lines of code message, = -1 if otherwise
     * @throws IllegalArgumentException if me or contents is null or user didn’t join me
     */
    public void sendMessage(MessageExchange me, String contents, int lines) {
        if (me == null || contents == null) {
            throw new IllegalArgumentException("me or contents is null");
        }
        boolean roomChecker = false;
        Message messageSend = null;
        for (int roomIndex = 0; roomIndex < this.rooms.size(); roomIndex++) {
            MessageExchange currentRoom = this.rooms.get(roomIndex);
            if (currentRoom == me) {
                roomChecker = true;
            }
        }
        if (!roomChecker) {
            throw new IllegalArgumentException("user didn’t join me");
        }
        if (lines == -1) {
            try {
                messageSend = new TextMessage(User.this, contents);
            } catch (OperationDeniedException theException) {
                System.out.println(theException.getMessage());
            }
        } else {
            try {
                messageSend = new CodeMessage(User.this, contents, lines);
            } catch (OperationDeniedException theException) {
                System.out.println(theException.getMessage());
            }
        }
        me.recordMessage(messageSend);
    }

    public abstract String fetchMessage(MessageExchange me);

    public abstract String displayName();
}
