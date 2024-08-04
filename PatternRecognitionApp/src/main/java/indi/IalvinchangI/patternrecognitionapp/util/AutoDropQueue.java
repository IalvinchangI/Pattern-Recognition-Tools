package indi.IalvinchangI.patternrecognitionapp.util;

import java.util.LinkedList;

/**
 * AutoDropQueue is a queue whose capacity is limited
 * <p>
 * When you add a new element into a full AutoDropQueue, it will drop the oldest element
 * 
 * @author IalvinchangI
 */
public class AutoDropQueue<E> {

    /**
     * Create a new AutoDropQueue
     * @param capacity the capacity of AutoDropQueue
     */
    public AutoDropQueue(int capacity) {
        this.capacity = capacity;
        this.elements = new LinkedList<>();
    }

    /** the capacity of AutoDropQueue */
    private int capacity = 0;

    /**
     * head is the newest element
     * <p>
     * tail is the oldest element
     */
    private LinkedList<E> elements = null;


    /**
     * add new element into AutoDropQueue
     * @param element the element you want to add
     * @return If the AutoDropQueue is full, it will drop the oldest element
     */
    public E add(E element) {
        if (element == null) {
            return null;
        }
        
        E oldElement = null;
        if (this.elements.size() == this.capacity) {
            oldElement = this.elements.pollLast();  // tail
        }

        this.elements.addFirst(element);  // head

        return oldElement;
    }


    /**
     * Retrieves, but does not remove, the oldest element in AutoDropQueue
     * @return the oldest element in AutoDropQueue
     */
    public E peek() {
        if (this.elements != null) {
            return this.elements.getLast();  // tail
        }
        return null;
    }

    
    /**
     * remove all elements in AutoDropQueue
     */
    public void clear() {
        this.elements.clear();
    }
}
