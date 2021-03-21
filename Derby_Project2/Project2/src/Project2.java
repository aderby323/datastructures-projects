import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
* COP 3538: Project 2 - Stacks & Priority Queues
 * <p>
 * This class takes in a .csv file of countries provided by the user. Country objects are created for each corresponding
 * country in the file and are placed within 5 Queues bases on their GDP per capita. These Queues range from:
 * Poor (GDP per capita less than 1,000), Fair (GDP per capita from 1,000 to 4,999), Good (GDP per capita from
 * 5,000 to 19,999), Very Good (GDP per capita from 20,000 to 49,999), and Excellent (GDP per capita from 50,000
 * and above).
 * </p>
 *
 * <p>
 * The Queues are printed in order from least GDP per capita to highest GDP per capita. Finally, a Stack is then created
 * which removes items from each Queue (Poor to Excellent, one-by-one) and prints the Stack.
 * </p>
 * @author Alexander Derby, N01037933
 * @version 2/20/2020
 **/
public class Project2
{
    private static Priority poorCountries = new Priority(50);
    private static Priority fairCountries = new Priority(50);
    private static Priority goodCountries = new Priority(50);
    private static Priority veryGoodCountries = new Priority(25);
    private static Priority excellentCountries = new Priority(20);
    private static int countriesCount;

    /**
     * Main method. Calls method to retrieve a .csv file and parse each Country object to a Queue based on GDP
     * per capita. Each Queue is printed from Poor to Excellent. A Stack object is created to remove
     * each Country from each Queue, one-by-one, and push it to the Stack. Finally, the Stack is printed.
     * @param args: Default
     **/
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        retrieveAndParse(input);
        System.out.println("POOR");
        printHeader();
        poorCountries.printQueue();

        System.out.println("\n\nFAIR");
        printHeader();
        fairCountries.printQueue();

        System.out.println("\n\nGOOD");
        printHeader();
        goodCountries.printQueue();

        System.out.println("\n\nVERY GOOD");
        printHeader();
        veryGoodCountries.printQueue();

        System.out.println("\n\nEXCELLENT");
        printHeader();
        excellentCountries.printQueue();

        System.out.println("\n\nStack of Countries");
        printHeader();
        Stack countriesStack = new Stack(countriesCount);
        pushToStack(countriesStack);
        countriesStack.printStack();
        input.close();
    }

    /**
     * Prompts the user for a .csv file, parses the data for each country, and stores the countries
     * in their corresponding Queue. Takes in a Scanner object as an argument.
     * @param input: Uses a Scanner object to gather user input.
     **/
    public static void retrieveAndParse(Scanner input) {
        boolean successful = false;
        while (!successful) {
            System.out.println("Enter the name of the .csv file: ");
            String source = input.nextLine();
            try {
                countriesCount = 0;
                Scanner csvFile = new Scanner(new File(source));
                while (csvFile.hasNextLine()) {
                    String row = csvFile.nextLine();
                    if (row.startsWith("Country")) {
                        continue;
                    }
                    String[] rowArray = row.split(",");
                    Country c = new Country(rowArray[0], rowArray[1], rowArray[2], rowArray[3], rowArray[4], rowArray[5]);
                    enqueueCountries(c);
                    countriesCount++;
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
     * Places Country objects in a Queue based on their GDP per capita.
     * @param country: Takes in a Country object to view its GDP per capita and assign it to
     * a corresponding Queue (Poor, Fair, Good, Very Good, or Excellent).
     **/
    public static void enqueueCountries(Country country){
        if (country.gdpPerCapita < 1000.00){
            poorCountries.Enqueue(country);
        }
        else if (country.gdpPerCapita >= 1000.00 && country.gdpPerCapita < 5000.00){
            fairCountries.Enqueue(country);
        }
        else if (country.gdpPerCapita >= 5000.00 && country.gdpPerCapita < 20000.00){
            goodCountries.Enqueue(country);
        }
        else if (country.gdpPerCapita >= 20000.00 && country.gdpPerCapita < 50000.00){
            veryGoodCountries.Enqueue(country);
        }
        else{
            excellentCountries.Enqueue(country);
        }
    }

    /**
     * Removes Country objects from each Queue, starting from lowest GDP per capita to highest, and
     * pushes them to the Stack.
     * @param countriesStack: Takes in a Stack object to push Countries into.
     * @return Returns a Stack with inserted Countries.
     **/
    public static Stack pushToStack(Stack countriesStack){
        while (!poorCountries.isEmpty()){
            countriesStack.Push(poorCountries.Dequeue());
        }
        while (!fairCountries.isEmpty()){
            countriesStack.Push(fairCountries.Dequeue());
        }
        while (!goodCountries.isEmpty()){
            countriesStack.Push(goodCountries.Dequeue());
        }
        while (!veryGoodCountries.isEmpty()){
            countriesStack.Push(veryGoodCountries.Dequeue());
        }
        while (!excellentCountries.isEmpty()){
            countriesStack.Push(excellentCountries.Dequeue());
        }
        return countriesStack;
    }

    /**
     * Prints a header for each Queue/Stack of Countries
     * */
    public static void printHeader(){
        System.out.format("%-35s%-6s%-30s%-12s%-14s%-3s\n", "Name", "Code", "Capital", "Population", "GDP", "HapRank");
        System.out.println(String.format("%110s", "-").replace(" ", "-"));
    }
}
