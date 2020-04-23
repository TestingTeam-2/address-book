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

    /**
     * Initializes Person object with all fields.
     * @param firstName
     * @param lastName
     * @param address
     * @param city
     * @param state
     * @param zip
     * @param phone
     */
    public Person(String firstName, String lastName, String address, String city, String state, String zip, String phone) {
        if (firstName == null || firstName.isEmpty())
            throw new IllegalArgumentException("First name cannot be empty");
        if (lastName == null || lastName.isEmpty())
            throw new IllegalArgumentException("Last name cannot be empty");
        if (zip.length() != 5 && zip != null)
            throw new IllegalArgumentException("The Zip Code must be 5 digits");
        if (!(phone.length() == 7 || phone.length() == 10) && phone != null)
            throw new IllegalArgumentException("Phone numbers must be 7 or 10 digits if any");

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
    }

    /**
     * @return first name of Person object.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return last name of Person object.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return address of Person object.
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return city of Person object.
     */
    public String getCity() {
        return city;
    }

    /**
     * @return state of Person object.
     */
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

    /**
     * @return Last name, First name of a person object.
     */
    @Override
    public String toString() {
        return lastName + ", " + firstName;
    }

    /**
     * Search functionality to filter through all fields of a Person object.
     *
     * @return true if the desired string is found in any field of a person
     */
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

    /**
     * Searches out a desired individual field of a person object.
     *
     * @param field desired field
     * @return desired field
     */
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