import java.util.ArrayList;

public class Tutor extends User {

    // instance variable
    private String customTitle;

    public Tutor(String username, String bio) {
        super(username, bio);
        this.customTitle = "Tutor";
    }

    public String fetchMessage(MessageExchange me) {
        String returnString;
        int logSize;
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
        meLog = me.getLog(Tutor.this);
        logSize = meLog.size();
        for (int messageIndex = 0; messageIndex < logSize; messageIndex++) {
            returnString += meLog.get(messageIndex).getContents() + "\n";
        }
        return returnString;
    }

    public String displayName() {
        String strUsername = this.username;
        String strTitle = "<" + this.customTitle + "> ";
        return strTitle + strUsername;
    }

    public void setCustomTitle(String newTitle) {
        this.customTitle = newTitle;
    }

    // not yet done
    public MessageExchange createAutograder(ArrayList<User> users) {
        Autograder newAutograder = new Autograder();

    }

}
