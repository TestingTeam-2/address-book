package AddressBook;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import org.junit.Rule;

public class AddressBookControllerTest {

    @Mock
    AddressBook addressBook;

    @Mock
    AddressBookController addressBookController;

    @Mock
    Person p;

    @Mock
    Person p1;


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
    }

    @AfterEach
    public void tearDown() {
        addressBook = null;
        addressBookController = null;
        p = null;
        p1 = null;
    }

    /**
     * Checks if persons are correctly added to address book.
     *
     * @result Persons will be added to address book without any errors.
     */
    @Test
    @Timeout(1)
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
    @Timeout(1)
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
    @Timeout(1)
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
    @Timeout(1)
    public void get() {
        addressBook.add(p);
        assertNotNull(addressBookController.get(0));
    }

  @Test
    public void testInvalidGet() throws IndexOutOfBoundsException {

        // stub test
        assertThrows(IndexOutOfBoundsException.class, () -> when(
            addressBook.get(
                anyInt()
            )
        ).thenThrow(new IndexOutOfBoundsException()));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            addressBookController.get(0);
        });
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

    @Test
    public void save() {

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

}

