package model;

public class PhysicalBook extends Book {

    private int    stockQuantity;
    private String shelfLocation;

    public PhysicalBook(String title, String author, String isbn, int year,
                        double price, BookCategory category,
                        int stockQuantity, String shelfLocation) {
        super(title, author, isbn, year, price, category);
        this.stockQuantity = stockQuantity;
        this.shelfLocation = shelfLocation;
    }

    public int    getStockQuantity()           { return stockQuantity; }
    public String getShelfLocation()           { return shelfLocation; }
    public void   setStockQuantity(int qty)    { this.stockQuantity = qty; }
    public void   setShelfLocation(String loc) { this.shelfLocation = loc; }

    @Override
    public String getType() { return "Physical Book"; }

    @Override
    public void display() {
        super.display();
        System.out.println("Stock   : " + stockQuantity);
        System.out.println("Shelf   : " + shelfLocation);
        System.out.println("In Stock: " + (stockQuantity > 0 ? "Yes" : "No"));
        System.out.println("-------------------------------");
    }

    @Override
    public String getSummary() {
        return super.getSummary() + " | Stock: " + stockQuantity;
    }
}