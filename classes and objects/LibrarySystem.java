import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {
    // COLORS :DDD learned this from gemini :D
    private static final String RESET = "\u001B[0m"; //text color back to defaulttt
    private static final String BOLD = "\u001B[1m"; //bold text!
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String PURPLE = "\u001B[35m";

    private static ArrayList<Book> library = new ArrayList<>(); //main list to store books we add
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) { // main loop without using while(true) :DD
        int choice = 0;

        while (choice != 5) {
            Menu();
            choice = validInput(CYAN + "Enter option: " + RESET, 1, 5); //option from user nd make sure its between 1-5

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    displayBooks();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    filterByGenre();
                    break;
                case 5:
                    System.out.println(YELLOW + "\nEdi wag. Goodbye >:(" + RESET);
                    break;
                default:
                    System.out.println(RED + "Invalid option!\n" + RESET);
            }
        }
        scanner.close(); // closes when we exit
    }

    private static void Menu() { // menu printer with cool colors I learned from gemini :D
        System.out.println(BOLD + PURPLE + "========================================" + RESET);
        System.out.println(BOLD + PURPLE + "         LIBRARY MANAGEMENT SYSTEM      " + RESET);
        System.out.println(BOLD + PURPLE + "========================================" + RESET);
        System.out.println("1 - " + CYAN + "Add Book" + RESET);
        System.out.println("2 - " + CYAN + "Display All Books" + RESET);
        System.out.println("3 - " + CYAN + "Search Book by Title" + RESET);
        System.out.println("4 - " + CYAN + "Filter Books by Genre" + RESET);
        System.out.println("5 - " + RED + "Exit" + RESET);
        System.out.println(PURPLE + "----------------------------------------" + RESET);
    }

    private static void addBook() { // function  to ask user for book details and add to library
        System.out.println(BOLD + "\n--- Add New Book ---" + RESET);
        System.out.print("Enter title: ");
        String title = scanner.nextLine().trim(); // .trim() removes extra spaces from the input

        System.out.print("Enter author: ");
        String author = scanner.nextLine().trim();

        System.out.print("Enter genre (e.g., Fiction, Sci-Fi, History): "); //i added genres just cause its cool :D
        String genre = scanner.nextLine().trim();

        int year = validInput("Enter year (max 2026): ", 0, 2026); // validation for valid year

        library.add(new Book(title, author, genre, year)); //new book object then put it into my arraylist
        System.out.println(GREEN + "Book added successfully!\n" + RESET); 
    }

    private static void displayBooks() { //display function to show all books 
        if (library.isEmpty()) {
            System.out.println(RED + "\nNo books in the library yet.\n" + RESET);
            return;
        }

        System.out.println(BOLD + "\n========================= BOOK LIST ==========================" + RESET);
        System.out.printf(BOLD + "%-22s %-18s %-15s %-5s%n" + RESET, "Title", "Author", "Genre", "Year");
        System.out.println("---------------------------------------------------------------");
        for (Book b : library) {
            System.out.printf("%-22s %-18s %-15s %-5d%n", 
                b.getTitle(), b.getAuthor(), b.getGenre(), b.getYear());
        }
        System.out.println();
    }

    private static void searchBook() { //function to find book by title
        if (library.isEmpty()) {
            System.out.println(RED + "\nLibrary is empty. No books to search.\n" + RESET);
            return;
        }

        System.out.print("\nEnter a book to search: ");
        String searchT = scanner.nextLine().trim();

        Book found = findTitle(searchT); //my helper function to find the book object

        if (found == null) {
            System.out.println(RED + "Book not found!\n" + RESET);
            return;
        }

        System.out.println(GREEN + "\nBook found!" + RESET);
        found.displayDetails();
        System.out.println();
    }

    private static void filterByGenre() { // filter to search books by genre
        if (library.isEmpty()) {
            System.out.println(RED + "\nLibrary is empty. No genres to filter.\n" + RESET);
            return;
        }

        System.out.print("\nEnter genre to filter by: ");
        String searchG = scanner.nextLine().trim();

        boolean matchF = showGenreBooks(searchG);

        if (!matchF) {
            System.out.println(RED + "No books found under genre: " + searchG + "\n" + RESET);
        }
    }

    private static boolean showGenreBooks(String searchG) { //helper loop that loops thru the array list then prints matching genres
        boolean matchF = false;
        
        for (Book b : library) {
            if (b.getGenre().equalsIgnoreCase(searchG)) { //makes it search without worrying about capital or lowercase letters
                if (!matchF) {
                    System.out.println(BOLD + "\n--- Books in Genre: " + searchG + " ---" + RESET);
                    System.out.printf(BOLD + "%-22s %-18s %-5s%n" + RESET, "Title", "Author", "Year");
                    System.out.println("---------------------------------------------------");
                }
                System.out.printf("%-22s %-18s %-5d%n", b.getTitle(), b.getAuthor(), b.getYear());
                matchF = true;
            }
        }
        if (matchF) {
            System.out.println();
        }
        return matchF;
    }

    private static Book findTitle(String title) { //another function to find books by title
        for (Book b : library) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null; //returns null if nothing matched
    }

    private static int validInput(String label, int min, int max) { //input checker to prevent crash when user types in invalid input like letters instead of numbers
        boolean valid = false;
        int number = -1;

        while (!valid) {
            System.out.print(label);
            
            if (!scanner.hasNextInt()) { //checks if input is actually an integer
                System.out.println(RED + "Invalid input! Please enter a valid number." + RESET);
                scanner.nextLine();
                continue;
            }

            number = scanner.nextInt();
            scanner.nextLine(); 

            if (number < min || number > max) { //mkes sure number is within allowed min and max range
                System.out.println(RED + "Invalid input! Number must be between " + min + " and " + max + "." + RESET);
                continue;
            }

            valid = true;
        }

        return number;
    }
}

class Book { //book class to store book details
    private String title;
    private String author;
    private String genre;
    private int year;

    public Book(String title, String author, String genre, int year) { //constructor to setup the book object with the details we get from user input
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
    }
//getter methods to get the details of the book object
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }
//prints details line-by-line
    public void displayDetails() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Year: " + year);
    }
}   