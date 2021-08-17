/*
  Name: Edwin Tse
  PID:  A16616338
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EmptyStackException;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Messenger Application Test
 * @author TODO
 * @since  TODO
 */
public class MessengerApplicationTest {

    /*
      Messages defined in starter code. Remember to copy and paste these strings to the
      test file if you cannot directly access them. DO NOT change the original declaration
      to public.
     */
    private static final String INVALID_INPUT =
            "The source path is not valid.";

    /*
      Global test variables. Initialize them in @Before method.
     */
    Tutor marina;
    MessageExchange room;

    /*
      The date used in Message and its subclasses. You can directly
      call this in your test methods.
     */
    LocalDate date = LocalDate.now();

    /*
     * Setup
     */
    @Before
    public void setup() {
        marina = new Tutor("Marina", "Instructor");
        room = new Autograder(marina);
    }

    /*
      Recap: Assert exception without message
     */
    @Test (expected = IllegalArgumentException.class)
    public void testPremiumUserThrowsIAE() {
        marina = new Tutor("Marina", null);
    }

    /*
      Assert exception with message
     */
    @Test
    public void testPhotoMessageThrowsODE() {
        try {
            CodeMessage pm = new CodeMessage(marina, "PA02.zip", 10);
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            assertEquals(INVALID_INPUT, ode.getMessage());
        }
    }

    /*
     * Assert message content without hardcoding the date
     */
    @Test
    public void testTextMessageGetContents() {
        try {
            TextMessage tm = new TextMessage(marina, "A sample text message.");

            // concatenating the current date when running the test
            String expected = "<Tutor> Marina [" + date + "]: A sample text message.";
            assertEquals(expected, tm.getContents());
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        }
    }

    @Test (expected = OperationDeniedException.class)
    public void test1() throws OperationDeniedException {
        User newUser = new Student ("COVID", "No vaccine could stop me :)");
        Message newMessage = new TextMessage(newUser, "Hi");
        assertEquals(newMessage.getContents(), "COVID [2021-08-16]: Hi");
        assertEquals(newMessage.getSender(), newUser);
        CodeMessage newCodeMessage = new CodeMessage(newUser, "LMAO.HtmL", 10);
        assertEquals(newCodeMessage.getContents(), "COVID [2021-08-16]: Code at LMAO.HtmL");
        assertEquals("html", newCodeMessage.getExtension());
        assertEquals(10, newCodeMessage.getLines());
        assertEquals("No vaccine could stop me :)", newUser.displayBio());
        assertEquals("COVID", newUser.displayName());
        User oliver = new Student("Oliver", "");
        CodeMessage javaMessageOliver = new CodeMessage(oliver, "LMAO.Java", 59);
        User huaning = new Student("Huaning", "");
        CodeMessage javaMessagehuaning = new CodeMessage(huaning, "sthelse.jAva", 21);
        User colin = new Student("Colin", "");
        CodeMessage javaMessagecolin = new CodeMessage(colin, "wee.jaVa", 70);
        assertEquals("Oliver [2021-08-16]: Code at LMAO.Java", javaMessageOliver.getContents());
        assertEquals("Huaning [2021-08-16]: Code at sthelse.jAva", javaMessagehuaning.getContents());
        assertEquals("Colin [2021-08-16]: Code at wee.jaVa", javaMessagecolin.getContents());
        assertEquals("java", javaMessagecolin.getExtension());
        assertEquals("java", javaMessagehuaning.getExtension());
        assertEquals(59, javaMessageOliver.getLines());
        assertEquals(21, javaMessagehuaning.getLines());
        oliver.setBio("Smarty");
        assertEquals("Smarty", oliver.displayBio());
        assertEquals("", colin.displayBio());
        Tutor errorUser = new Tutor("Jenny", "Good Teacher");
        assertEquals("Good Teacher", errorUser.displayBio());
        newUser.setBio("Creating a new variant");
        assertEquals("<Tutor> Jenny", errorUser.displayName());
        errorUser.setCustomTitle("COVID");
        assertEquals("<COVID> Jenny", errorUser.displayName());
        Autograder newRoom = new Autograder(errorUser);
        assertEquals(false, newRoom.addUser(errorUser));
        assertArrayEquals(new User[] {errorUser}, newRoom.getUsers().toArray());
        newRoom.addUser(oliver);
        assertArrayEquals(new User[] {errorUser, oliver}, newRoom.getUsers().toArray());
        newRoom.removeUser(oliver, oliver);
        assertEquals(true, newRoom.addUser(oliver));
        assertArrayEquals(new User[] {errorUser, oliver}, newRoom.getUsers().toArray());
        assertEquals(true, newRoom.addUser(huaning));
        assertArrayEquals(new User[] {errorUser, oliver, huaning}, newRoom.getUsers().toArray());
        assertEquals(true, newRoom.addUser(colin));
        assertArrayEquals(new User[] {errorUser, oliver, huaning, colin},
                newRoom.getUsers().toArray());
        assertEquals(true, newRoom.recordMessage(newCodeMessage));
        assertEquals(true, newRoom.recordMessage(newMessage));
        assertEquals(true, newRoom.recordMessage(javaMessageOliver));
        assertEquals(true, newRoom.recordMessage(javaMessagehuaning));
        assertEquals(true, newRoom.recordMessage(javaMessagecolin));
        assertEquals("All tasks are handled within 16 units of burst time and 22 units of wait time."
                ,newRoom.resolveAllProblems(errorUser));
        new CodeMessage(oliver, "Tapy", 3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testmessage() throws OperationDeniedException {
        new TextMessage(null, "306");
    }

    @Test (expected = OperationDeniedException.class)
    public void testmessageode() throws OperationDeniedException {
        User newUser = new Student("", "");
        new TextMessage(newUser, "The abstract class Message will have " +
                "three instance variables: sender of this message, the time this " +
                "message is created, and the contents of this message. Since this is an " +
                "abstract class, it cannot be directly instantiated. However, the instances " +
                "of its subclasses can directly use the non-abstract methods (non-abstract " +
                "methods would contain actual implementations) in this superclass. This " +
                "saves us from having to implement the same thing multiple times (duplicate " +
                "implementation), which is a benefit of using an abstract class. \n" +
                "\n" +
                "Later in this assignment you will implement TextMessage and CodeMessage " +
                "classes that extend Message. Since the abstract class Message already has " +
                "implementations for getDate() and getSender(), TextMessage and CodeMessage " +
                "can have access to these methods without actually implementing them.");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testmessagenull() throws OperationDeniedException {
        User newUser = new Student("", "");
        new TextMessage(newUser, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void codemessagenull1() throws OperationDeniedException {
        User newUser = new Student("", "");
        new CodeMessage(newUser, null, 20);
    }

    @Test (expected = IllegalArgumentException.class)
    public void codemessagenull2() throws OperationDeniedException {
        User newUser = new Student("", "");
        new CodeMessage(null,"" , 20);
    }

    @Test (expected = OperationDeniedException.class)
    public void codemessageline() throws OperationDeniedException {
        User newUser = new Student("", "");
        new CodeMessage(newUser, "null.java", 9);
    }

    @Test (expected = IllegalArgumentException.class)
    public void usernull1() throws OperationDeniedException {
        User newUser = new Student(null, "");
    }

    @Test (expected = IllegalArgumentException.class)
    public void usernull2() throws OperationDeniedException {
        User newUser = new Student("", null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void userbionull() throws OperationDeniedException {
        User newUser = new Student("", "");
        newUser.setBio(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void userjoinrmnull() throws OperationDeniedException {
        User newUser = new Student("", "");
        newUser.joinRoom(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void userquitrmnull() throws OperationDeniedException {
        User newUser = new Student("", "");
        newUser.quitRoom(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void usersendnull() throws OperationDeniedException {
        User newUser = new Student("", "");
        newUser.sendMessage(null, "sth.java", 10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void usersendnull1() throws OperationDeniedException {
        Tutor newUser = new Tutor("", "");
        Autograder newRm = new Autograder(newUser);
        newUser.sendMessage(newRm, null, 10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void usersendnotjoinme() throws OperationDeniedException {
        Tutor newUser = new Tutor("", "");
        Autograder newRm = new Autograder(newUser);
        User anotherUser = new Student("", "");
        anotherUser.sendMessage(newRm, "sth.html", 10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void studentfetchnull() throws OperationDeniedException {
        Tutor newUser = new Tutor("", "");
        Autograder newRm = new Autograder(newUser);
        User anotherUser = new Student("", "");
        anotherUser.fetchMessage(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void studentfetchnotinme() throws OperationDeniedException {
        Tutor newUser = new Tutor("", "");
        Autograder newRm = new Autograder(newUser);
        User anotherUser = new Student("", "");
        anotherUser.fetchMessage(newRm);
    }

    @Test (expected = IllegalArgumentException.class)
    public void studentfetchtutornull() throws OperationDeniedException {
        Tutor newUser = new Tutor("", "");
        Autograder newRm = new Autograder(newUser);
        User anotherUser = new Student("t", "");
        anotherUser.joinRoom(newRm);
        anotherUser.sendMessage(newRm, "sthbeautiful.py", 100);
        assertEquals("This message cannot be fetched because you are not a premium user.\n",
                anotherUser.fetchMessage(newRm));
        assertEquals("t [2021-08-16]: Code at sthbeautiful.py" + "\n", newUser.fetchMessage(newRm));
        assertNotEquals(anotherUser.fetchMessage(newRm), newUser.fetchMessage(newRm));
        assertEquals("", newRm.resolveAllProblems(newUser));
        newUser.fetchMessage(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void messageexchangenull() throws OperationDeniedException {
        Tutor newUser = new Tutor("", "");
        User oliver = new Student("Oliver", "");
        User aaron = new Student("","");
        User huanning = new Student("","");
        User yuri = new Student("","");
        ArrayList<User> userlst = new ArrayList<User> ();
        userlst.add(oliver);
        userlst.add(aaron);
        userlst.add(huanning);
        userlst.add(yuri);
        MessageExchange anotherRm = newUser.createAutograder(userlst);
        Autograder newRm = new Autograder(newUser);
        newRm.addUser(oliver);
        newRm.addUser(aaron);
        newRm.addUser(huanning);
        newRm.addUser(yuri);
        oliver.sendMessage(newRm, "sth.java", 20);
        aaron.sendMessage(newRm, "sth.java", 20);
        huanning.sendMessage(newRm, "sth.java", 20);
        yuri.sendMessage(newRm, "sth.java", 80);
        assertEquals("All tasks are handled within 14 units of burst time and 12 units of wait " +
                "time.",newRm.resolveAllProblems(newUser));
        MessageExchange anotherRm1 = newUser.createAutograder(null);
    }
}
