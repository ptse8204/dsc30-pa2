public class QueueRunTest {
    public static void main(String[] args) {
        DLLQueue newQueue = new DLLQueue();
        System.out.println(newQueue.size());
        System.out.println(newQueue.isEmpty());
        newQueue.enqueue(5);
        newQueue.enqueue(4);
        newQueue.enqueue(3);
        System.out.println(newQueue.dequeue()); // expect 5
        System.out.println(newQueue.peek()); // expect 4
        System.out.println(newQueue.size()); // expect 1
    }
}
