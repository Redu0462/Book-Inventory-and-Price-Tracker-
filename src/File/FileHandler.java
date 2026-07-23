package File;

import management.Inventory;
import model.*;
import exception.*;
import java.io.*;


public class FileHandler {

    public static void saveToFile(Inventory<? extends Book> inventory, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("type,title,author,isbn,year,price,category,extra1,extra2");
            for (Book b : inventory.getAllBooks()) {
                if (b instanceof PhysicalBook pb)
                    writer.printf("PHYSICAL,%s,%s,%s,%d,%.2f,%s,%d,%s%n",
                            pb.getTitle(), pb.getAuthor(), pb.getIsbn(),
                            pb.getYear(), pb.getPrice(), pb.getCategory(),
                            pb.getStockQuantity(), pb.getShelfLocation());
                else if (b instanceof EBook eb)
                    writer.printf("EBOOK,%s,%s,%s,%d,%.2f,%s,%s,%s%n",
                            eb.getTitle(), eb.getAuthor(), eb.getIsbn(),
                            eb.getYear(), eb.getPrice(), eb.getCategory(),
                            eb.getFileFormat(), eb.getDownloadUrl());
            }
            System.out.println("Saved to: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    public static void loadFromFile(Inventory<Book> inventory, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean first = true;
            while ((line = reader.readLine()) != null) {
                if (first) { first = false; continue; }
                if (line.trim().isEmpty()) continue;
                String[] p = line.split(",", -1);
                if (p.length < 9) continue;
                try {
                    String       type  = p[0].trim();
                    String       title = p[1].trim();
                    String       auth  = p[2].trim();
                    String       isbn  = p[3].trim();
                    int          year  = Integer.parseInt(p[4].trim());
                    double       price = Double.parseDouble(p[5].trim());
                    BookCategory cat   = BookCategory.valueOf(p[6].trim());

                    if ("PHYSICAL".equalsIgnoreCase(type))
                        inventory.addBook(new PhysicalBook(title, auth, isbn, year, price, cat,
                                Integer.parseInt(p[7].trim()), p[8].trim()));
                    else if ("EBOOK".equalsIgnoreCase(type))
                        inventory.addBook(new EBook(title, auth, isbn, year, price, cat,
                                p[7].trim(), p[8].trim()));

                } catch (DuplicateISBNException e) {
                    System.out.println("Skipped duplicate: " + e.getMessage());
                }
            }
            System.out.println("Loaded from: " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }
}