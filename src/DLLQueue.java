/*
  Name: Edwin Tse
  PID:  A16616338
 */

import java.util.NoSuchElementException;

/**
 * Queue implementation using Doubly-linked list.
 */
public class DLLQueue {

    // instance variables
    private DoublyLinkedList queue;

    /**
     * Initialize the DLLQueue class
     */
    public DLLQueue() {
        this.queue = new DoublyLinkedList();
    }

    /**
     * Return the number of elements currently stored in this queue.
     * @return int number of elements
     */
    public int size() {
        return this.queue.size();
    }

    /**
     * Return true if this queue is empty, false otherwise.
     * @return boolean true if this queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    /**
     * Add the given data to this queue.
     * @param data int data that would added into the queue
     */
    public void enqueue(int data) {
        this.queue.add(data);
    }

    /**
     * Remove and return the first element from this queue.
     * @return int first element from this queue
     */
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.queue.remove(0);
    }

    /**
     * Peek and return the top element from this queue.
     * @return int the top element from this queue
     */
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.queue.get(0);
    }

}
