/*
  Name: Edwin Tse
  PID:  A16616338
 */

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
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Message> log = new ArrayList<Message>();
    private Tutor tutor;

    public Autograder(Tutor tutor) {
        this.tutor = tutor;
        this.users.add(tutor);
        try {
            tutor.joinRoom(Autograder.this);
        }
        catch (Exception theException) {
            theException.getMessage();
        }
    }

    public ArrayList<Message> getLog(User requester) {
        int logSize;
        int starter;
        ArrayList<Message> returnlst = new ArrayList<Message>();
        if (requester.getClass() == Tutor.class) {
            returnlst = this.log;
        }
        else if (requester.getClass() == Student.class) {
            logSize = log.size();
            if (logSize < MAX_MSG_SIZE) {
                starter = 0;
            }
            else {
                starter = logSize - MAX_MSG_SIZE;
            }
            returnlst = new ArrayList<Message> (log.subList(starter,
                    logSize));
            return returnlst;
        }
        return returnlst;
    }

    public boolean addUser(User u) {
        if (users.contains(u)) {
            return false;
        }
        else {
            users.add(u);
            try{
                u.joinRoom(Autograder.this);
            }
            catch (Exception exceptionMessage) {
                exceptionMessage.getMessage();
            }
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
        return this.users;
    }

    public boolean recordMessage(Message m) {
        this.log.add(m);
        return true;
    }

    public String resolveAllProblems(User requester) {
        int timeNeed; // Convert line to time
        int remainTime; // Check whether time has remainder
        int currentTime; // Represent the time of the queue
        DLLQueue problemQueue = new DLLQueue();
        for (int logIndex = 0; logIndex < log.size(); logIndex++) {
            Message currentMessage = log.get(logIndex);
            if (currentMessage instanceof CodeMessage &&
               ((CodeMessage) currentMessage).getExtension() == "java") {
                timeNeed = ((CodeMessage) currentMessage).getLines()/TIME_EXCHANGE_IDX;
                remainTime = ((CodeMessage) currentMessage).getLines()%TIME_EXCHANGE_IDX;
                if (remainTime != 0) {
                    timeNeed += 1;
                }
                if (timeNeed > DEFAULT_ALLOTTED_TIME) {
                    timeNeed -= DEFAULT_ALLOTTED_TIME;
                    burstTime += DEFAULT_ALLOTTED_TIME;
                    waitTime += DEFAULT_ALLOTTED_TIME * (log.size() - logIndex - 1 +
                                problemQueue.size());
                    problemQueue.enqueue(timeNeed);
                }
                else {
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
            }
            else {
                burstTime += currentTime;
                waitTime += currentTime * problemQueue.size();
            }
        }
        if (burstTime != 0) {
            return "All tasks are handled within " + String.valueOf(burstTime) +
                   " units of burst time and " + String.valueOf(waitTime) + " units of wait time.";
        }
        else {
            return "";
        }
    }

}