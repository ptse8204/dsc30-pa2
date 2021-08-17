public class DLLrunTest {
    public static void main(String[] args) {
        DoublyLinkedList dllnewlst = new DoublyLinkedList();
        dllnewlst.add(10);
        dllnewlst.add(20);
        dllnewlst.add(30);
        System.out.println(dllnewlst.contains(30));
        System.out.println(dllnewlst.toString());
    }
}
