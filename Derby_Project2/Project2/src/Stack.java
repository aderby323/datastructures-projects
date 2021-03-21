public class Stack {
    /**
     * This class represents a LIFO (Last-In, First-Out) stack of Country objects.
     * @author Alexander Derby, N01037933
     * @version 2/20/2020
     * */

    private int stackSize, point = -1;
    private Country[] stack;

    /**
     * Constructor
     * @param size User creates a new Stack with a given size
     * */
    public Stack(int size){
        stackSize = size;
        stack = new Country[size];
    }

    /**
     * Pushes a Country object to the Stack
     * @param c Country object to be pushed
     * */
    public void Push(Country c){
        stack[++point] = c;
    }

    /**
     * Pops a Country object from the Stack
     * @return Country object at index
     * */
    public Country Pop(){
        return stack[point--];
    }

    /**
     * Checks Stack to determine if it is empty
     * @return True if Stack is empty, False otherwise
     * */
    public boolean isEmpty(){
        return point < 0;
    }

    /**
     * Checks Stack to determine if it is full
     * @return True if Stack is full, False otherwise
     * */
    public boolean isFull(){
        return point == stackSize -1;
    }

    /**
     * Pops and prints all the Country objects from the Stack until the Stack is empty
     * */
    public void printStack(){
        Country c;
        while (!isEmpty()){
            c = Pop();
            System.out.println(c);
        }
    }
}
