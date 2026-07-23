package management;

import model.*;
import exception.*;
import java.util.*;

public class Inventory<T extends Book> {

    private ArrayList<T> books = new ArrayList<>();

    public void addBook(T book) throws DuplicateISBNException {
        for (T b : books)
            if (b.getIsbn().equalsIgnoreCase(book.getIsbn()))
                throw new DuplicateISBNException(book.getIsbn());
        books.add(book);
        System.out.println("Added: " + book.getSummary());
    }

    public void removeBook(String isbn) throws BookNotFoundException {
        T found = findByIsbn(isbn);
        books.remove(found);
        System.out.println("Removed: " + found.getSummary());
    }

    public T findByIsbn(String isbn) throws BookNotFoundException {
        for (T b : books)
            if (b.getIsbn().equalsIgnoreCase(isbn))
                return b;
        throw new BookNotFoundException(isbn);
    }

    public List<T> searchByTitle(String keyword) {
        List<T> result = new ArrayList<>();
        for (T b : books)
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                result.add(b);
        return result;
    }

    public List<T> searchByAuthor(String keyword) {
        List<T> result = new ArrayList<>();
        for (T b : books)
            if (b.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
                result.add(b);
        return result;
    }

    public void updatePrice(String isbn, double newPrice) throws BookNotFoundException {
        T book = findByIsbn(isbn);
        double old = book.getPrice();
        book.setPrice(newPrice);
        System.out.printf("Price updated \"%s\": $%.2f -> $%.2f%n", book.getTitle(), old, newPrice);
    }

    public void displayAll() {
        if (books.isEmpty()) { System.out.println("Inventory is empty."); return; }
        System.out.println("===== INVENTORY (" + books.size() + " books) =====");
        for (T b : books) b.display();
    }

    public ArrayList<T> getAllBooks() { return books; }
    public int size()                 { return books.size(); }
}