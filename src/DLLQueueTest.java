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

public class DLLQueueTest {
    @Before
    public void setup(){
    }
    @Test
    public void initAll1() {
        new DLLQueue();
    }
    @Test (expected = NoSuchElementException.class)
    public void initAll2(){
        DLLQueue init2 = new DLLQueue();
        init2.enqueue(1);
        init2.enqueue(1);
        init2.enqueue(1);
        init2.enqueue(1);
        init2.enqueue(1);
        init2.enqueue(1);
        assertEquals(6, init2.size());
        assertEquals(init2.dequeue(), 1);
        assertEquals(1, init2.dequeue());
        assertEquals(1,init2.dequeue());
        init2.dequeue();
        assertEquals(2, init2.size());
        assertEquals(1, init2.peek());
        assertEquals(1, init2.peek());
        init2.dequeue();
        init2.dequeue();
        init2.peek();
    }
    @Test (expected = NoSuchElementException.class)
    public void initAll3(){
        DLLQueue init3 = new DLLQueue();
        assertEquals(true, init3.isEmpty());
        init3.enqueue(1);
        assertEquals(false, init3.isEmpty());
        init3.dequeue();
        init3.dequeue();
    }
}
