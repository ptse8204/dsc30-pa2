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
        /* TODO */
    }

    public void quitRoom(MessageExchange me) {
        /* TODO */
    }

    public void sendMessage(MessageExchange me, String contents, int lines) {
        /* TODO */
    }

    public abstract String fetchMessage(MessageExchange me);

    public abstract String displayName();
}