package Library_Enhance.Main.Models;

import java.util.Random;

public class Item {
    protected String title;
    protected String author;
    protected String publicationDate;
    protected String type;
    protected String summary;
    protected int quantity;
    private String itemId;


    public Item(String title, String author, String publicationDate, int quantity, String type, String summary) {
        this.title = title;
        this.author = author;
        this.publicationDate  = publicationDate ;
        this.quantity = quantity;
        this.type = type;
        this.summary = summary;
        this.itemId = makeItemId(this.title);
    }
    // Checking if the book have quanity
    public boolean isAvailable() {
        if (quantity > 0) {
            return true;
        }
        return false;
    }

    // This for borrowing
    public void decreaseQuanity() {
        this.quantity--;
    }

    // this for returning
    public void increaseQuanity() {
        this.quantity++;
    }

    // this for the item matching with the search
    public boolean matchingSearch(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty())
            return true;
        String lowerSearch = searchTerm.toLowerCase();
        return title.toLowerCase().contains(lowerSearch) ||
                author.toLowerCase().contains(lowerSearch) ||
                publicationDate.toLowerCase().contains(lowerSearch) ||
                type.toLowerCase().contains(lowerSearch) ||
                summary.toLowerCase().contains(lowerSearch);
    }

    // Get item quanity
    public int getQuanity(){
        return this.quantity;
    }
    
    public String getTitle(){
        return this.title;
    }
    public String getItemId(){
        return this.itemId;
    }

    // Additional getters for other fields so services can compare items
    public String getAuthor(){
        return this.author;
    }

    public String getPublicationDate(){
        return this.publicationDate;
    }

    public String getType(){
        return this.type;
    }

    // Safer quantity access and mutation
    public int getQuantity(){
        return this.quantity;
    }

    public void addQuantity(int q){
        if (q > 0) this.quantity += q;
    }

    // This for print out the detail
    @Override
    public String toString() {
        String output = this.title + "\n" + this.author + "\n" + this.publicationDate + "\n" + this.quantity;
        return output;
    }
    private String makeItemId(String title){
        Random rand = new Random();      
        int number = rand.nextInt(100,1000);
        return title.charAt(0)+number+"";
    }
}