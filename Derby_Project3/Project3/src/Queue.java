public class Queue {

    /**
     * Nested class. Defines a Node for the doubly linked list queue.
     * */
    private class Node {
        Country country;
        Node next;
        Node prev;

        /**
         * Constructor that creates a new Node with Country data.
         * */
        private Node(Country c){
            country = c;
        }
    }

    /**
     * Implements a double-ended doubly linked list as a Queue.
     *
     * @author Alex Derby
     * @version 3/12/2020
     * */
    private Node first, last;
    private int size;

    /**
     * Default constructor. Creates an empty queue.
     * */
    public Queue() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Inserts a new Node at the end of the queue.
     * @param c Takes in a new Country object to create a new Node with.
     * */
    public void insertEnd(Country c){
        Node temp = new Node(c);
        if (isEmpty()){
            first = temp;
            last = temp;
        }
        else {
            last.next = temp;
            temp.prev = last;
            last = temp;
        }
        first.prev = last;
        last.next = first;
        size++;
    }

    /**
     * Inserts a new Node at the front of the queue.
     * @param c Takes in a new Country object to create a new Node with.
     * */
    public void insertFront(Country c){
        Node temp = new Node(c);
        if (isEmpty()){
            first = temp;
            last = temp;
        }
        else {
            temp.next = first;
            first.prev = temp;
            first = temp;
        }
        first.prev = last;
        last.next = first;
        size++;
    }

    /**
     * Removes a Node from the end of the queue.
     * @return Returns the country associated with the last node in the queue.
     * */
    public Country removeEnd(){
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        Node current = last;
        last = current.prev;
        last.next = first;
        first.prev = last;
        size--;

        return current.country;
    }

    /**
     * Removes a Node from the front of the queue.
     * @return Returns the country associated with the first node in the queue.
     * */
    public Country removeFront(){
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        Node current = first;
        first = current.next;
        first.prev = last;
        last.next = first;
        size--;

        return current.country;
    }

    /**
     * Deletes nodes in the queue whose associated country's GDP per capita is between a given interval.
     * @param min Minimum boundary
     * @param max Maximum boundary
     * @return inRange Returns true if any country was found inside of the boundaries. Otherwise, false.
     * */
    public boolean intervalDelete(double min, double max){
        Node current = first;
        boolean inRange = false;
        while (current != null){
            if (current.country.gdpPerCapita >= min && current.country.gdpPerCapita <= max) {
                inRange = true;
                if (current == first){
                    removeFront();
                }
                if (current == last) {
                    removeEnd();
                }
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    size--;
                }
            }
            if (current.next == first){
                break;
            }
            current = current.next;
        }
        return inRange;
    }

    /**
     * Prints the contents of the linked list queue.
     * */
    public void printQueue(){
        Node current = first;
        while (current != null){
            System.out.println(current.country);
            if (current == last){
                return;
            }
            current = current.next;
        }
    }

    /**
     * Checks if the queue is empty.
     * @return Returns true if the queue is empty. Otherwise, false.
     * */
    public boolean isEmpty(){
        return (first == null || last == null || size == 0);
    }

    /**
     * Checks if the queue is full.
     * @return Always returns false.
     * */
    public boolean isFull(){
        return false;
    }
}
