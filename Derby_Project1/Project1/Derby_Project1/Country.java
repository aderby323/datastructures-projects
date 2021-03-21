import java.util.Formatter;

public class Country
{
    /**
     * This class serves as the blueprint for creating a Country object. The country contains the country's name,
     * international code, capital, population, GDP, and Happiness Rank.
     * @Alexander Derby
     * @1/31/2020
     */
    private String name, code, capital, population, gdp, hapRank;

    public Country(String name, String code, String capital, String population, String gdp, String hapRank) {
        this.name = name;
        this.code = code;
        this.capital = capital;
        this.population = population;
        this.gdp = gdp;
        this.hapRank = hapRank;
    }

    /**
     * Returns the name of the country.
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the country.
     * @param name: Takes in a String input and assigns the name of the country as the input.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the code of the country.
     * @return code
     */
    public String getCode() {
        return code;
    }
    /**
     * Sets the code of the country.
     * @param code: Takes in a String input and assigns the code of the country as the input.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns the capital of the country.
     * @return capital
     */
    public String getCapital() {
        return capital;
    }
    /**
     * Sets the capital of the country.
     * @param capital: Takes in a String input and assigns the capital of the country as the input.
     */
    public void setCapital(String capital) {
        this.capital = capital;
    }

    /**
     * Returns the population of the country.
     * @return population
     */
    public String getPopulation() {
        return population;
    }
    /**
     * Sets the population of the country.
     * @param population: Takes in a String input and assigns the population of the country as the input.
     */
    public void setPopulation(String population) {
        this.population = population;
    }

    /**
     * Returns the GDP of the country.
     * @return gdp
     */
    public String getGdp() {
        return gdp;
    }
    /**
     * Sets the GDP of the country.
     * @param gdp: Takes in a String input and assigns the GDP of the country as the input.
     */
    public void setGdp(String gdp) {
        this.gdp = gdp;
    }

    /**
     * Returns the Happiness Rank of the country.
     * @return code
     */
    public String getHapRank() {
        return hapRank;
    }
    /**
     * Sets the Happiness Rank of the country.
     * @param hapRank: Takes in a String input and assigns the Happiness Rank of the country as the input.
     */
    public void setHapRank(String hapRank) {
        this.hapRank = hapRank;
    }

    /**
     * Compares two countries by name and returns true if the country's names match, otherwise false.
     * @param c: Takes in a Country object to compare against.
     * @return True if two countries match and False if countries do not match.
     */
    public boolean compareCountry(Country c) {
        return this.name.compareTo(c.name) > 0;
    }

    /**
     * Prints a Country object, formatted to display each field in a new line. Intended for displaying a
     * single country.
     * @param c: Takes in a Country object to display
     */
    public void printCountry(Country c) {
        System.out.format("\n%-17s%-30s\n", "Name: ", c.getName());
        System.out.format("%-17s%-30s\n", "Code: ", c.getCode());
        System.out.format("%-17s%-30s\n", "Capital: ", c.getCapital());
        System.out.format("%-17s%-30s\n", "Population: ", c.getPopulation());
        System.out.format("%-17s%-30s\n", "GDP: ", c.getGdp());
        System.out.format("%-17s%-30s\n\n", "Happiness Rank: ", c.getHapRank());
    }

    /**
     * Overrides the toString() method in Object superclass.
     * @return countryBuild.toString(): Displays a Country object, formatted to fit in one line.
     */
    @Override
    public String toString(){
        String formatTemplate;
        StringBuilder countryBuild = new StringBuilder();
        Formatter countryFormat = new Formatter(countryBuild);
        formatTemplate = "%-35s%-6s%-30s%-12s%-14s%-3s";
        countryFormat.format(formatTemplate,name,code,capital,population,gdp,hapRank);
        return countryBuild.toString();
    }
}
