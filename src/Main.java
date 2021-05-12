import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
//        Contact irfa = new Contact("Irfa", "Sheikh", "5555555555");  //constructor for new contact
//        Contact roland = new Contact("Roland", "Valdez", "5557777777");
//        System.out.println(irfa.getContact());
//        System.out.println(roland.getContact());
        boolean repeat = true;
        do {
            Path pathToContacts = Paths.get("./data/contacts.txt");
            DisplayMenu();
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            if (userInput.equals("1")) {
                displayContacts(pathToContacts);
                System.out.println();
            } else if (userInput.equals("2")) {
                System.out.println("\nCan you enter the Full Name and number?");
                String contact = scanner.nextLine();
                addContact(contact, pathToContacts);
                System.out.println();
                displayContacts(pathToContacts);
                System.out.println();
            } else if (userInput.equals("3")) {//search contact
                System.out.println("\nEnter the name of the contact");
                String search = scanner.nextLine();
                searchContacts(search, pathToContacts);
            } else if (userInput.equals("4")) {// delete contact
                System.out.println("\nEnter the name of the contact to delete");
                String search = scanner.nextLine();
                deleteContact(search, pathToContacts);
                displayContacts(pathToContacts);

            } else if (userInput.equals("5")) {//exit
                System.out.println("\nGood Bye!");
                repeat = false;
            }
        } while (repeat);

    }
/////////////// OUTSIDE MAIN
    public static void DisplayMenu(){
        System.out.println("\nContact Manager Menu:" +
                "\n1. View contacts." +
                "\n2. Add a new contact. " +
                "\n3. Search a contact by name. " +
                "\n4. Delete an existing contact." +
                "\n5. Exit. " +
                "\nEnter an option (1, 2, 3, 4 or 5):"); // this displays the menu..in order for anything to run you have to call it at the top in the main method
    }
    public static void displayContacts (Path pathToContacts) throws IOException {
        List<String> contactList = Files.readAllLines(pathToContacts); //reading each line and storing each line into a list
        System.out.println("Contact List:");
        System.out.println("Name | Phone Number");
        System.out.println("---------------");

        for (int i = 0; i < contactList.size(); i += 1) {
            int index = contactList.get(i).lastIndexOf(" "); // go to contact list and get that specific line, look for the last index of the last occurrence of a space
            String phone = contactList.get(i).substring(index + 1);
            if (phone.length() == 7){
                phone = phone.substring(0,3) + "-" + phone.substring(3);
            }else if (phone.length() == 10){
                phone = phone.substring(0,3) + "-" + phone.substring(3, 6) + "-" + phone.substring(6);
            }
            String fullName = contactList.get(i).substring(0, index);
            System.out.println(fullName + " | " + phone);}
    }
    public static void addContact(String contact, Path pathToContacts) throws IOException {
        Files.write(pathToContacts, Arrays.asList(contact), StandardOpenOption.APPEND);
    }
    public static void searchContacts(String search, Path pathToContacts) throws IOException {
        boolean foundEntry = false;
        List<String> contactList = Files.readAllLines(pathToContacts); //reading each line and storing each line into a list
        for (int i = 0; i < contactList.size(); i += 1) {
            if (contactList.get(i).contains(search)) {
                System.out.println();
                System.out.println( "This is the contact that matches your search parameter.");
                System.out.println(contactList.get(i));
                foundEntry = true;
                break;
            }
        }
        if (!foundEntry){
            System.out.println("The name you searched is not in our contact list!");
        }
    }
    public static void deleteContact(String search, Path pathToContacts) throws IOException {
        List<String> contacts = Files.readAllLines(pathToContacts);
        List<String> newContacts = new ArrayList<>();

        for (String contact : contacts) {
            if (contact.contains(search)) {
                System.out.println("Is this the contact you wish to delete?");
                System.out.println(contact);
                Scanner scanner = new Scanner(System.in);
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("y")){
                    continue;
                }
                else{
                    System.out.println();
                    displayContacts(pathToContacts);
                }
            }
            newContacts.add(contact);
        }
        Files.write(pathToContacts, newContacts);
    }

}
