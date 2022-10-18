import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ContactsManager {
    //Variables
    private static Contacts contact;
    private static Path dataFile;
    private static Scanner sc = new Scanner(System.in);
    private static List<Contacts> contactsList;

    // Constructor
    public static void init() throws IOException {
        // Get Current Directory
        String pwd = System.getProperty("user.dir");

        // Name Directories & Files
        String directory = pwd + "/data";
        String filename = "contacts.txt";

        // Get file paths
        Path dataDirectory = Paths.get(directory);
        dataFile = Paths.get(directory, filename);

        // Create Files/Directories if they do not exist
        if (Files.notExists(dataDirectory)) {
            Files.createDirectories(dataDirectory);
        }
        if (! Files.exists(dataFile)) {
            Files.createFile(dataFile);
        }

        contactsList = new ArrayList<>();
        loadData();
    }

    // Methods
    public static void loadData() throws IOException {
        for(String string : Files.readAllLines(dataFile)){
            String[] arrOfStr = string.split(Pattern.quote(" | "));
            Contacts newContact = new Contacts(arrOfStr[0], arrOfStr[1]);
            contactsList.add(newContact);
        }
    }

    public static void writeData() throws IOException {
        List<String> strings = new ArrayList<>();
        for(Contacts contact : contactsList){
            strings.add(contact.getName() + " | " + contact.getNumber());
        }
        Files.write(dataFile, strings);
    }

    public static void addContact() throws IOException {
        // Get contact info
        System.out.print("Name: ");
        String name = sc.nextLine();
        // Check for existing contact
        boolean makeContact = checkName(name);
        if (makeContact) {
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
            loadData();
        } else {
            System.out.println("Canceled.");
        }
    }

    public static void viewContacts() throws IOException{
        System.out.printf("%-13s | %-13s |%n", "Name", "Number");
        System.out.println("------------------------------");
        for(Contacts contact : contactsList){
            System.out.printf("%-13s | %-13s |%n", contact.getName(), formatNumber(contact.getNumber()));
        }
    }

    public static void deleteContacts() throws IOException {
        System.out.print("Name to Delete: ");
        String findContact = sc.nextLine();
        for(Contacts contact : contactsList){
            if(contact.getName().toLowerCase().contains(findContact.toLowerCase())){
                contactsList.remove(contact);
                break;
            }
        }
    }

    public static void findContacts(){
        System.out.print("Name to find: ");
        String findContact = sc.nextLine();
        for(Contacts contact : contactsList) {
            if (contact.getName().toLowerCase().contains(findContact.toLowerCase())) {
                System.out.println((contact.getName() + " | " + contact.getNumber()));
            }
        }
    }

    private static String formatNumber(String number){
        if (number.length() >= 9 ){
            return "(" + number.substring(0,3) + ")" + number.substring(3,6) + "-" + number.substring(6);
        } else {
            return number.substring(0,3) + "-" + number.substring(3);
        }
    }

    private static boolean checkName(String name){
        for(Contacts contact : contactsList) {
            if (contact.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.printf("There's already a contact named %s. Do you want to continue? (y/n) ", name);
                String answer = sc.nextLine();
                return answer.toLowerCase().startsWith("y");
            }
        }
        return true;
    }
}
