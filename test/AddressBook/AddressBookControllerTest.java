package AddressBook;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class AddressBookControllerTest {

    @Mock
    AddressBook addressBook;

    @Mock
    AddressBookController addressBookController;

    @Mock
    Person p;

    @Mock
    Person p1;

    private FileSystem fs;
    private File file;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @BeforeEach
    public void setUp() {
        addressBook = new AddressBook();
        addressBookController = new AddressBookController(addressBook);
        p = new Person("Brian", "Withrow", "12345 12TH AVE SE",
            "Naples", "FL", "30001", "239555555");
        p1 = new Person("Briana", "Withrow", "12345 12TH AVE SE",
            "Naples", "FL", "30001", "239555556");
        fs = new FileSystem();
        file = new File("test/AddressBook/test.db");
    }

    @AfterEach
    public void tearDown() {
        addressBook = null;
        addressBookController = null;
        p = null;
        p1 = null;
        fs = null;
        file = null;
    }

    /**
     * Checks if persons are correctly added to address book.
     *
     * @result Persons will be added to address book without any errors.
     */
    @Test
    public void add() {
        addressBookController.add(p);
        addressBookController.add(p1);
        Person[] pArray = new Person[]{p, p1};
        assertArrayEquals(pArray, addressBook.getPersons());
    }

    /**
     * Checks if persons are correctly set to certain index in address book.
     *
     * @result Person will be set to certain index in address book without any errors.
     */
    @Test
    public void set() {
        int index = 0;
        addressBook.add(p);
        addressBookController.set(index, p1);
        assertEquals(p1, addressBook.get(index));
    }

    /**
     * Checks if persons are correctly removed from address book.
     *
     * @result Person will be removed from address book without any errors.
     */
    @Test
    public void remove() {
        int index = 0;
        addressBook.add(p);
        addressBookController.remove(index);
        assertEquals(0, addressBook.getRowCount());
    }

    /**
     * Checks if person is correctly retrieved based on certain index from address book.
     *
     * @result Person will be retrieved from address book based on index parameter without any
     * errors.
     */
    @Test
    public void get() {
        addressBook.add(p);
        assertNotNull(addressBookController.get(0));
    }

    /**
     * Checks if address book is cleared.
     *
     * @result Row count will be retrieved as zero without any errors.
     */
    @Test
    public void clear() {
        addressBook.add(p);
        addressBook.add(p1);
        addressBookController.clear();
        assertEquals(0, addressBook.getRowCount());
    }

    /**
     * Checks if address book is correctly opened.
     *
     * @result Address book will be opened without any errors.
     */
    @Test
    public void open() throws FileNotFoundException, SQLException {
        addressBook = Mockito.spy(new AddressBook());
        addressBookController = new AddressBookController(addressBook);
        File file = new File("test/AddressBook/test.db");

        assertThrows(FileNotFoundException.class, () ->
            addressBookController.open(new File("#")));

        addressBookController.open(file);
        addressBookController.open(file);
        addressBookController.open(file);
        verify(addressBook, times(3)).fireTableDataChanged();
    }

    /**
     * Checks if address book is correctly saved.
     *
     * @result Address book will be saved without any errors.
     */
    @Test
    public void save() throws SQLException, FileNotFoundException {
        List<Person> persons = new ArrayList<>();
        persons.add(
            new Person("Carlos", "Reyes", "1234 25th Way", "Naples", "FL", "30001", "2392392399"));
        persons.add(
            new Person("Brian", "Withrow", "1234 26th Way", "Chicago", "IL", "10001", "2012012001"));
        addressBook.add(persons.get(0));
        addressBook.add(persons.get(1));
        AddressBookController abc = new AddressBookController(addressBook);
        abc.save(file);
        AddressBook readAddressBook = new AddressBook();
        fs.readFile(readAddressBook, file);
        for (int i = 0; i < readAddressBook.getPersons().length; i++) {
            for (int j = 0; j < Person.fields.length; j++) {
                assertEquals(persons.get(i).getField(j),readAddressBook.get(i).getField(j));
            }
        }
    }

    /**
     * Checks if address book is correctly retrieved.
     *
     * @result Address book will be retrieved without any errors.
     */
    @Test
    public void getModel() {
        assertNotNull(addressBookController.getModel());
    }

    @Test
    public void testInvalidAdd() {
        assertThrows(IllegalArgumentException.class, () ->
            addressBookController.add( new Person("", "Withrow", "12345 12TH AVE SE",
                "Naples", "FL", "30001", "239555555")));
        assertThrows(IllegalArgumentException.class, () ->
            addressBookController.add(new Person(null, "Withrow", "12345 12TH AVE SE",
                "Naples", "FL", "30001", "239555555")));
    }
    @Test
    public void testInvalidSet() {
        addressBook.add(p);
        assertThrows(IndexOutOfBoundsException.class, () ->
            addressBookController.set(-1, p1));
    }
    @Test
    public void testInvalidRemove() {
        assertThrows(IndexOutOfBoundsException.class, () ->
            addressBookController.remove(0));
    }
    @Test
    public void testInvalidGet() throws IndexOutOfBoundsException {
        // stub test
        assertThrows(IndexOutOfBoundsException.class, () -> when(
            addressBook.get(
                anyInt()
            )
        ).thenThrow(new IndexOutOfBoundsException()));

        assertThrows(IndexOutOfBoundsException.class, () ->
            addressBookController.get(0)
        );
    }

}

