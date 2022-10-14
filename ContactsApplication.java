public class ContactsApplication {
    public static void main(String[] args) {
        Contacts newContact = new Contacts("name", "number");
        newContact.getName();
        newContact.setName("Cris");
    }
}
