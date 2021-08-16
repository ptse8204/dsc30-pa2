import java.util.ArrayList;

public abstract class User {

    // Error message to use in OperationDeniedException
    protected static final String JOIN_ROOM_FAILED =
            "Failed to join the chat room.";
    protected static final String INVALID_MSG_TYPE =
            "Cannot send this type of message to the specified room.";

    // instance variables
    protected String username;
    protected String bio;
    protected ArrayList<MessageExchange> rooms;

    public User(String username, String bio) {
        if (username == null || bio == null) {
            throw new IllegalArgumentException("username or bio is null.");
        }
        this.username = username;
        this.bio = bio;
    }

    public void setBio(String newBio) {
        if (newBio == null) {
            throw new IllegalArgumentException("newBio is null");
        }
        this.bio = newBio;
    }

    public String displayBio() {
        return this.bio;
    }

    public void joinRoom(MessageExchange me) throws OperationDeniedException {
        if (me == null){
            throw new IllegalArgumentException("the me is null");
        }
        for (int roomIndex = 0; roomIndex < this.rooms.size(); roomIndex++){
            MessageExchange currentRoom = this.rooms.get(roomIndex);
            if (currentRoom == me){
                throw new OperationDeniedException(JOIN_ROOM_FAILED);
            }
        }
        boolean joinRoomChecker = this.rooms.add(me);
        if (joinRoomChecker == false){
            throw new OperationDeniedException(JOIN_ROOM_FAILED);
        }
        this.rooms.add(me);
    }

    public void quitRoom(MessageExchange me) {
        if (me == null){
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

    public void sendMessage(MessageExchange me, String contents, int lines) {
        Message messageSend;
        if (me == null || contents == null){
            throw new IllegalArgumentException("me or contents is null");
        }
        boolean roomChecker = false;
        for (int roomIndex = 0; roomIndex < this.rooms.size(); roomIndex++) {
            MessageExchange currentRoom = this.rooms.get(roomIndex);
            if (currentRoom == me) {
                roomChecker = true;
            }
        }
        if (roomChecker == false) {
            throw new IllegalArgumentException("user didn’t join me");
        }
        if (lines == -1) {
            try {
                new TextMessage(User.this, contents);
            }
            catch (Exception theException){
                System.out.println(theException.getMessage());
                //System.exit();
            }
            messageSend = new TextMessage(User.this, contents);
        }
        else {
            try {
                new CodeMessage(User.this, contents, lines);
            }
            catch (Exception theException){
                System.out.println(theException.getMessage());
                //System.exit();
            }
            messageSend = new CodeMessage(User.this, contents, lines);
        }
        me.recordMessage(messageSend);
    }

    public abstract String fetchMessage(MessageExchange me);

    public abstract String displayName();
}