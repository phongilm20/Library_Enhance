package Library_Enhance.Main.Services;

import java.util.*;
import Library_Enhance.Main.Models.BorrowRecored;
import Library_Enhance.Main.Models.Item;

public class BorrowService {
    ArrayList<BorrowRecored> borrowList = new ArrayList<>();
    private StudentServices studentServices;
    private LibraryServices libraryServices;

    //
    public BorrowService(StudentServices studentServices) {
        this.studentServices = studentServices;

    }
    // BORROW ITEM
    public String borrowItem(String uid, Item item, String date) {
        // Check for the ID of student and the item have enough quanity
        // Logic: can have the same itemID product for many pp but with unique returnId
        // When student borrow: diffrent returnId
        if (studentServices.isUidExist(uid) && item.isAvailable()) {
            String name = this.studentServices.findStudent(uid).getName();
            String title = item.getTitle();
            String itemId = item.getItemId();
            String borrowDate = date;
            String returnId = returnId();
            BorrowRecored borrowRecored = new BorrowRecored(uid, itemId, title, name, borrowDate, returnId);
            borrowList.add(borrowRecored);

            item.decreaseQuanity();

            return returnId;
        }
        return null;
    }

    // make an ID for the returning while borrowing
    // Logic : make sure the returnId is unique in list of borrowing
    private String returnId() {
        Random rand = new Random();
        String abcdef = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int randomIndex = rand.nextInt(abcdef.length());
        int number = rand.nextInt(100, 1000);
        String output = abcdef.charAt(randomIndex) + number + "";

        for (BorrowRecored borrowRedcRecored : borrowList) {
            if (!borrowRedcRecored.getReturnId().equals(output)) {
                return output;
            } else {
                return returnId();
            }
        }
        return null;
    }

    // RETURN ITEM - if that item in the borrow list, then quanity incrase, remove
    // Logic: return by using itemId and then fill the returnDate into the
    // borrowRecored
    public boolean returnItem(String itemId, String returnDay) {
        for (BorrowRecored borrowRecored : borrowList) {
            if (borrowRecored.getItemId().equals(itemId)) {
                Item item = this.libraryServices.searchItemsById(itemId);
                item.increaseQuanity();
                borrowRecored.setReturnDate(returnDay);
                borrowList.remove(borrowRecored);
                return true;
            }
        }
        return false;
    }



    // See the history of borrowing and returning
    // Logic find all borrowing and returning of a student by uid and make sure that
    // uid is exist
    public String printingBorrowHistoryByUid(String uid) {
        if (studentServices.isUidExist(uid) == false) {
            for (BorrowRecored borrowRecored : borrowList) {
                if (borrowRecored.getUid().equals(uid)) {
                    return borrowRecored.toString();
                }
            }
        }
        return null;
    }
}