/*
  Name: Edwin Tse
  PID:  A16616338
 */

import java.util.ArrayList;

public class Tutor extends User {

    // instance variable
    private String customTitle;

    public Tutor(String username, String bio) {
        super(username, bio);
        this.customTitle = "Tutor";
    }

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

    public String displayName() {
        String strUsername = this.username;
        String strTitle = "<" + this.customTitle + "> ";
        return strTitle + strUsername;
    }

    public void setCustomTitle(String newTitle) {
        this.customTitle = newTitle;
    }

    public MessageExchange createAutograder(ArrayList<User> users) {
        if (users == null) {
            throw new IllegalArgumentException("users is null");
        }
        Autograder newAutograder = new Autograder(Tutor.this);
        for (int userIndex = 0; userIndex < users.size(); userIndex++) {
            User currentUser = users.get(userIndex);
            try {
                currentUser.joinRoom(newAutograder);
            } catch (Exception theException) {
                System.out.println(theException.getMessage());
            }
        }
        return newAutograder;
    }

}
