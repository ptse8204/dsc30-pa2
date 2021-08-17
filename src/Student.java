/*
  Name: Edwin Tse
  PID:  A16616338
 */

import java.util.ArrayList;
import java.util.List;

/**
 * The Student class extends the User class. Users belonging to this class will have a limitation
 * when fetching a message.
 */
public class Student extends User {

    // Message to append when fetching non-text message
    private static final String FETCH_DENIED_MSG =
            "This message cannot be fetched because you are not a premium user.";

    // max number of messages to fetch
    private static final int MAX_MSG_SIZE = 100;

    /**
     * Initialize the student class
     * @param username String name of the student
     * @param bio String bio of the student
     */
    public Student(String username, String bio) {
        super(username, bio);
    }

    /**
     * Fetches messages from the log of the MessageExchange.
     * @param me MessageExchange the room that you would like to fetch message from
     */
    public String fetchMessage(MessageExchange me) {
        String returnString = "";
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
        meLog = me.getLog(Student.this);
        for (int messageIndex = 0; messageIndex < meLog.size(); messageIndex++) {
            if (meLog.get(messageIndex).getClass() == TextMessage.class) {
                returnString += meLog.get(messageIndex).getContents() + "\n";
            } else {
                returnString += FETCH_DENIED_MSG + "\n";
            }
        }
        return returnString;
    }

    /**
     * Returns the username.
     * @return String username
     */
    public String displayName() {
        return this.username;
    }
}
