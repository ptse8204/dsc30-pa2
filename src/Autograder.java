/*
  Name: Edwin Tse
  PID:  A16616338
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Autograder constructor, this constructor will implement the MessageExchange interface.
 * @author Edwin Tse
 * @since 1.2
 */

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
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Message> log = new ArrayList<Message>();
    private Tutor tutor;

    /**
     * This method initalize the Autograder constructor
     * @param tutor a tutor object is require in order to init the Autograder constructor
     */
    public Autograder(Tutor tutor) {
        this.tutor = tutor;
        this.users.add(tutor);
        try {
            tutor.joinRoom(Autograder.this);
        } catch (OperationDeniedException theException) {
            theException.getMessage();
        }
    }

    /**
     * This method returns the log of this chat room.
     * @param requester The user that requests this operation.
     * @return if user class == student, return >100 log, else all log is returned
     */
    public ArrayList<Message> getLog(User requester) {
        int logSize;
        int starter;
        ArrayList<Message> returnlst = new ArrayList<Message>();
        if (requester.getClass() == Tutor.class) {
            returnlst = this.log;
        } else if (requester.getClass() == Student.class) {
            logSize = log.size();
            if (logSize < MAX_MSG_SIZE) {
                starter = 0;
            } else {
                starter = logSize - MAX_MSG_SIZE;
            }
            returnlst = new ArrayList<Message>(log.subList(starter,
                    logSize));
            return returnlst;
        }
        return returnlst;
    }

    /**
     * Method adds the given user u to this autograder room.
     * @param u User to add.
     * @return true if user successfully added, false otherwise
     */
    public boolean addUser(User u) {
        if (users.contains(u)) {
            return false;
        } else {
            users.add(u);
            try {
                u.joinRoom(Autograder.this);
            } catch (OperationDeniedException exceptionMessage) {
                exceptionMessage.getMessage();
            }
            return true;
        }
    }

    /**
     * Method removes the given user u from this room at the request of the given requester.
     * @param requester The user that requests this operation. Valid only if requester == u or
     *                  requester is a tutor class.
     * @param u User to remove.
     * @return true if user successfully removed, false otherwise
     */
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

    /**
     * Method returns the users of this autograder room.
     * @return users of this autograder room
     */
    public ArrayList<User> getUsers() {
        return this.users;
    }

    /**
     * Method adds a new message to the log of this autograder room.
     * @param m Message to add.
     * @return true (always true)
     */
    public boolean recordMessage(Message m) {
        this.log.add(m);
        return true;
    }

    /**
     * Method captures the actual process of the tutor handling all the code questions.
     * @param requester could be any user
     * @return a string in a format of
     * "All tasks are handled within (intnum) units of burst time and (intnum) units of wait time."
     */
    public String resolveAllProblems(User requester) {
        int timeNeed; // Convert line to time
        int remainTime; // Check whether time has remainder
        int currentTime; // Represent the time of the queue
        DLLQueue problemQueue = new DLLQueue();
        for (int logIndex = 0; logIndex < log.size(); logIndex++) {
            Message currentMessage = log.get(logIndex);
            if (currentMessage instanceof CodeMessage
                && ((CodeMessage) currentMessage).getExtension().equals("java")) {
                timeNeed = ((CodeMessage) currentMessage).getLines() / TIME_EXCHANGE_IDX;
                remainTime = ((CodeMessage) currentMessage).getLines() % TIME_EXCHANGE_IDX;
                if (remainTime != 0) {
                    timeNeed += 1;
                }
                if (timeNeed > DEFAULT_ALLOTTED_TIME) {
                    timeNeed -= DEFAULT_ALLOTTED_TIME;
                    burstTime += DEFAULT_ALLOTTED_TIME;
                    waitTime += DEFAULT_ALLOTTED_TIME * (log.size() - logIndex - 1
                             + problemQueue.size());
                    problemQueue.enqueue(timeNeed);
                } else {
                    burstTime += timeNeed;
                    waitTime += timeNeed * (log.size() - logIndex - 1 + problemQueue.size());
                }
            }
        }
        while (problemQueue.size() > 0) {
            currentTime = problemQueue.dequeue();
            if (currentTime > DEFAULT_ALLOTTED_TIME) {
                currentTime -= DEFAULT_ALLOTTED_TIME;
                burstTime += DEFAULT_ALLOTTED_TIME;
                waitTime += DEFAULT_ALLOTTED_TIME * problemQueue.size();
                problemQueue.enqueue(currentTime);
            } else {
                burstTime += currentTime;
                waitTime += currentTime * problemQueue.size();
            }
        }
        if (burstTime != 0) {
            return "All tasks are handled within " + String.valueOf(burstTime)
                    + " units of burst time and " + String.valueOf(waitTime)
                    + " units of wait time.";
        } else {
            return "";
        }
    }
}
