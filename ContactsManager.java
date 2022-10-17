import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ContactsManager {
    //Variables
    private Contacts contact;
    private Path dataFile;
    Scanner sc = new Scanner(System.in);
    private List<Contacts> contactsList;
    private List<String> strings;


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

        contactsList = new ArrayList<>();

        for(String string : Files.readAllLines(dataFile)){
           String[] arrOfStr = string.split(Pattern.quote(" | "));
           Contacts newContact = new Contacts(arrOfStr[0], arrOfStr[1]);
           contactsList.add(newContact);
        }
        System.out.println(contactsList);
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
            List.of(contact.getName() + " | " + contact.getNumber()), // list with one item
            StandardOpenOption.APPEND
        );
    }

    //View contact info
    public void viewContacts() throws IOException{
        System.out.println("Name | Phone Number\n -----------------");
        for(String string : Files.readAllLines(dataFile)){
            System.out.println(string);
        }
    }

    public void deleteContacts(){
        System.out.print("Name: ");
        String findContact = sc.nextLine();
        for(Contacts contact : contactsList){
            if(contact.getName().toLowerCase().equals(findContact.toLowerCase())){
//                System.out.println(contactsList.indexOf(contact));
                int index = contactsList.indexOf(contact);
                contactsList.remove(index);
                break;
            }

        }

    }
}
