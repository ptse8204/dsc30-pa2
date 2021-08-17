/*
  Name: Edwin Tse
  PID:  A16616338
 */

import java.util.NoSuchElementException;

/**
 * Queue implementation using Doubly-linked list.
 */
public class DLLQueue {

    private DoublyLinkedList queue;

    public DLLQueue() {
        this.queue = new DoublyLinkedList();
    }

    public int size() {
        return this.queue.size();
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public void enqueue(int data) {
        this.queue.add(data);
    }

    public int dequeue() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return this.queue.remove(0);
    }

    public int peek() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return this.queue.get(0);
    }

}
