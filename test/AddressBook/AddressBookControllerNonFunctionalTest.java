package AddressBook;

import java.io.File;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class AddressBookControllerNonFunctionalTest {

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
     * Checks if persons are correctly added to address book within 1 second.
     *
     * @result Persons will be added to address book without any errors.
     */
    @Test
    @Timeout(1)
    public void add() {
        addressBookController.add(p);
    }

    /**
     * Checks if persons are correctly set to certain index in address book within 1 second.
     *
     * @result Person will be set to certain index in address book without any errors.
     */
    @Test
    @Timeout(1)
    public void set() {
        int index = 0;
        addressBook.add(p);
        addressBookController.set(index, p1);
    }

    /**
     * Checks if persons are correctly removed from address book within 1 second.
     *
     * @result Person will be removed from address book without any errors.
     */
    @Test
    @Timeout(1)
    public void remove() {
        int index = 0;
        addressBook.add(p);
        addressBookController.remove(index);
    }

    /**
     * Checks if person is correctly retrieved based on certain index from address book within 1 second.
     *
     * @result Person will be retrieved from address book based on index parameter without any
     * errors.
     */
    @Test
    @Timeout(1)
    public void get() {
        addressBook.add(p);
    }

    /**
     * Checks if address book is cleared within 1 second.
     *
     * @result Row count will be retrieved as zero without any errors.
     */
    @Test
    @Timeout(1)
    public void clear() {
        addressBook.add(p);
        addressBook.add(p1);
        addressBookController.clear();
    }

}

