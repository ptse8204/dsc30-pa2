/*
  Name: Edwin Tse
  PID:  A16616338
 */

import java.util.EmptyStackException;

/**
 * Stack implementation using Doubly-linked list.
 */
public class DLLStack {

    private DoublyLinkedList stack;

    public DLLStack() {
        this.stack = new DoublyLinkedList();
    }

    public int size() {
        return this.stack.size();
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    public void push(int data) {
        this.stack.add(data);
    }

    public int pop() {
        int stackSize = this.size();
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return this.stack.remove(this.size() - 1);
    }

    public int peek() {
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return this.stack.get(this.size() - 1);
    }

}
