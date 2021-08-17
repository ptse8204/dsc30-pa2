import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Messenger Application Test
 * @author TODO
 * @since  TODO
 */


public class DoublyLinkedListTest {
    @Test (expected = IndexOutOfBoundsException.class)
    public void test() {
        DoublyLinkedList dllnewlst = new DoublyLinkedList();
        assertEquals(true, dllnewlst.add(10));
        assertEquals(false, dllnewlst.isEmpty());
        assertEquals(true, dllnewlst.add(20));
        assertEquals(2, dllnewlst.size());
        assertEquals(true,dllnewlst.add(30));
        assertEquals(true,dllnewlst.contains(30));
        assertEquals("[(head) -> 10 -> 20 -> 30 -> (tail)]",dllnewlst.toString());
        dllnewlst.add(0,40);
        assertEquals("[(head) -> 40 -> 10 -> 20 -> 30 -> (tail)]",dllnewlst.toString());
        dllnewlst.set(0, dllnewlst.get(3));
        dllnewlst.set(1, 30);
        assertEquals("[(head) -> 30 -> 30 -> 20 -> 30 -> (tail)]", dllnewlst.toString());
        assertEquals(30, dllnewlst.get(0));
        assertEquals(30, dllnewlst.remove(3));
        assertEquals(true, dllnewlst.contains(20));
        assertEquals(false, dllnewlst.contains(0));
        assertEquals(3, dllnewlst.size());
        assertEquals(false, dllnewlst.isEmpty());
        dllnewlst.clear();
        assertEquals(true, dllnewlst.isEmpty());
        dllnewlst.clear();
        dllnewlst.get(7);
    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void test1() {
        DoublyLinkedList dllnewlst = new DoublyLinkedList();
        assertEquals(true, dllnewlst.add(10));
        assertEquals(false, dllnewlst.isEmpty());
        assertEquals(true, dllnewlst.add(20));
        dllnewlst.clear();
        dllnewlst.add(1, 3);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void test2() {
        DoublyLinkedList dllnewlst = new DoublyLinkedList();
        assertEquals(true, dllnewlst.add(10));
        assertEquals(false, dllnewlst.isEmpty());
        assertEquals(true, dllnewlst.add(20));
        dllnewlst.clear();
        dllnewlst.set(1, 3);
    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void test3() {
        DoublyLinkedList dllnewlst = new DoublyLinkedList();
        assertEquals(true, dllnewlst.add(10));
        assertEquals(false, dllnewlst.isEmpty());
        assertEquals(true, dllnewlst.add(20));
        dllnewlst.clear();
        dllnewlst.remove(1);
    }
}
