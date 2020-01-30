package AddressBook;

import java.util.regex.Pattern;


public class Person {
  
    public static final String[] fields =
            {
                    "Last Name",
                    "First Name",
                    "Address",
                    "City",
                    "State",
                    "ZIP",
                    "Phone",
            };

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;

 
    public Person(String firstName, String lastName, String address, String city, String state, String zip, String phone) {
        if (firstName == null || firstName.isEmpty())
            throw new IllegalArgumentException("First name cannot be empty");
        if (lastName == null || lastName.isEmpty())
            throw new IllegalArgumentException("Last name cannot be empty");
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
    }


    public String getFirstName() {
        return firstName;
    }

  
    public String getLastName() {
        return lastName;
    }

    
    public String getAddress() {
        return address;
    }

  
    public String getCity() {
        return city;
    }

   
    public String getState() {
        return state;
    }

    /**
     * Returns this Person's ZIP code.
     *
     * @return ZIP code of this Person
     */
    public String getZip() {
        return zip;
    }

    /**
     * Returns this Person's telephone number.
     *
     * @return Telephone number of this Person.
     */
    public String getPhone() {
        return phone;
    }

   
    @Override
    public String toString() {
        return lastName + ", " + firstName;
    }

  
    public boolean containsString(String findMe) {
        Pattern p = Pattern.compile(Pattern.quote(findMe), Pattern.CASE_INSENSITIVE);
        return p.matcher(firstName).find()
                || p.matcher(lastName).find()
                || p.matcher(address).find()
                || p.matcher(city).find()
                || p.matcher(state).find()
                || p.matcher(zip).find()
                || p.matcher(phone).find();
    }

   
    public String getField(int field) {
        switch (field) {
            case 0:
                return lastName;
            case 1:
                return firstName;
            case 2:
                return address;
            case 3:
                return city;
            case 4:
                return state;
            case 5:
                return zip;
            case 6:
                return phone;
            default:
                throw new IllegalArgumentException("Field number out of bounds");
        }
    }
}