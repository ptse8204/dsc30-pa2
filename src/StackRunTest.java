public class StackRunTest {
    public static void main(String[] args) {
        DLLStack newStack = new DLLStack();
        System.out.println(newStack.size());
        System.out.println(newStack.isEmpty());
        newStack.push(5);
        newStack.push(4);
        newStack.push(3);
        System.out.println(newStack.pop());
        System.out.println(newStack.peek());
        System.out.println(newStack.size());
    }
}
