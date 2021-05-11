public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact (String firstName, String lastName, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;

    }
     public void setContact(String firstName, String lastName, String phoneNumber){
         this.firstName = firstName;
         this.lastName = lastName;
         this.phoneNumber = phoneNumber;
     }
     public String getContact(){
         return this.firstName +  " " + this.lastName + " " + this.phoneNumber;
     }

}
