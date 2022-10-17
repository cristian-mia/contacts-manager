import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContactsApplication {
    public void main(String[] args) throws IOException {
        Contacts newContact = new Contacts("name", "number");
        newContact.getName();
        newContact.setName("Cris");

        ContactsManager myContacts = new ContactsManager();
    }
}
