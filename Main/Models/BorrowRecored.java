package Library_Enhance.Main.Models;

// Each time borrowing item
public class BorrowRecored {
    private String uid;
    private String itemId;
    private String itemTitle;
    private String returnId;
    private String studentName;
    private String borrowDate;
    private String returnDate;

    public BorrowRecored(String uid, String itemId, String title, String name, String borrowDate, String returnId){
        this.uid=uid;
        this.itemId = itemId;
        this.itemTitle = title;
        this.studentName= name;
        this.borrowDate = borrowDate;
        this.returnDate = "";
    }
    

    @Override
    public String toString(){
        return "UID-"+this.uid+" Name"+this.studentName+"\nItem:"+this.itemTitle+"\nReturn ID: "+this.itemId+"\nDate: "+this.borrowDate+"-"+this.returnDate;
    }
    
    public void setReturnDate(String returnDate){
        this.returnDate = returnDate;
    }
    
    public String getItemId(){
        return this.itemId;
    }
    public String getUid(){
        return this.uid;
    }
    public String getReturnId(){
        return this.returnId;
    }

    
}
