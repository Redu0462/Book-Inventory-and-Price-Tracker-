package exception;

// User-defined Exception (Chapter 06)
public class DuplicateISBNException extends Exception {
    public DuplicateISBNException(String isbn) {
        super("Book with ISBN \"" + isbn + "\" already exists.");
    }
}