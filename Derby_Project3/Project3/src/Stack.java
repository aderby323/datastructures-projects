public class Stack {

    /**
     * Nested class. Defines a Node for the singly linked list stack.
     * */
    private class Node {
        Country country;
        Node next;

        /**
         * Constructor that creates a new Node with Country data.
         * */
        private Node(Country c){
            country = c;
        }
    }

    /**
     * Implements a double-ended singly linked list as a Stack.
     *
     * @author Alex Derby
     * @version 3/12/2020
     * */
    private Node first, last;
    private int size;

    /**
     * Default constructor. Creates an empty stack.
     * */
    public Stack(){
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Pushes a Country to the top of the stack.
     * @param c Takes in a Country object to create a new Node.
     * */
    public void Push(Country c){
        Node temp = new Node(c);

        if (isEmpty()){
            first = temp;
            last = temp;
        }
        else {
            temp.next = first;
            first = temp;
        }
        size++;
    }

    /**
     * Pops a Country from the top of the stack.
     * @return Returns the country associated with the first node in the list.
     * */
    public Country Pop(){
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        Country c = first.country;
        first = first.next;
        size--;

        return c;
    }

    /**
     * Prints the contents of the linked list stack.
     * */
    public void printStack(){
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
     * Determines if the stack is empty.
     * @return Returns true if the stack is empty. Otherwise, false.
     * */
    public boolean isEmpty(){
        return first == null;
    }

    /**
     * Determines if the stack is full.
     * @return Always returns false.
     * */
    public boolean isFull(){
        return false;
    }
}
