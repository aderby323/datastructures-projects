/**
 * COP 3530: Project 5 - Hash Tables
 * Dr. Xudong Liu, University of North Florida
 *
 * <p>
 * This class serves as the blueprint for a hash table. Each index contains a linked list of nodes, with nodes
 * storing a country's name and GDP per capita.
 * </p>
 * */

public class HashTable {

    /**
     * Nested class that defines a Node that contains the Name of a country and its GDP per capita.
     * */
    private class Node {
        String name;
        double gdpPerCapita;
        Node nextNode;

        /**
         * Constructor: Creates a Node with a Name and GDP per capita.
         * */
        public Node(String name, double gdpPerCapita) {
            this.name = name;
            this.gdpPerCapita = gdpPerCapita;
        }

        /**
         * Prints the contents of the Node.
         * */
        public void printNode() {
            System.out.printf("%-35s%,-20.2f\n", name, gdpPerCapita);
        }
    }

    /**
     * Nested class that defines a Double-Ended, singly Linked List of Nodes
     * */
    private class NodeLL {

        public Node first, last;

        /**
         * Constructor: Defines the first and last Nodes in the Linked List as null.
         * */
        public NodeLL() {
            first = null;
            last = null;
        }

        /**
         * Takes in a Node to be inserted into the end of the Linked List.
         * @param n: Node to be inserted into the Linked List
         * */
        public void insert(Node n) {
            if (isEmpty()) {
                first = n;
            }
            else {
                last.nextNode = n;
            }
            last = n;
        }

        /**
         * Takes in the name of a country to be deleted from the Linked List.
         * @param name: Name of country to be deleted
         * */
        public void delete(String name) {
            Node current = first;
            Node prev = first;

            if (isEmpty()) {
                throw new IllegalStateException();
            }

            while (!current.name.equalsIgnoreCase(name)) {
                if (current.nextNode == null) {
                    System.out.println("Node not found.");
                    return;
                }
                else {
                    prev = current;
                    current = current.nextNode;
                }
            }

            if (current == first) {
                first = first.nextNode;
            }
            else {
                prev.nextNode = current.nextNode;
            }
            System.out.println(name + " has been deleted.");
        }

        /**
         * Traverses the Linked List and prints every Node's contents.
         * */
        public void traverse() {
            Node current = first;
            if (first == null) {
                System.out.println("Empty");
            }
            while(current != null) {
                if (current == first) {
                    System.out.printf("%-35s%,-20.2f\n", current.name, current.gdpPerCapita);
                }
                else {
                    System.out.printf("\t %-35s%,-20.2f\n", current.name, current.gdpPerCapita);
                }
                current = current.nextNode;
            }
        }

        /**
         * Determines if Linked List is empty.
         * @return Returns true if Linked List is empty. Otherwise, false.
         * */
        public boolean isEmpty() {
            return (first == null);
        }

    }


    private Node current;
    private int key;
    private NodeLL[] hashTable;
    private final int HASH_SIZE = 311;

    /**
     * Constructor: Creates a hash table with the default size of 311 and fills each index with a linked list of nodes.
     * */
    public HashTable() {
        hashTable = new NodeLL[HASH_SIZE];
        for (int i = 0; i < HASH_SIZE; i++) {
            hashTable[i] = new NodeLL();
        }
    }

    /**
     * Takes in a string and generates a key by adding the Unicode values of all the characters in the string and
     * modulus the result by the hash size.
     * @param name: Country name to be hashed.
     * @return Returns the key.
     * */
    public int hash(String name) {
        key =  0;
        for (char c : name.toCharArray()) {
            key += (int) c;
        }
        key = key % HASH_SIZE;
        return key;
    }

    /**
     * Takes in a Country name and its GDP per capita, creates a Node with the inputs, generates a key based on the
     * country name, and inserts the Node into the proper index of the table.
     * @param country, gdpPerCapita: Country name and GDP per capita used to create a Node.
     * */
    public void insert(String country, double gdpPerCapita) {
        Node temp = new Node(country, gdpPerCapita);
        key = hash(country);
        hashTable[key].insert(temp);
    }

    /**
     * Uses the name of a country to generate a key to identify the index and search the linked list at the index. Scans
     * the list for the country. If found, its GDP per capita is returned. Otherwise, returns -1 and Not Found.
     * @param country: Country name to be found.
     * @return Returns GDP per capita if found. -1 otherwise.
     * */
    public double find(String country) {
        key = hash(country);
        current = hashTable[key].first;
        while(current != null) {
            if (current.name.equalsIgnoreCase(country)) {
                System.out.printf("%s was found with a GDP per capita of %.2f\n",country, current.gdpPerCapita);
                return current.gdpPerCapita;
            }
            current = current.nextNode;
        }
        System.out.println(country+ " was not found.");
        return -1;
    }

    /**
     * Deletes a given country from the hash table.
     * @param country Name of country to be deleted.
     * */
    public void delete(String country) {
        key = hash(country);
        hashTable[key].delete(country);
    }

    /**
     * Prints all the nodes in the hash table.
     * */
    public void display() {
        System.out.println();
        for (int i = 0; i < HASH_SIZE; i++) {
            System.out.printf("%d.", i);
            hashTable[i].traverse();
        }
        System.out.println();
    }

    /**
     * Prints the number of empty indexes and collisions in the hash table.
     * */
    public void printFreeAndCollisions() {
        int free = 0;
        int collisions = 0;

        for (int i = 0; i < HASH_SIZE; i++) {
            current = hashTable[i].first;
            if (current == null) {
                free++;
            }
            else {
                if (current.nextNode != null) {
                    collisions++;
                }
            }
        }
        System.out.printf("Hash Table has %d empty spaces and %d collisions.", free, collisions);
    }

}
