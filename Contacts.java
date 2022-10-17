public class Contacts {
    // Variables
    private String name;
    private String number;
    // Constructor
    public Contacts(String name, String number){
        this.name = name;
        this.number = number;
    }
    // Methods
    public String getName(){
        return this.name;
    }
    public String getNumber(){
        return this.number;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setNumber(String number){
        this.number = number;
    }
}
