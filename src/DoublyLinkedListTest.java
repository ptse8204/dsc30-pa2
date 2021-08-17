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
    @Test
    public void test {
        DoublyLinkedList dllnewlst = new DoublyLinkedList();
        assertEquals(dllnewlst.add(10));
        dllnewlst.add(20);
        dllnewlst.add(30);
        assertEquals(true,dllnewlst.contains(30));
        System.out.println(dllnewlst.toString());
    }

}
