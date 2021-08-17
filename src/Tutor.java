/*
  Name: Edwin Tse
  PID:  A16616338
 */

import java.util.ArrayList;

/**
 * The Tutor class extends the User class. Users belonging to this class will have no limitation of
 * fetching messages. Also, they will have a custom title that will always display before the name.
 */
public class Tutor extends User {

    // instance variable
    private String customTitle;

    /**
     * This constructor initialize the Tutor class
     * @param username String name of the user
     * @param bio String bio of the user
     */
    public Tutor(String username, String bio) {
        super(username, bio);
        this.customTitle = "Tutor";
    }

    /**
     * Fetches all messages from the log returned by MessageExchange me.
     * @param me MessageExchange getting the log from this object
     * @return a String that is pre-formated
     * @throws IllegalArgumentException if the input parameter is null or if the user is not in the
     * room me.
     */
    public String fetchMessage(MessageExchange me) {
        boolean roomChecker;
        ArrayList<Message> meLog;
        if (me == null) {
            throw new IllegalArgumentException("the input parameter is null");
        }
        roomChecker = false;
        for (int roomIndex = 0; roomIndex < this.rooms.size(); roomIndex++) {
            MessageExchange currentRoom = this.rooms.get(roomIndex);
            if (currentRoom == me) {
                roomChecker = true;
            }
        }
        if (!roomChecker) {
            throw new IllegalArgumentException("user is not in the room me");
        }
        meLog = me.getLog(Tutor.this);
        int logSize = meLog.size();
        String returnString = "";
        for (int messageIndex = 0; messageIndex < logSize; messageIndex++) {
            returnString += meLog.get(messageIndex).getContents() + "\n";
        }
        return returnString;
    }

    /**
     * Returns the username and customTitle
     * @return String formated as “<customTitle> username” where customTitle and username are
     * replaced with their values.
     */
    public String displayName() {
        String strUsername = this.username;
        String strTitle = "<" + this.customTitle + "> ";
        return strTitle + strUsername;
    }

    /**
     * Sets the customTitle variable to the new value.
     * @param newTitle String new title that need to be pass in
     */
    public void setCustomTitle(String newTitle) {
        this.customTitle = newTitle;
    }

    /**
     * Creates a new instance of the Autograder (will be implemented later) class.
     * For each user in the users list calls joinRoom method to join the room.
     * @param users ArrayList<User>
     * @return MessageExchange new MessageExchange
     * @throws IllegalArgumentException if the users is null
     */
    public MessageExchange createAutograder(ArrayList<User> users) {
        if (users == null) {
            throw new IllegalArgumentException("users is null");
        }
        Autograder newAutograder = new Autograder(Tutor.this);
        for (int userIndex = 0; userIndex < users.size(); userIndex++) {
            User currentUser = users.get(userIndex);
            try {
                currentUser.joinRoom(newAutograder);
            } catch (OperationDeniedException theException) {
                System.out.println(theException.getMessage());
            } catch (IllegalArgumentException illegalException) {
                System.out.println(illegalException.getMessage());
            }
        }
        return newAutograder;
    }

}
