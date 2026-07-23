package Main;

import File.FileHandler;
import model.*;
import exception.*;
import management.*;
import java.util.*;


public class Main {

    static Inventory<Book> inventory = new Inventory<>();
    static PriceTracker    tracker   = new PriceTracker();
    static Scanner         sc        = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;

        while (choice != 9) {
            System.out.println("\n=== BOOKSTORE MENU ===");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book Price");
            System.out.println("5. Delete Book");
            System.out.println("6. Sort Books by Category");
            System.out.println("7. Save to File");
            System.out.println("8. Load from File");
            System.out.println("9. Exit");
            System.out.print("Choice: ");
            choice = readInt();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> inventory.displayAll();
                case 3 -> searchBook();
                case 4 -> updatePrice();
                case 5 -> deleteBook();
                case 6 -> BookSorter.sortByCategory(inventory);
                case 7 -> FileHandler.saveToFile(inventory, "inventory.csv");
                case 8 -> FileHandler.loadFromFile(inventory, "inventory.csv");
                case 9 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice. Enter 1-9.");
            }
        }
    }

    // ── Input Validation Helpers ──────────────────────────────────────────────

    static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid! Enter a whole number: ");
            }
        }
    }

    static double readDouble() {
        while (true) {
            try {
                double val = Double.parseDouble(sc.nextLine().trim());
                if (val < 0) { System.out.print("Must be positive: "); continue; }
                return val;
            } catch (NumberFormatException e) {
                System.out.print("Invalid! Enter a number: ");
            }
        }
    }

    static int readPositiveInt() {
        while (true) {
            int val = readInt();
            if (val >= 0) return val;
            System.out.print("Must be 0 or more: ");
        }
    }

    static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String val = sc.nextLine().trim();
            if (!val.isEmpty()) return val;
            System.out.println("Cannot be empty. Try again.");
        }
    }

    static BookCategory readCategory() {
        while (true) {
            System.out.println("Category (FICTION/NON_FICTION/SCIENCE/HISTORY/TECHNOLOGY/BIOGRAPHY/CHILDREN/OTHER):");
            try {
                return BookCategory.valueOf(sc.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category. Try again.");
            }
        }
    }

    // ── Menu Methods ──────────────────────────────────────────────────────────

    static void addBook() {
        System.out.print("Type (1=Physical, 2=EBook): ");
        int type = readInt();
        while (type != 1 && type != 2) {
            System.out.print("Enter 1 or 2: ");
            type = readInt();
        }

        String title  = readNonEmpty("Title: ");
        String author = readNonEmpty("Author: ");
        String isbn   = readNonEmpty("ISBN: ");

        System.out.print("Year: ");
        int year = readInt();
        while (year < 1000 || year > 2026) {
            System.out.print("Enter a valid year (1000-2026): ");
            year = readInt();
        }

        System.out.print("Price: ");
        double price = readDouble();

        BookCategory cat = readCategory();

        try {
            if (type == 1) {
                System.out.print("Stock: ");
                int stock = readPositiveInt();
                String shelf = readNonEmpty("Shelf: ");
                inventory.addBook(new PhysicalBook(title, author, isbn, year, price, cat, stock, shelf));
            } else {
                String format = readNonEmpty("Format (PDF/EPUB): ");
                String url    = readNonEmpty("Download URL: ");
                inventory.addBook(new EBook(title, author, isbn, year, price, cat, format, url));
            }
            tracker.recordPrice(isbn, price);
            System.out.println("Book added!");
        } catch (DuplicateISBNException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void searchBook() {
        System.out.print("Search by (1=ISBN, 2=Title, 3=Author): ");
        int opt = readInt();
        while (opt < 1 || opt > 3) {
            System.out.print("Enter 1, 2 or 3: ");
            opt = readInt();
        }

        String keyword = readNonEmpty("Keyword: ");

        try {
            if (opt == 1) {
                inventory.findByIsbn(keyword).display();
            } else if (opt == 2) {
                List<Book> results = inventory.searchByTitle(keyword);
                if (results.isEmpty()) System.out.println("No books found.");
                else results.forEach(b -> System.out.println(b.getSummary()));
            } else {
                List<Book> results = inventory.searchByAuthor(keyword);
                if (results.isEmpty()) System.out.println("No books found.");
                else results.forEach(b -> System.out.println(b.getSummary()));
            }
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void updatePrice() {
        String isbn = readNonEmpty("ISBN: ");
        System.out.print("New Price: ");
        double newPrice = readDouble();
        try {
            inventory.updatePrice(isbn, newPrice);
            tracker.recordPrice(isbn, newPrice);
            tracker.displayHistory(isbn);
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void deleteBook() {
        String isbn = readNonEmpty("ISBN to delete: ");
        try {
            inventory.removeBook(isbn);
            System.out.println("Book deleted.");
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
