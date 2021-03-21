import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * COP 3530: Project 3 - Linked Lists
 * <p>
 * This class takes in a .csv file of countries provided by the user. Country objects are created for each corresponding
 * country in the file but only those with a GDP per capita in the range of POOR (GDP per capita less than 1,000), GOOD
 * (GDP per capita from 5,000 to 19,999), or EXCELLENT (GDP per capita from 50,000 and above) are utilized and stored in
 * a double-ended singly linked list stack implementation. The stack is then printed.
 * </p>
 *
 * <p>
 * A double-ended doubly linked list queue implementation is then created. Items are popped from the stack one at a time,
 * in intervals from inserting into the front of the queue to the end and so forth until the stack is empty. The queue is
 * then printed. A method is called that deleted countries with a GDP between two given values from the queue. When the
 * method is finished, the queue is printed again. Finally, items are removed from the queue in intervals from the front
 * of the queue to the end and so forth until the queue is empty and pushed to the stack. The stack is printed and the
 * program exits.
 * </p>
 *
 * @author Alexander Derby
 * @version 3/12/2020
 * */
public class Project3 {

    private static Stack stackList = new Stack();
    private static Queue queueList = new Queue();
    //C:\\Users\Alex\Downloads\Countries3.csv

    /**
     * Main class. Prints contents of the stack, inserts elements into queue from stack, prints contents of queue, calls
     * the interval delete method with 50,000.00 and 70,000.00 as the parameters, prints the queue again, pushes elements
     * from the queue to the stack, and finally prints the stack.
     * @param args Default
     * */
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        retrieveAndParse(input);
        System.out.println("\nStack Contents: ");
        printHeader();
        stackList.printStack();
        pushToQueue();
        System.out.println("\nQueue Contents (Stack -> Queue): ");
        printHeader();
        queueList.printQueue();
        queueList.intervalDelete(50000.00,70000.00);
        System.out.println("\nQueue Contents after interval delete: ");
        printHeader();
        queueList.printQueue();
        pushToStack();
        System.out.println("\nStack Contents: ");
        printHeader();
        stackList.printStack();
    }

    /**
     * Prompts the user for a .csv file, parses the data for each country, and only stores countries with a POOR,
     * GOOD, or EXCELLENT GDP per capita into a double-ended singly linked list stack.
     * @param input: Uses a Scanner object to gather user input.
     **/
    public static void retrieveAndParse(Scanner input) {
        boolean successful = false;
        while (!successful) {
            System.out.println("Enter the name of the .csv file: ");
            String source = input.nextLine();
            try {
                Scanner csvFile = new Scanner(new File(source));
                while (csvFile.hasNextLine()) {
                    String row = csvFile.nextLine();
                    if (row.startsWith("Country")) {
                        continue;
                    }
                    String[] rowArray = row.split(",");
                    Country c = new Country(rowArray[0], rowArray[1], rowArray[2], rowArray[3], rowArray[4], rowArray[5]);
                    if (checkCapita(c)){
                        stackList.Push(c);
                    }
                }
                successful = true;
                csvFile.close();
            } catch (FileNotFoundException f) {
                System.err.println("File not found");
                f.printStackTrace();
            }
        }
    }

    /**
     * Checks a Country's GDP per capita to determine if it is a POOR, GOOD, or EXCELLENT country.
     * @param country: Takes i na Country object to view its GDP per capita
     * @return True if Country's GDP per capita is: < 1000, 5000 < 20,000, or > 50,000
     **/
    public static boolean checkCapita(Country country) {
        return (country.gdpPerCapita < 1000.00 ||
                country.gdpPerCapita >= 5000.00 && country.gdpPerCapita < 20000.00 ||
                country.gdpPerCapita >= 50000.00);
    }

    /**
     * Pushes elements from the Stack into the Queue, in intervals of the front to the end and so forth.
     * */
    public static void pushToQueue(){
        boolean odd = true;
        while (!stackList.isEmpty()){
            if (odd){
                queueList.insertFront(stackList.Pop());
                odd = false;
                continue;
            }
            queueList.insertEnd(stackList.Pop());
            odd = true;
        }
    }

    /**
     * Pushes elements from the Queue into the Stack, in intervals of the front to the end and so forth.
     * */
    public static void pushToStack() {
        boolean odd = true;
        while(!queueList.isEmpty()) {
            if (odd) {
                stackList.Push(queueList.removeFront());
                odd = false;
                continue;
            }
            stackList.Push(queueList.removeEnd());
            odd = true;
        }
    }

    /**
     * Prints a header for each Queue/Stack of Countries
     * */
    public static void printHeader(){
        System.out.format("%-35s%-6s%-30s%-12s%-14s%-3s\n", "Name", "Code", "Capital", "Population", "GDP", "HapRank");
        System.out.println(String.format("%110s", "-").replace(" ", "-"));
    }
}
