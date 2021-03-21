import java.util.ArrayList;

/**
 * COP 3530: Project 4 - Binary Search Trees
 * Dr. Xudong Liu
 * <p>
 * This class serves as a blueprint for creating a binary search tree that stores the Names and GDPs per capita of a
 * country inside of a Node. It contains method to insert a country into the tree, locate a country within the tree,
 * remove a country within the tree, and print the tree in various orders such as Preorder, Inorder, and Postorder.
 * </p>
 *
 * @author Alexander Derby
 * @version 04/09/2020
 * */

public class BinarySearchTree {

    /**
     * Nested class that defines a Node that contains the Name of a country and its GDP per capita.
     * */
    private class Node {
        String name;
        double gdpPerCapita;
        Node leftChild, rightChild;

        /**
         * Constructor: Creates a Node with a Name and GDP per capita.
         * */
        public Node(String name, double gdpPerCapita){
            this.name = name;
            this.gdpPerCapita = gdpPerCapita;
        }

        /**
         * Prints the contents of the Node.
         * */
        public void printNode(){
             System.out.printf("%-35s%,-20.2f\n", name, gdpPerCapita);
        }
    }

    private Node root;

    private ArrayList<Node> nodeAL = new ArrayList<>();
    private boolean sorted = false;
    private int traversed;

    /**
     * Default contructor: Creates an empty binary search tree.
     * */
    public BinarySearchTree(){ root = null; }

    /**
     * Takes in a String and Double that represent a Name and GDP per capita to create a Node with and inserts it into
     * the tree. If the tree is empty, the root becomes the Node created. If the tree is not empty, the Node is
     * inserted into its correct location depending on its name.
     *
     * @param name Creates a node using the given name of the country.
     * @param gdpPerCapita Creates a node using the given GDP per capita of the country.
     * */
    public void insert(String name, double gdpPerCapita){
        Node temp = new Node(name,gdpPerCapita);

        if (root == null) {
            root = temp;
            nodeAL.add(temp);
            return;
        }

        Node curr = root;

        while (true) {
            if (temp.name.compareToIgnoreCase(curr.name) < 0) {
                if (curr.leftChild != null) {
                    curr = curr.leftChild;
                } else {
                    curr.leftChild = temp;
                    break;
                }
            }
            else {
                if (curr.rightChild != null) {
                    curr = curr.rightChild;
                } else {
                    curr.rightChild = temp;
                    break;
                }
            }
        }
        nodeAL.add(temp);
    }

    /**
     * Takes in a String to traverse the tree and attempt to locate the corresponding Node in the tree. If the Node
     * is found, its contents are printed along with the number of Nodes traversed to reach it. If the Node is not
     * found, the user is shown the number of Nodes the method traversed while attempting to find the country.
     *
     * @param name Name of the country to be found
     * @return Returns the GDP per capita of the located country. Otherwise, returns -1 if country was not found.
     * */
    public double find(String name) {
        if (root == null) {
            throw new IllegalStateException();
        }
        Node curr = root;
        traversed = 0;

        while(!curr.name.equalsIgnoreCase(name)) {
            if (curr.name.compareToIgnoreCase(name) > 0) {
                curr = curr.leftChild;
                traversed++;
            }
            else {
                curr = curr.rightChild;
                traversed++;
            }
            if (curr == null) {
                System.out.printf("\n%s was not found.\n", name);
                System.out.printf("%d nodes traversed.\n", traversed);
                return -1;
            }
        }
        traversed++;
        System.out.printf("\n%s is found with GDP per capita: $%.2f\n", name, curr.gdpPerCapita);
        System.out.printf("%d nodes traversed.\n", traversed);
        return curr.gdpPerCapita;
    }

    /**
     * Takes in a String to find the location of the corresponding Node in the tree and delete it. When the Node is
     * found, the method determines if the Node is a leaf, has one child, or two children and removes it from the tree
     * accordingly.
     *
     * @param name Name of country to be found.
     * */
    public void delete(String name) {
        if (root == null) {
            throw new IllegalStateException();
        }
        Node curr = root;
        Node parent = root;
        boolean leftChild = false;

        while(!curr.name.equalsIgnoreCase(name)) {
            parent = curr;
            if (curr.name.compareToIgnoreCase(name) > 0) {
                curr = curr.leftChild;
                leftChild = true;
            }
            else {
                leftChild = false;
                curr = curr.rightChild;
            }
            if (curr == null) {
                System.out.println(name + " not found.");
                return;
            }
        }

        if (curr.rightChild == null && curr.leftChild == null) {
            if (curr == root) {
                root = null;
            }
            else if (leftChild) {
                parent.leftChild = null;
            }
            else {
                parent.rightChild = null;
            }
            System.out.println("\n" + name + " has been deleted from the tree.");
        }

        else if (curr.rightChild == null) {
            if (curr == root) {
                root = curr.leftChild;
            }
            else if (leftChild) {
                parent.leftChild = curr.leftChild;
            }
            else {
                parent.rightChild = curr.leftChild;
            }
            System.out.println("\n" + name + " has been deleted from the tree.");
        }

        else if (curr.leftChild == null) {
            if (curr == root) {
                root = curr.rightChild;
            }
            else if(leftChild) {
                parent.leftChild = curr.rightChild;
            }
            else {
                parent.rightChild = curr.rightChild;
            }
            System.out.println("\n" + name + " has been deleted from the tree.");
        }

        else {
            Node successor = getSuccessor(curr);
            if (curr == root) {
                root = successor;
            }
            else if (leftChild) {
                parent.leftChild = successor;
            }
            else {
                parent.rightChild = successor;

            }
            successor.leftChild = curr.leftChild;
            System.out.println("\n" + name + " has been deleted from the tree.");
        }
        nodeAL.remove(getIndex(name));
    }

    /**
     * Finds the successor of the Node given by the delete() method.
     *
     * @param curr Finds successor of given Node.
     * @return Returns the successor Node.
     * */
    private Node getSuccessor(Node curr) {
        Node successor = curr;
        Node parentOfSuccessor = curr;
        Node temp = curr.rightChild;

        while (temp != null) {
            parentOfSuccessor = successor;
            successor = temp;
            temp = temp.leftChild;
        }

        if (successor != curr.rightChild) {
            parentOfSuccessor.leftChild = successor.rightChild;
            successor.rightChild = curr.rightChild;
        }
        return successor;
    }

    /**
     * Prints the tree recursively using Inorder traversal. (Left-Node-Right)
     * @param curr Initially root Node until recursively called to find all Nodes.
     * */
    public void printInorder(Node curr){
        if (curr == null){
            return;
        }
        printInorder(curr.leftChild);
        curr.printNode();
        printInorder(curr.rightChild);
    }

    /**
     * Prints the tree recursively using Preorder traversal. (Node-Left-Right)
     * @param curr Initially root Node until recursively called to find all Nodes.
     * */
    public void printPreorder(Node curr){
        if (curr == null){
            return;
        }
        curr.printNode();
        printPreorder(curr.leftChild);
        printPreorder(curr.rightChild);
    }

    /**
     * Prints the tree recursively using Postorder traversal. (Left-Right-Node)
     * @param curr Initially root Node until recursively called to find all Nodes.
     * */
    public void printPostorder(Node curr){
        if (curr == null) {
            return;
        }
        printPostorder(curr.leftChild);
        printPostorder(curr.rightChild);
        curr.printNode();
    }

    /**
     * Prints the five-lowest countries in the tree bases on their GDP per capita.
     * */
    public void printBottomFive() {
        if (!sorted) {
            sort();
            sorted = true;
        }
        for (int i = 0; i < 5; i++) {
            nodeAL.get(i).printNode();
        }
    }

    /**
     * Prints the five-highest countries in the tree bases on their GDP per capita.
     * */
    public void printTopFive() {
        if (!sorted) {
            sort();
            sorted = true;
        }
        for (int i = nodeAL.size() - 1; i > nodeAL.size() - 6; i--) {
            nodeAL.get(i).printNode();
        }
    }

    /**
     * Returns the root of the tree.
     * @return Returns root Node.
     * */
    public Node getRoot() {
        if (root == null) {
            throw new NullPointerException();
        }
        return root;
    }

    /**
     * Sorts the ArrayList, using Insertion sort, by GDP per capita. (While O(N^2), number of elements was small so I
     * decided to use Insertion.)
     * */
    private void sort() {
        for (int i = 1; i < nodeAL.size(); i++) {
            Node key = nodeAL.get(i);
            int j = i - 1;

            while (j >= 0 && nodeAL.get(j).gdpPerCapita > key.gdpPerCapita) {
                nodeAL.set((j+1), nodeAL.get(j));
                j = j - 1;
            }
            nodeAL.set((j + 1), key);
        }
    }

    /**
     * Finds the index of a given String in the ArrayList.
     * @param name Name to be located within ArrayList
     * @return Returns the index of the located String. Otherwise, returns -1;
     * */
    private int getIndex(String name) {
        int i;
        for (i = 0; i < nodeAL.size(); i++) {
            if (nodeAL.get(i).name.equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }
}
