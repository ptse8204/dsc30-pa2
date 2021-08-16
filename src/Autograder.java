import java.util.ArrayList;
import java.util.List;

public class Autograder implements MessageExchange {

    // time allowed
    private static final int DEFAULT_ALLOTTED_TIME = 5;

    // max number of messages to fetch
    private static final int MAX_MSG_SIZE = 100;

    // time exchange index
    private static final int TIME_EXCHANGE_IDX = 10;

    /* instance variables */
    private DoublyLinkedList waitlist, finished;
    private int burstTime, waitTime;

    // instance variables
    private ArrayList<User> users;
    private ArrayList<Message> log;
    private Tutor tutor;

    public Autograder(Tutor tutor) {
        this.tutor = tutor;
        this.users.add(tutor);
    }

    public ArrayList<Message> getLog(User requester) {
        /* TODO */
        return null;
    }

    public boolean addUser(User u) {
        if (users.contains(u)) {
            return false;
        }
        else {
            users.add(u);
            u.joinRoom(Autograder.this);
            return true;
        }
    }

    public boolean removeUser(User requester, User u) {
        boolean roomCheck = false;
        if (requester == u) {
            roomCheck = true;
        } else if (requester instanceof Tutor) {
            roomCheck = true;
        }
        if (roomCheck && this.users.contains(u)) {
            this.users.remove(u);
            u.quitRoom(Autograder.this);
            return true;
        }
        return false;
    }

    public ArrayList<User> getUsers() {
        return this.users
    }

    public boolean recordMessage(Message m) {
        this.log.add(m);
        return true;
    }

    public String resolveAllProblems(User requester) {
        /* TODO */
        return null;
    }

}