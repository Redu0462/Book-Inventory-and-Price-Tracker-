package management;

import model.*;
import exception.*;
import java.util.*;



public class BookSorter {

    // Sort and display books grouped by category
    public static void sortByCategory(Inventory<Book> inventory) {
        if (inventory.getAllBooks().isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        // Create a map: category -> list of books
        Map<BookCategory, List<Book>> grouped = new LinkedHashMap<>();

        // Initialize all categories in order
        for (BookCategory cat : BookCategory.values())
            grouped.put(cat, new ArrayList<>());

        // Put each book into its category group
        for (Book b : inventory.getAllBooks())
            grouped.get(b.getCategory()).add(b);

        // Display each category group
        System.out.println("\n===== BOOKS SORTED BY CATEGORY =====");
        for (Map.Entry<BookCategory, List<Book>> entry : grouped.entrySet()) {
            List<Book> books = entry.getValue();
            if (books.isEmpty()) continue; // skip empty categories

            System.out.println("\n[ " + entry.getKey() + " ] - " + books.size() + " book(s)");
            System.out.println("-------------------------------");
            for (Book b : books)
                System.out.println("  -> " + b.getSummary());
        }
        System.out.println("=====================================");
    }
}