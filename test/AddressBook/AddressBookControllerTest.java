package AddressBook;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class AddressBookControllerTest {

    @Mock
    AddressBook addressBook;


    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /**
     * Checks if persons are correctly added to address book.
     * @result Persons will be added to address book
     *         without any errors.
     */
    @Test
    @Timeout(1)
    public void add() {
        AddressBook addressBook = new AddressBook();
        AddressBookController addressBookController = new AddressBookController(addressBook);

        Person p = new Person("Brian", "Withrow", "12345 12TH AVE SE",
            "Naples", "FL", "30001", "239555555");
        Person p1 = new Person("Briana", "Withrow", "12345 12TH AVE SE",
            "Naples", "FL", "30001", "239555556");
        addressBookController.add(p);
        addressBookController.add(p1);
        Person[] pArray = new Person[]{p, p1};
        assertArrayEquals(pArray, addressBook.getPersons());
    }

    /**
     * Checks if persons are correctly set to certain index in address book.
     * @result Person will be set to certain index in address book
     *         without any errors.
     */
    @Test
    @Timeout(1)
    public void set() {
        AddressBook addressBook = new AddressBook();
        AddressBookController addressBookController = new AddressBookController(addressBook);

        int index = 0;
        Person p = new Person("Brian", "Withrow", "12345 12TH AVE SE",
            "Naples", "FL", "30001", "239555555");
        Person p1 = new Person("Briana", "Withrow", "12345 12TH AVE SE",
            "Naples", "FL", "30001", "239555556");
        addressBook.add(p);
        addressBookController.set(index, p1);
        assertEquals(p1, addressBook.get(index));
    }

    /**
     * Checks if persons are correctly removed from address book.
     * @result Person will be removed from address book
     *         without any errors.
     */
    @Test
    @Timeout(1)
    public void remove() {
        AddressBook addressBook = new AddressBook();
        AddressBookController addressBookController = new AddressBookController(addressBook);

        int index = 0;
        Person p = new Person("Brian", "Withrow", "12345 12TH AVE SE",
            "Naples", "FL", "30001", "239555555");
        addressBook.add(p);
        addressBookController.remove(index);
        assertEquals(0, addressBook.getRowCount());

    }

    /**
     * Checks if person is correctly retrieved based on certain index from address book.
     * @result Person will be retrieved from address book based on index parameter
     *         without any errors.
     */
    @Test
    @Timeout(1)
    public void get() {
        AddressBook addressBook = new AddressBook();
        AddressBookController addressBookController = new AddressBookController(addressBook);
        Person p = new Person("Brian", "Withrow", "12345 12TH AVE SE",
            "Naples", "FL", "30001", "239555555");
        addressBook.add(p);
        assertNotNull(addressBookController.get(0));
    }

    /**
     * Checks if address book is cleared.
     * @result Row count will be retrieved as zero
     *         without any errors.
     */
    @Test
    public void clear() {
        AddressBook addressBook = new AddressBook();
        AddressBookController addressBookController = new AddressBookController(addressBook);

        Person p = new Person("Brian", "Withrow", "12345 12TH AVE SE",
            "Naples", "FL", "30001", "239555555");
        Person p1 = new Person("Briana", "Withrow", "12345 12TH AVE SE",
            "Naples", "FL", "30001", "239555556");
        addressBook.add(p);
        addressBook.add(p1);
        addressBookController.clear();
        assertEquals(0, addressBook.getRowCount());
    }

    /**
     * Checks if address book is correctly opened.
     * @result Address book will be opened
     *         without any errors.
     */
    @Test
    public void open() throws FileNotFoundException, SQLException {
        AddressBook addressBook = Mockito.spy(new AddressBook());
        AddressBookController addressBookController = new AddressBookController(addressBook);
        File file = new File("test/AddressBook/test.db");

        addressBookController.open(file);
        addressBookController.open(file);
        addressBookController.open(file);
        verify(addressBook, times(3)).fireTableDataChanged();
    }

    @Test
    public void save() {

    }

    /**
     * Checks if address book is correctly retrieved.
     * @result Address book will be retrieved
     *         without any errors.
     */
    @Test
    public void getModel() {
        AddressBook addressBook = new AddressBook();
        AddressBookController addressBookController = new AddressBookController(addressBook);

        assertNotNull(addressBookController.getModel());
    }

}

