package AddressBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddressBookControllerTest {

    @Mock
    AddressBook addressBook;


    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
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

    @Test
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

    @Test
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

    @Test
    public void get() {
        AddressBook addressBook = new AddressBook();
        AddressBookController addressBookController = new AddressBookController(addressBook);
        Person p = new Person("Brian", "Withrow", "12345 12TH AVE SE",
            "Naples", "FL", "30001", "239555555");
        addressBook.add(p);
        assertNotNull(addressBookController.get(0));
    }

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

    @Test
    public void open() throws FileNotFoundException, SQLException {

        AddressBookController addressBookController = new AddressBookController(addressBook);
        File file = new File("test/AddressBook/test");

        addressBookController.open(file);
        verify(addressBook, times(1)).fireTableDataChanged();
    }

    @Test
    public void save() {

    }

    @Test
    public void getModel() {
        AddressBook addressBook = new AddressBook();
        AddressBookController addressBookController = new AddressBookController(addressBook);

        assertNotNull(addressBookController.getModel());
    }

}

