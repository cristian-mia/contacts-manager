import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactsManager {
    //Variables
    private Contacts contact;
    private Path dataFile;
    Scanner sc = new Scanner(System.in);


    // Constructor
    public ContactsManager() throws IOException {
        // Get Current Directory
        String pwd = System.getProperty("user.dir");

        // Name Directories & Files
        String directory = pwd + "/data";
        String filename = "contacts.txt";

        // Get file paths
        Path dataDirectory = Paths.get(directory);
        this.dataFile = Paths.get(directory, filename);

        // Create Files/Directories if they do not exist
        if (Files.notExists(dataDirectory)) {
            Files.createDirectories(dataDirectory);
        }
        if (! Files.exists(dataFile)) {
            Files.createFile(dataFile);
        }
    }

    // Methods
    public void addContact() throws IOException {
        // Get contact info
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Number: ");
        String number = sc.nextLine();
        // Create contact object
        contact = new Contacts(name, number);
        // Write contact to database
        Files.write(
            Paths.get(String.valueOf(dataFile)),
            List.of(contact.getName() + " " + contact.getNumber()), // list with one item
            StandardOpenOption.APPEND
        );
    }

}
