/*
  Name: Edwin Tse
  PID:  A16616338
 */

import java.util.ArrayList;
import java.util.List;

public class Student extends User {

    // Message to append when fetching non-text message
    private static final String FETCH_DENIED_MSG =
            "This message cannot be fetched because you are not a premium user.";

    // max number of messages to fetch
    private static final int MAX_MSG_SIZE = 100;

    public Student(String username, String bio) {
        super(username, bio);
    }

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
        if (roomChecker == false) {
            throw new IllegalArgumentException("user is not in the room me");
        }
        meLog = me.getLog(Student.this);
        for (int messageIndex = 0; messageIndex < meLog.size(); messageIndex++) {
            if (meLog.get(messageIndex).getClass() == TextMessage.class) {
                returnString += meLog.get(messageIndex).getContents() + "\n";
            }
            else {
                returnString += FETCH_DENIED_MSG + "\n";
            }
        }
        return returnString;
    }

    public String displayName() {
        return this.username;
    }
}
