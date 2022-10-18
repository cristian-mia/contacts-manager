import java.io.IOException;
import java.util.Scanner;

public class ContactsApplication {
    public static void main(String[] args) throws IOException {
        ContactsManager.init();
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
                case 1: ContactsManager.viewContacts(); break;
                case 2: ContactsManager.addContact(); break;
                case 3: ContactsManager.findContacts(); break;
                case 4: ContactsManager.deleteContacts(); break;
                case 5: doMore = false; break;
            }
        }
        ContactsManager.writeData();
    }
}
