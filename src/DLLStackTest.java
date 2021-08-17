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

public class DLLStackTest {
    @Before
    public void setup(){
    }
    @Test
    public void initAll1() {
        new DLLStack();
    }
    @Test (expected = EmptyStackException.class)
    public void initAll2(){
        DLLStack init2 = new DLLStack();
        init2.push(1);
        init2.push(1);
        init2.push(1);
        init2.push(1);
        init2.push(1);
        init2.push(1);
        assertEquals(6, init2.size());
        assertEquals(init2.pop(), 1);
        assertEquals(1, init2.pop());
        assertEquals(1,init2.pop());
        init2.pop();
        assertEquals(2, init2.size());
        assertEquals(1, init2.peek());
        assertEquals(1, init2.peek());
        init2.pop();
        init2.pop();
        init2.peek();
    }
    @Test (expected = EmptyStackException.class)
    public void initAll3(){
        DLLStack init3 = new DLLStack();
        assertEquals(true, init3.isEmpty());
        init3.push(1);
        assertEquals(false, init3.isEmpty());
        init3.pop();
        init3.pop();
    }
}
