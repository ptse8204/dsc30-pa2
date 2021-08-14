import java.util.ArrayList;

public class Tutor extends User {

    // instance variable
    private String customTitle;

    public Tutor(String username, String bio) {
        super(username, bio);
        this.customTitle = "Tutor";
    }

    public String fetchMessage(MessageExchange me) {
        /* TODO */
        return null;
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
        /* TODO */
        return null;
    }

}
