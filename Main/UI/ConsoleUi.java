package Library_Enhance.Main.UI;

import java.util.*;

import javax.swing.tree.ExpandVetoException;

import Library_Enhance.Main.Models.Item;
import Library_Enhance.Main.Models.Student;
import Library_Enhance.Main.Services.BorrowService;
import Library_Enhance.Main.Services.LibraryServices;
import Library_Enhance.Main.Services.StudentServices;

public class ConsoleUi  {
    private LibraryServices libraryServices = new LibraryServices();
    private StudentServices studentServices = new StudentServices();
    private BorrowService borrowService = new BorrowService(studentServices);
    private Scanner scanner = new Scanner(System.in);

    public ConsoleUi() {
    }

    public void start() {
        boolean isExit = false;
        printWelcome();
        while (isExit == false) {
            printMainMenu();
            int choice = getMenuChoice();
            switch (choice) {
                case 1:
                    clearScreen();
                    handleSearch(scanner);
                    break;
                case 2:
                    clearScreen();
                    handleDeleteItem(scanner);
                    clearScreen();
                    break;
                case 3:
                    clearScreen();
                    handleAddItem();
                    clearScreen();
                    break;
                case 4:
                    clearScreen();
                    handleStudentMenu();
                    clearScreen();
                    break;
                case 5:
                    clearScreen();
                    System.out.println("Thank you for using");
                    isExit = true;
                    break;
                default:
                    clearScreen();
                    System.out.println("Invalid choice");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("");
        System.out.println("              MAIN MENU            ");
        System.out.println("1  Search Items                    ");
        System.out.println("2  Delete Item                     ");
        System.out.println("3  Add Item                        ");
        System.out.println("4  Student Services                ");
        System.out.println("5  Exit                            ");
        System.out.print("->Choose option (1-5): ");
    }

    private void handleAddItem() {
        try{
            System.out.println("Title: ");
            String title = handleInputString(scanner);

            System.out.println("Author: ");
            String author = scanner.nextLine();

            System.out.println("Publication Date: ");
            String publicationDate = scanner.nextLine();

            System.out.println("Type:");
            String type = scanner.nextLine();

            System.out.println("Summary: ");
            String summary = scanner.nextLine();

            System.out.println("Quanity: ");
            int quantity = handleInputInt(scanner);

            libraryServices.addItems(title, author, publicationDate, type, summary, quantity);
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
        
    }

    private int handleInputInt(Scanner scanner) throws Exception {
        int time = 0;
        do {
            try {
                if(time==3){break;}
                int input = scanner.nextInt();
                if (input > 0) {
                    return input;
                }
            }
            catch (Exception exception) {
                System.out.println("Quanity is an positive number!");
                System.out.println("PLease type again");
                time++;
            }
            finally{
                scanner.nextLine();

            }
        } while ( time < 4);
        throw new Exception("Error");
    }

    private void handleSearch(Scanner scanner) {
        if (this.libraryServices.isEmpty()) {
            System.out.println("Error");
        } else {
            System.out.println("Items terms: ");
            try {
                String inputSearchTerm = handleInputString(scanner);
                ArrayList<Item> itemsFound = this.libraryServices.searchItems(inputSearchTerm);
    
                if (itemsFound.isEmpty()) {
                    System.out.println("There is no any items match your search!");
                } else {
                    System.out.println("Found " + itemsFound.size() + " items!");
                    int number = 0;
                    for(Item item : itemsFound) {
                        System.out.println("-----------------");
                        System.out.println(number + 1 + ". " + item.toString());
                    }
                    System.out.println("-----------------");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printWelcome() {
        System.out.println("Welcome to my library!");
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
    }

    private String handleInputString(Scanner scanner) throws Exception {
        int i = 0 ; 
        String input = null;
        while(i<4){
            input = scanner.nextLine();

            
            if(input.equals("exit")){
                throw new Exception();
            }

            else if (!(input.isEmpty())) {
                return input;
            }

            else { 
                System.out.println("It cannot be null!");
                System.out.println("Please type again (exit): ");
                i++;
            }
            
        }
        throw new Exception("Error");

    }

    private void handleDeleteItem(Scanner scanner) {
        if (this.libraryServices.isEmpty()) {
            System.out.println("Error");
        } else {
            try {
                handleSearch(scanner);
                System.out.println("Enter ItemID to delete");
                String itemId = handleInputString(scanner);
                System.out.println("Are you sure to remove this item?");
                this.libraryServices.getItem(itemId);
                System.out.println("1. Yes");
                System.out.println("2. No");
                String answer = scanner.next();
                if(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("1")){
                    scanner.nextLine();
                    this.libraryServices.deleteItemsByItemsId(itemId);
                    System.out.println("Removed");
                    return;
                }
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // ===== STUDENT MENU =====
    private void handleStudentMenu() {
        try{
            System.out.println("\n STUDENT SERVICES\n");
            System.out.println("1. Borrow Item");
            System.out.println("2. Return Item");
            System.out.print("->Choose (1-2): ");

            int choice = getMenuChoice();
            switch (choice) {
                case 1:
                    handleBorrow();
                    break;
                case 2:
                        handleReturn();
                break;
                default:
                    printError("Invalid choice!");
            }
        }
        catch(Exception e){
        }
    }

    private int getMenuChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    private void handleBorrow() throws Exception {
        if(!this.libraryServices.isEmpty()){
            handleSearch(scanner);
            System.out.println("Item ID: ");
            String itemId = handleInputString(scanner);
            Item item = this.libraryServices.searchItemsById(itemId);
            
            
            System.out.println("UID: ");
                    String uid = handleInputString(scanner);
                    if(!this.studentServices.isUidExist(uid)){
                        System.out.println("Name: ");
                        String name = handleInputString(scanner);
                        System.out.println("Mail: ");                String mail = scanner.nextLine();
                System.out.println("Phone number: ");
                String phone = scanner.nextLine();
                Student student = new Student(name, uid, mail, phone);
                this.studentServices.registerStudent(student);
            }

            System.out.println("Today: ");
            String today = handleInputString(scanner);
            String returnId = this.borrowService.borrowItem(uid, item, today);
            System.out.println("Your return ID: " + returnId);
        }
             
    }

    private void handleReturn() {
    }

    private void printError(String message) {
        System.out.println("Error: " + message);
    }
}