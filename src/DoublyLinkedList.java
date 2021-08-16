/*
 * NAME: Edwin Tse
 * PID: A16616338
 */

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.LinkedList;

//toString(), Contain

/**
 * Doubly-Linked List Implementation
 *
 * @author TODO
 * @since TODO
 */

    public class DoublyLinkedList {

    private int nelems;
    private Node head;
    private Node tail;
    private LinkedList<Node> outLinkedL;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {
        int data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(int element) {
            this.data = element;
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(int element, Node nextNode, Node prevNode) {
            this.data = element;
            this.next = nextNode;
            this.prev = prevNode;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            this.prev = p;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            this.next = n;
        }

        /**
         * Set the element
         *
         * @param e new element
         */
        public void setElement(int e) {
            this.data = e;
        }

        /**
         * Accessor to get the next Node in the list
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Accessor to get the prev Node in the list
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public int getElement() {
            return this.data;
        }

        /**
         * Remove this node from the list. Update previous and next nodes
         */
        public void remove() {
            Node before = this.prev;
            Node after = this.next;
            before.setNext(after);
            after.setPrev(before);
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        this.head = new Node(0);
        this.tail = new Node(1);
        this.nelems = 0;
        outLinkedL = new LinkedList<Node>();
        outLinkedL.add(0, head);
        outLinkedL.add(1, tail);
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * @return Number of elements currently on the list
     */
    public int size() {
        return this.nelems;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * @param index The index of the desired element.
     * @return The element stored in the Node with the desired index.
     * @throws IndexOutOfBoundsException if index received is out of bounds for
     *                                   the current list.
     */
    public int get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size() - 1) {
            throw new IndexOutOfBoundsException("index is outside the range [0, size - 1]");
        }
        return outLinkedL.get(index + 1).getElement();
    }

    /**
     * Add an element to the end of the list
     *
     * @param data data to be added
     */
    public boolean add(int data) {
        Node previNode = outLinkedL.get(size());
        Node endNode = outLinkedL.get(size() + 1);
        Node newNode = new Node (data, endNode, previNode);
        previNode.setNext(newNode);
        endNode.setPrev(newNode);
        outLinkedL.add(size() + 1, newNode);
        this.nelems += 1;
        return true;
    }

    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room.
     *
     * @param index Where in the list to add the element.
     * @param data  Data to be added.
     * @throws IndexOutOfBoundsException if index received is out of bounds for
     *                                   the current list.
     */
    public void add(int index, int data)
            throws IndexOutOfBoundsException {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("index is outside the range [0, size]");
        }
        Node beforeNode = outLinkedL.get(index);
        Node afterNode = outLinkedL.get(index + 1);
        Node newNode = new Node(data, afterNode, beforeNode);
        beforeNode.setNext(newNode);
        afterNode.setPrev(newNode);
        outLinkedL.add(index + 1, newNode);
        this.nelems += 1;
    }

    /**
     * Sets the value of an element at a certain index in the list.
     *
     * @param index Where in the list the data should be added.
     * @param data  Data to add.
     * @return Element that was previously at this index.
     * @throws IndexOutOfBoundsException if index received is out of bounds for
     *                                   the current list.
     */
    public int set(int index, int data)
            throws IndexOutOfBoundsException {
        if (index < 0 || index > size() - 1) {
            throw new IndexOutOfBoundsException("index is outside the range [0, size - 1]");
        }
        Node beforeNode = outLinkedL.get(index);
        Node afterNode = outLinkedL.get(index + 2);
        Node alterNode = outLinkedL.get(index + 1);
        Node newNode = new Node(data, afterNode, beforeNode);
        beforeNode.setNext(newNode);
        afterNode.setPrev(newNode);
        outLinkedL.set(index + 1, newNode);
        return alterNode.getElement();
    }

    /**
     * remove the element from position index in the list
     * @param index:index where in the list the data should be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if index<0 || index >= size
     */
    public int remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size() - 1) {
            throw new IndexOutOfBoundsException("index is outside the range [0, size - 1]");
        }
        Node removeNode = outLinkedL.get(index + 1);
        removeNode.remove();
        this.nelems -= 1;
        return outLinkedL.remove(index + 1).getElement();
    }

    /**
     * Clear the linked list
     */
    public void clear() {
        outLinkedL.clear();
        this.head = new Node(0);
        this.tail = new Node(1);
        this.nelems = 0;
        outLinkedL = new LinkedList<Node>();
        outLinkedL.add(head);
        outLinkedL.add(tail);
    }

    /**
     * Determine if the list empty
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        int emptyVal = 0;
        if (this.nelems == emptyVal) {
            return true;
        } else{
            return false;
        }
    }

    // Helper method to get the Node at the Nth index
    private Node getNth(int index) {
        return outLinkedL.get(index);
    }

    /**
     * Determine if this list contains the given data
     * @param data data to find
     * @return true if list contains given data, false otherwise
     */
    public boolean contains(int element) {
        for (int listIndex = 1; listIndex < size() - 1; listIndex++) {
            int currentElement = outLinkedL.get(listIndex).getElement();
            if (element == currentElement){
                return true;
            }
        }
        return false;
    }

    /**
     * String representation of this list in the form of:
     * "[(head) -> elem1 -> elem2 -> ... -> elemN -> (tail)]"
     * @return string representation
     */
    @Override
    public String toString() {
        String returnString = "[(head)";
        String arrowSpace = " -> ";
        for (int listIndex = 1; listIndex < size() - 1; listIndex++){
            returnString += arrowSpace + String.valueOf(outLinkedL.get(listIndex).getElement());
        }
        returnString += arrowSpace + "(tail)]";
        return returnString;
    }
}