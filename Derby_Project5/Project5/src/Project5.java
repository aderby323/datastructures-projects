import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * COP 3530: Project 5 - Hash Tables
 * Dr. Xudong Liu, University of North Florida
 * <p>
 * This class reads a .CSV file of countries provided by the user. Countries are inserted into a hash table, using
 * separate chaining to handle collisions, and displayed to the user. The countries Cyprus, Kazakhstan, Hungary, and
 * Japan are deleted from the hast table. Next, Costa Rica, North Cyprus, and Hungary are attempted to be located
 * within the hash table. The countries Zambia, Canada, Egypt, Yemen, and India are also deleted from the hash table.
 * Finally, the full contents of the hash table are displayed and the user is also shown the number of empty indexes
 * in the table along with the number of collisions occurring in the table.
 * </p>
 *
 * */

public class Project5 {

    public static HashTable countriesTable = new HashTable();
    //C:\\Users\Alex\Downloads\Countries5.csv

    /**
     * Main method. Calls the necessary functions to display the hash table, delete given countries from the table, and
     * print the number of empty indexes and collisions.
     * @param args Default parameters
     * */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        retrieveAndParse(input);
        countriesTable.display();

        countriesTable.delete("Cyprus");
        countriesTable.delete("Kazakhstan");
        countriesTable.delete("Hungary");
        countriesTable.delete("Japan");
        System.out.println();

        countriesTable.find("Costa Rica");
        countriesTable.find("North Cyprus");
        countriesTable.find("Hungary");
        System.out.println();

        countriesTable.delete("Zambia");
        countriesTable.delete("Canada");
        countriesTable.delete("Egypt");
        countriesTable.delete("Yemen");
        countriesTable.delete("India");
        System.out.println();

        countriesTable.display();

        countriesTable.printFreeAndCollisions();
    }

    /**
     * Prompts the user for a .csv file, parses the data for each country, and stores the countries
     * in a hash table. Takes in a Scanner object as an argument.
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
                    countriesTable.insert(c.getName(), c.gdpPerCapita);
                }
                successful = true;
                csvFile.close();
            } catch (FileNotFoundException f) {
                System.err.println("File not found");
                f.printStackTrace();
            }
        }
    }
}
