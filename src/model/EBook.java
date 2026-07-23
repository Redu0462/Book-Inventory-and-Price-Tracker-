package model;


public class EBook extends Book {

    private String fileFormat;
    private String downloadUrl;

    public EBook(String title, String author, String isbn, int year,
                 double price, BookCategory category,
                 String fileFormat, String downloadUrl) {
        super(title, author, isbn, year, price, category);
        this.fileFormat  = fileFormat;
        this.downloadUrl = downloadUrl;
    }

    public String getFileFormat()            { return fileFormat; }
    public String getDownloadUrl()           { return downloadUrl; }
    public void   setFileFormat(String fmt)  { this.fileFormat  = fmt; }
    public void   setDownloadUrl(String url) { this.downloadUrl = url; }

    @Override
    public String getType() { return "E-Book"; }


    @Override
    public void display() {
        super.display();
        System.out.println("Format  : " + fileFormat);
        System.out.println("Download: " + downloadUrl);
        System.out.println("-------------------------------");
    }

    @Override
    public String getSummary() {
        return super.getSummary() + " | Format: " + fileFormat;
    }
}