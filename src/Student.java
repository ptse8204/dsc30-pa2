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
        int logSize;
        int starter;
        String returnString;
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
        logSize = meLog.size();
        if (logSize < 100) {
            starter = 0;
        }
        else {
            starter = logSize - 100;
        }
        for (int messageIndex = starter; messageIndex < logSize; messageIndex++) {
            if (meLog.get(messageIndex) instanceof TextMessage) {
                returnString += meLog.get(messageIndex).getContents() + "\n";
            }
            else {
                returnString += FETCH_DENIED_MSG;
            }
        }
        return returnString;
    }

    public String displayName() {
        return this.username;
    }
}
