package AddressBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;


public class AddressBookController {
    AddressBook addressBook;

    /**
     * Sets this addressBook to the passed addressBook.
     */
    public AddressBookController(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a person object to the addressBook.
     * @param p
     * @throws NullPointerException
     */
    public void add(Person p) throws NullPointerException {
        addressBook.add(p);
    }

    /**
     * Sets an existing person to the passed person object at index i.
     * @param index
     * @param person
     */
    public void set(int index, Person person) {
        addressBook.set(index, person);
    }

    /**
     * Removes an existing person at the given index in the addressBook.
     * @param index
     */
    public void remove(int index) {
        addressBook.remove(index);
    }

    /**
     * Gets the person at a given index in the addressBook.
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    public Person get(int index) throws IndexOutOfBoundsException {
        return addressBook.get(index);
    }

    /**
     * Clears the addressBook of all entries.
     */
    public void clear() {
        addressBook.clear();
    }

    /**
     * The AddressBook opens a file and updates the table
     * to match the contents within the file.
     * @param file
     * @throws FileNotFoundException
     * @throws SQLException
     */
    public void open(File file) throws FileNotFoundException, SQLException {
        new FileSystem().readFile(addressBook, file);
        addressBook.fireTableDataChanged();
    }

    /**
     * The AddressBook saves the current file to the given
     * file destination.
     * @param file
     * @throws SQLException
     */
    public void save(File file) throws SQLException {
        new FileSystem().saveFile(addressBook, file);
    }

    /**
     * The AddressBook's model is returned to the user.
     * @return
     */
    public AddressBook getModel() {
        return addressBook;
    }
}