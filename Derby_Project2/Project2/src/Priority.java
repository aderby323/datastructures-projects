public class Priority {
    /**
     * This class represents a "First-Priority-In, First-Priority-Out" queue of Country objects.
     * @author Alexander Derby, N01037933
     * @version 2/20/2020
     * */

    private int queueSize;
    private int items;
    private Country[] priorityQueue;

    /**
     * Constructor
     * @param size User creates a new Priority Queue with a given size
     * */
    public Priority(int size){
        queueSize = size;
        priorityQueue = new Country[queueSize];
        items = 0;
    }

    /**
     * Pushes a new Country to the Priority Queue. The higher the GDP per capita, the higher the priority of the
     * Country
     * @param c Country object to push to the Queue
     * */
    public void Enqueue(Country c){
        int i;
        if (items == 0){
            priorityQueue[items++] = c;
        }
        else {
            for (i = items - 1; i >= 0; i--) {
                if (c.gdpPerCapita < priorityQueue[i].gdpPerCapita){
                    priorityQueue[i+1] = priorityQueue[i];
                }
                else {
                    break;
                }
            }
            priorityQueue[i+1] = c;
            items++;
        }
    }

    /**
     * Pops a Country from the Queue
     * @return Country with the highest GDP per capita in the Queue
     * */
    public Country Dequeue(){
        return priorityQueue[--items];
    }

    /**
     * Prints all Countries in the Queue from highest Priority to least Priority
     * */
    public void printQueue(){
        Country c;
        int i = items;
        while (i > 0) {
            c = priorityQueue[--i];
            System.out.println(c);
        }
    }
    /**
     * Checks if Queue is empty
     * @return True if the Queue is empty, otherwise False
     * */
    public boolean isEmpty(){
        return items == 0;
    }


    /**
     * Checks if Queue is full
     * @return True if the Queue is full, otherwise False
     * */
    public boolean isFull(){
        return items == queueSize;
    }
}
