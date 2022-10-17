import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ContactsApplication {
    static ContactsManager myContacts;

    public static void main(String[] args) throws IOException {
        myContacts = new ContactsManager();
        myContacts.addContact();
        myContacts.viewContacts();
        myContacts.deleteContacts();
    }


}
