import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project1 {
    /**
     * COP 3530: Project 1 - Array Searches and Sorts
     * <p>
     * This class takes in a .csv file of countries, with each country having information such as:
     * Name, Code, Capital, Population, GDP, and their Happiness Rank, parses the .csv file to create
     * an array of Country objects, and gives the user the ability to sort the array by name, happiness rank,
     * or GDP (per capita) of the Country objects. The user can also print a report of all the countries
     * or print an individual country.
     * <p>
     * @Alexander Derby
     * @1/31/2020
    * */

    public static Country[] countries = new Country[155];
    public static int countriesCount = 0;
    public static boolean isSuccessful = false, isSortedByName = false;

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        retrieveAndParse(input);
        while (isSuccessful) {
            selection();
        }
    }

    /**
     * This method retrieves the file that the user provides, parses the data to each country, and stores the countries
     * in an Array. Takes in a Scanner object as an argument.
     * @param input Uses a Scanner object to gather user input.
    */
    public static void retrieveAndParse(Scanner input) {
        while (!isSuccessful) {
            System.out.println("Enter the name of the file: ");
            String source = input.nextLine();
            try {
                Scanner csvFile = new Scanner(new File(source));
                while (csvFile.hasNextLine()) {
                    String row = csvFile.nextLine();
                    if (row.startsWith("Country")) {
                        continue;
                    }
                    String[] rowArray = row.split(",");
                    Country c = new Country(rowArray[0], rowArray[1], rowArray[2], (rowArray[3]), (rowArray[4]), (rowArray[5]));
                    countries[countriesCount] = c;
                    ++countriesCount;
                }
                System.out.println("There are " + (countriesCount) + " countries recorded.");
                csvFile.close();
                isSuccessful = true;
            } catch (FileNotFoundException f) {
                System.err.println("File not found");
            }
        }
    }

    /**
     * The main space where the user selects various methods including: printing a full report of the countries stored in the Array, sorting the countries by country name,
     * sorting the countries by their Happiness Rank, sorting the countries by their GDP per capita, and/or finding a desired country and printing all information on the
     * requested country. The user is also given the option to quit and terminate the program.
     */
    public static void selection() {
        Scanner input = new Scanner(System.in);
        System.out.println("1. Print a report of all countries");
        System.out.println("2. Sort countries by name");
        System.out.println("3. Sort countries by happiness");
        System.out.println("4. Sort countries by GDP per capita");
        System.out.println("5. Find and print a given country");
        System.out.println("6. Quit");
        System.out.print("Enter your choice: ");
        String choice = input.next();
        input.nextLine();

        switch (choice) {
            case "1":
                printReport();
                break;
            case "2":
                sortName();
                System.out.println("Countries sorted by name.");
                break;
            case "3":
                sortHappiness();
                System.out.println("Countries sorted by happiness rank.");
                break;
            case "4":
                sortGDP();
                System.out.println("Countries sorted by GDP per capita.");
                break;
            case "5":
                System.out.println("Enter the country name");
                String name = input.nextLine();
                findCountry(name);
                break;
            case "6":
                System.out.println("\nThanks! Have a great day!");
                System.exit(0);
            default:
                System.out.print("Invalid choice. Enter a number between 1-6.\n");
                break;
        }
    }

    /**
     * Displays a full report of every country in the Countries array. Every country is displayed line-by-line.
     */
    public static void printReport() {
        System.out.format("%-35s%-6s%-30s%-12s%-14s%-3s", "Name", "Code", "Capital", "Population", "GDP", "HappinessRank");
        System.out.println("\n----------------------------------------------------------------------------------------------");
        for (int i = 0; i < countries.length; i++) {
            System.out.println(countries[i]);
        }
    }

    /**
     * Sorts the countries in the Countries array by Name using Bubble sort.
     */
    public static void sortName() {
        Country c, temp;
        for (int i = countries.length - 1; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                c = countries[j];
                if (c.compareCountry(countries[j + 1])) {
                    temp = countries[j];
                    countries[j] = countries[j + 1];
                    countries[j + 1] = temp;
                }
            }
        }
        isSortedByName = true;
    }

    /**
     * Sorts the countries in the Countries array by Happiness Rank using Selection sort.
     */
    public static void sortHappiness() {
        Country c, minC, temp;
        int min;

        for (int i = 0; i < countries.length - 1; i++) {
            minC = countries[i];
            min = i;
            for (int j = i + 1; j < countries.length; j++) {
                c = countries[j];
                if (Integer.parseInt(countries[j].getHapRank()) < Integer.parseInt(minC.getHapRank())) {
                    minC = c;
                    min = j;
                }
            }
            temp = countries[i];
            countries[i] = minC;
            countries[min] = temp;
        }
        isSortedByName = false;
    }

    /**
     * Sorts the countries in the Countries array by GDP per capita using Insertion sort.
     */
    public static void sortGDP(){
        Country c;
        int in, out;
        double temp;

        for (out = 1; out < countries.length; out++) {
            c = countries[out];
            temp = (Double.parseDouble(c.getGdp()) / Double.parseDouble(c.getPopulation()));
            in = out - 1;

            while (in >= 0 && (Double.parseDouble(countries[in].getGdp()) / Double.parseDouble(countries[in].getPopulation())) > temp) {
                countries[in+1] = countries[in];
                in = in - 1;
            }
            countries[in+1] = c;
        }
        isSortedByName = false;
    }

    /**
     * Attempts to locate a country in the Countries array and display it. If the Countries array is sorted by name,
     * use Binary search to find the country. Otherwise, use sequential search.
     * @param name: Takes in a String input to compare with the countries in the array.
     */
    public static void findCountry(String name){
        Country c;
        if (isSortedByName) {
            int first = 1, last = countries.length - 1,middle;

            while (first <= last) {
                middle = (first + last) / 2;
                if (countries[middle].getName().compareToIgnoreCase(name) < 0) {
                    first = middle + 1;
                } else if (countries[middle].getName().compareToIgnoreCase(name) > 0) {
                    last = middle - 1;
                } else {
                    c = countries[middle];
                    System.out.println("Binary search");
                    c.printCountry(c);
                    break;
                }
            }
            if (first <= last) {
                System.out.println("The country "+ name +" was not found.");
            }
        }
        else {
            boolean ran = false;
            for (int i = 0; i < countries.length; i++) {
                if (countries[i].getName().compareToIgnoreCase(name) == 0) {
                    c = countries[i];
                    System.out.println("Sequential search");
                    c.printCountry(c);
                    ran = true;
                }
            }
            if (!ran) {
                System.out.println("The country "+ name +" was not found.");
            }
        }
    }
}
