/*
  Name: Edwin Tse
  PID:  A16616338
 */

import java.util.EmptyStackException;

/**
 * Stack implementation using Doubly-linked list.
 */
public class DLLStack {

    // Declaring instance variable
    private DoublyLinkedList stack;

    /**
     * Initialize the instance variables of this stack.
     */
    public DLLStack() {
        this.stack = new DoublyLinkedList();
    }

    /**
     * Return the number of elements currently stored in this stack.
     * @return int the number of elements currently stored in this stack
     */
    public int size() {
        return this.stack.size();
    }

    /**
     * Return true if this stack is empty, false otherwise.
     * @return boolean true if this stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    /**
     * Add the given data to this stack.
     * @param data int the the data that would be added to the stack
     */
    public void push(int data) {
        this.stack.add(data);
    }

    /**
     * Remove and return the top element from this stack.
     * @return int the top element from this stack
     */
    public int pop() {
        int stackSize = this.size();
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return this.stack.remove(this.size() - 1);
    }

    /**
     * Peek and return the top element from this stack.
     * @return int the top element from this stack
     */
    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return this.stack.get(this.size() - 1);
    }

}
