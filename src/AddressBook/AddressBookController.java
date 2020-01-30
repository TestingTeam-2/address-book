package AddressBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * Created by 
 */
public class AddressBookController {
    AddressBook addressBook;

    public AddressBookController(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public void add(Person p) {
        addressBook.add(p);
    }

    public void set(int index, Person person) {
        addressBook.set(index, person);
    }

    public void remove(int index) {
        addressBook.remove(index);
    }

    public Person get(int index) {
        return addressBook.get(index);
    }

    public void clear() {
        addressBook.clear();
    }

    public void open(File file) throws FileNotFoundException, SQLException {
        new FileSystem().readFile(addressBook, file);
        addressBook.fireTableDataChanged();
    }

    public void save(File file) throws SQLException {
        new FileSystem().saveFile(addressBook, file);
    }

    public AddressBook getModel() {
        return addressBook;
    }
}