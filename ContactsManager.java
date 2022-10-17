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
    private Path dataFile;

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
        Scanner sc = new Scanner(System.in);
        // Add a Contact
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Number: ");
        String number = sc.nextLine();
        Files.write(
            Paths.get(String.valueOf(dataFile)),
            List.of(name + " " + number), // list with one item
            StandardOpenOption.APPEND
        );
    }

}
