import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        Contact irfa = new Contact("Irfa", "Sheikh", "5555555555");  //constructor for new contact
//        Contact roland = new Contact("Roland", "Valdez", "5557777777");
//        System.out.println(irfa.getContact());
//        System.out.println(roland.getContact());
        Path pathToContacts = Paths.get("./data/contacts.txt");
        List<String> contactList = Files.readAllLines(pathToContacts);
        System.out.println("Contact List:");
        for (int i = 0; i < contactList.size(); i += 1) {
            System.out.println((i + 1) + ": " + contactList.get(i));
        }


    }
}
