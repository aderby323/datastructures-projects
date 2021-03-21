import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * COP 3530: Project 4 - Binary Search Trees
 * Dr. Xudong Liu
 * <p>
 * This class reads a .CSV file of countries provided by the user. Countries are inserted into a binary search tree,
 * with each node in the tree containing the Name of the country and the GDP per capita, and sorted by name. The
 * following procedures are then called regarding the binary search tree created:
 * </p>
 *
 * <p>
 * 1) The tree is printed through Inorder traversal.
 * 2) The countries Australia, Greece, and Norway are deleted from the tree.
 * 3) The tree is printed through Preorder traversal.
 * 4) The tree attempts to locate the countries Sri Lanka, North Cyprus, Greece, and Australia and return their GDP per
 * capita.
 * 5) The countries Malawi, Somalia, Canada, Tunisia, and New Zealand are deleted from the tree.
 * 6) The tree is printed through Postorder traversal.
 * 7) The 5 lowest/highest countries in regards to GDP per capita are printed. The program exits.
 * </p>
 *
 * @author Alexander Derby
 * @version 04/09/2020
 * */

public class Project4 {

    private static BinarySearchTree countries = new BinarySearchTree();
    //C:\\Users\Alex\Downloads\Countries4.csv
    /**
     * Main method. Inputs all countries into the tree, prints the tree inorder, deletes given countries from the tree,
     * prints the tree preorder, finds given countries within the tree, deletes another selection of given countries,
     * prints the tree postorder, and prints the lowest/highest five countries in the tree regarding GDP per capita.
     * */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        retrieveAndParse(input);

        System.out.println("\nInorder Traversal");
        printHeader();
        countries.printInorder(countries.getRoot());

        countries.delete("Australia");
        countries.delete("Greece");
        countries.delete("Norway");

        System.out.println("\nPreorder Traversal");
        printHeader();
        countries.printPreorder(countries.getRoot());

        countries.find("Sri Lanka");
        countries.find("North Cyprus");
        countries.find("Greece");
        countries.find("Australia");

        countries.delete("Malawi");
        countries.delete("Somalia");
        countries.delete("Canada");
        countries.delete("Tunisia");
        countries.delete("New Zealand");

        System.out.println("\nPostorder Traversal");
        printHeader();
        countries.printPostorder(countries.getRoot());

        System.out.println("\nBottom five countries regarding GDP per capita");
        printHeader();
        countries.printBottomFive();
        System.out.println("\nTop five countries regarding GDP per capita");
        printHeader();
        countries.printTopFive();
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
                Scanner csvFile = new Scanner(new File(source));
                while (csvFile.hasNextLine()) {
                    String row = csvFile.nextLine();
                    if (row.startsWith("Country")) {
                        continue;
                    }
                    String[] rowArray = row.split(",");
                    Country c = new Country(rowArray[0], rowArray[1], rowArray[2], rowArray[3], rowArray[4], rowArray[5]);
                    countries.insert(c.getName(),c.gdpPerCapita);
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
     * Prints a header when displaying the list of countries in the tree
     * */
    public static void printHeader(){
        System.out.format("%-35s%-20s\n", "Name", "GDP");
        System.out.println(String.format("%50s", "-").replace(" ", "-"));
    }
}
