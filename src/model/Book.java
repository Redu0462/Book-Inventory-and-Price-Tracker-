package model;

public abstract class Book implements Displayable {

    private String title;
    private String author;
    private String isbn;
    private int year;
    private double price;
    private BookCategory category;

    public Book(String title, String author, String isbn, int year, double price, BookCategory category) {
        this.title    = title;
        this.author   = author;
        this.isbn     = isbn;
        this.year     = year;
        this.price    = price;
        this.category = category;
    }

    public String       getTitle()    { return title; }
    public String       getAuthor()   { return author; }
    public String       getIsbn()     { return isbn; }
    public int          getYear()     { return year; }
    public double       getPrice()    { return price; }
    public BookCategory getCategory() { return category; }

    public void setTitle(String title)        { this.title    = title; }
    public void setAuthor(String author)      { this.author   = author; }
    public void setIsbn(String isbn)          { this.isbn     = isbn; }
    public void setYear(int year)             { this.year     = year; }
    public void setPrice(double price)        { this.price    = price; }
    public void setCategory(BookCategory cat) { this.category = cat; }

    public abstract String getType();

    @Override
    public void display() {
        System.out.println("-------------------------------");
        System.out.println("Type    : " + getType());
        System.out.println("Title   : " + title);
        System.out.println("Author  : " + author);
        System.out.println("ISBN    : " + isbn);
        System.out.println("Year    : " + year);
        System.out.printf ("Price   : $%.2f%n", price);
        System.out.println("Category: " + category);
    }

    @Override
    public String getSummary() {
        return String.format("[%s] \"%s\" by %s - $%.2f", getType(), title, author, price);
    }
}