import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
                System.out.println("Can you enter the Full Name and number?");
                String contact = scanner.nextLine();
                addContact(contact, pathToContacts);
                System.out.println();
                displayContacts(pathToContacts);
                System.out.println();
            } else if (userInput.equals("3")) {//search contact
                System.out.println("Enter the name of the contact");
                String search = scanner.nextLine();
                searchContacts(search, pathToContacts);
            } else if (userInput.equals("4")) {// delete contact

            } else if (userInput.equals("5")) {//exit
                repeat = false;
            }
        } while (repeat);

    }
/////////////// OUTSIDE MAIN
    public static void DisplayMenu(){
        System.out.println("1. View contacts." +
                "\n2. Add a new contact. " +
                "\n3. Search a contact by name. " +
                "\n4. Delete an existing contact." +
                "\n5. Exit. " +
                "\nEnter an option (1, 2, 3, 4 or 5):"); // this displays the menu..in order for anything to run you have to call it at the top in the main method
    }
    public static void displayContacts (Path pathToContacts) throws IOException {
        List<String> contactList = Files.readAllLines(pathToContacts); //reading each line and storing each line into a list
        System.out.println("Contact List:");
        for (int i = 0; i < contactList.size(); i += 1) {
            System.out.println((i + 1) + ": " + contactList.get(i)); // loops through the list in the text file
        }
    }
    public static void addContact(String contact, Path pathToContacts) throws IOException {
        Files.write(pathToContacts, Arrays.asList(contact), StandardOpenOption.APPEND);
    }
    public static void searchContacts(String search, Path pathToContacts) throws IOException {
        boolean foundEntry = false;
        List<String> contactList = Files.readAllLines(pathToContacts); //reading each line and storing each line into a list
        for (int i = 0; i < contactList.size(); i += 1) {
            if (contactList.get(i).contains(search)) {
                System.out.println(contactList.get(i));
                foundEntry = true;
                break;
            }
        }
        if (!foundEntry){
            System.out.println("The name you searched is not in our contact list!");
        }
    }
}
