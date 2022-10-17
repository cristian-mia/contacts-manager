import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ContactsApplication {
    static ContactsManager myContacts;

    public static void main(String[] args) throws IOException {
        myContacts = new ContactsManager();
        Scanner sc = new Scanner(System.in);

        boolean doMore = true;
        while (doMore){
            System.out.println("\nSelect an option:\n" +
                    "1. View contacts.\n" +
                    "2. Add a new contact.\n" +
                    "3. Search a contact by name.\n" +
                    "4. Delete an existing contact.\n" +
                    "5. Exit.");
            System.out.print("Option: ");
            int option = sc.nextInt();

            switch (option) {
                case 1 -> myContacts.viewContacts();
                case 2 -> myContacts.addContact();
                case 4 -> myContacts.deleteContacts();
                case 5 -> doMore = false;
            }
        }
        myContacts.writeData();
    }



}
