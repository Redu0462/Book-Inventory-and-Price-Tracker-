package management;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PriceTracker {

    static class PriceRecord {
        private double price;
        private String timestamp;

        public PriceRecord(double price) {
            this.price = price;
            this.timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        @Override
        public String toString() {
            return String.format("$%.2f on %s", price, timestamp);
        }
    }

    private Map<String, List<PriceRecord>> history = new HashMap<>();

    public void recordPrice(String isbn, double price) {
        if (!history.containsKey(isbn))
            history.put(isbn, new ArrayList<>());
        history.get(isbn).add(new PriceRecord(price));
    }

    public void displayHistory(String isbn) {
        List<PriceRecord> records = history.get(isbn);
        if (records == null || records.isEmpty()) {
            System.out.println("No price history for ISBN: " + isbn); return;
        }
        System.out.println("Price History for ISBN: " + isbn);
        for (int i = 0; i < records.size(); i++)
            System.out.println("  " + (i + 1) + ". " + records.get(i));
    }
}