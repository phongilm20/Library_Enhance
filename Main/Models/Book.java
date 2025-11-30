package Library_Enhance.Main.Models;

public class Book extends Item {
    private String edition;

    public Book(String title, String author, String day, int quanity, String type, String summary, String edition){
        super(title,author,day,quanity,type,summary);
        this.edition = edition;
    }

    public String toString(){
        super.toString();
        return " " + this.edition;
    }
}
