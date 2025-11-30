package Library_Enhance.Main.Services;

import java.util.ArrayList;

import Library_Enhance.Main.Models.Item;

// This handle every logic of the library
public class LibraryServices {
    // The library List: all of items
    ArrayList <Item> itemList = new ArrayList<>();
    
    // Search Items
    public ArrayList<Item> searchItems(String searchTerm){
        ArrayList <Item> itemsFound = new ArrayList<>();
        for (Item item: itemList){
            if(item.matchingSearch(searchTerm)){
                itemsFound.add(item);
            }
        }
        return itemsFound;
    }
    public Item searchItemsById(String itemId){
        for (Item item: itemList){
            if(item.getItemId().toLowerCase().equals(itemId)){
                return item;
            }
        }
        return null;
    }
    

    

    // Add Items
    public void addItems(String title,String author,String publicationDate,String type,String summary, int quantity){
        // If an identical item already exists, increase its quantity instead of adding a new entry
        Item existing = isItemExisted(title, author, publicationDate, type);
        if (existing != null){
            existing.addQuantity(quantity);
        } 
        else {
            // Note: Item constructor expects (title, author, publicationDate, quantity, type, summary)
            Item item = new Item(title, author, publicationDate, quantity, type, summary);
            itemList.add(item);
        }
    }
    // Return an existing item that matches the provided metadata, or null if not found
    public Item isItemExisted(String title, String author, String publicationDate, String type){
        for (Item item: itemList){
            if (item.getTitle() != null && item.getAuthor() != null &&
                item.getPublicationDate() != null && item.getType() != null){
                if (item.getTitle().equalsIgnoreCase(title) &&
                    item.getAuthor().equalsIgnoreCase(author) &&
                    item.getPublicationDate().equalsIgnoreCase(publicationDate) &&
                    item.getType().equalsIgnoreCase(type)){
                        return item;
                }
            }
        }
        return null;
    }

    // Delete Items
    public void deleteItemsByItemsId(String itemId){
        for (Item item: itemList){
            if( item.getItemId().equals(itemId)){
                itemList.remove(item);
                break;
            }
        }
    }

    // Get Item 
    public void getItem(String itemId){
        for (Item item: itemList){
            if(item.getItemId().equals(itemId)){
                System.out.println(item.toString());
            }
        }

    }

    
    public boolean isEmpty(){
        if (this.itemList.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    // Return Size of the items
    public int size(){
        return itemList.size();
    }

}
