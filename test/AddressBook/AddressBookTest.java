package AddressBook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * Class developed to demonstrate stubbing for the AddressBook
 */
class AddressBookTest {

  class stubAddressBook extends AddressBook {

    public int getColumnCount() {
      return 0;
    }

    public int getRowCount() {
      return 0;
    }

  }

  private List<Person> persons;
  private AddressBook addressBook;

  /**
   * Sets up the reused variables used in testing
   */
  @BeforeEach
  public void setUp() {
    persons = new ArrayList<>();
    persons.add(
        new Person("Carlos", "Reyes", "1234 25th Way", "Naples", "FL", "30001", "2392392399"));
    persons.add(
        new Person("Brian", "Withrow", "1234 26th Way", "Chicago", "IL", "10001", "2012012001"));
    addressBook = new AddressBook();
  }

  /**
   * nullifies down the used variables in testing methods
   */
  @AfterEach
  public void tearDown() {
    persons = null;
    addressBook = null;
  }

  /**
   * Checks if persons are correctly retrieved.
   *
   * @result Persons will retrieved without any errors.
   */
  @Test
  void getPersons() {
    addressBook.add(persons.get(0));
    for (int i = 0; i < addressBook.getPersons().length; i++) {
      assertEquals(persons.get(0).getField(i), addressBook.getPersons()[0].getField(i));
    }
  }

  /**
   * Checks if persons are correctly added to address book .
   *
   * @result Persons will be added to address book without any errors.
   */
  @Test
  void add() {
    assertEquals(0, addressBook.getPersons().length);
    addressBook.add(persons.get(0));
    assertEquals(1, addressBook.getPersons().length);
  }

  /**
   * Checks if persons are correctly set to certain index in address book.
   *
   * @result Person will be set to certain index in address book without any errors.
   */
  @Test
  void set() {
    addressBook.add(persons.get(1));
    addressBook.set(0, persons.get(0));
    assertEquals(persons.get(0).getFirstName(), addressBook.get(0).getFirstName());
  }

  /**
   * Checks if persons are correctly removed from address book.
   *
   * @result Person will be removed from address book without any errors.
   */
  @Test
  void remove() {
    addressBook.add(persons.get(0));
    assertEquals(1, addressBook.getPersons().length);
    addressBook.remove(0);
    assertEquals(0, addressBook.getPersons().length);
  }

  /**
   * Checks if persons are correctly removed from address book within 1 second.
   *
   * @result Person will be removed from address book without any errors.
   */
  @Test
  @Timeout(1)
  void removeTime() {
    addressBook.add(persons.get(0));
    assertEquals(1, addressBook.getPersons().length);
    addressBook.remove(0);
    assertEquals(0, addressBook.getPersons().length);
  }


  /**
   * Checks if address book is cleared.
   *
   * @result Row count will be retrieved as zero without any errors.
   */
  @Test
  void clear() {
    addressBook.add(persons.get(0));
    assertEquals(1, addressBook.getPersons().length);
    addressBook.add(persons.get(1));
    assertEquals(2, addressBook.getPersons().length);
    addressBook.clear();
    assertEquals(0, addressBook.getPersons().length);
  }

  /**
   * Checks if address book is cleared when address book is empty.
   *
   * @result Row count will be retrieved as zero without any errors.
   */
  @Test
  void emptyClear() {
    assertEquals(0, addressBook.getPersons().length);
    addressBook.clear();
    assertEquals(0, addressBook.getPersons().length);
  }

  /**
   * Checks if row count is successfully retrieved.
   *
   * @result Row count will be retrieved without any errors.
   */
  @Test
  void getRowCount() {
    addressBook.add(persons.get(0));
    assertEquals(1, addressBook.getPersons().length);
    assertEquals(1, addressBook.getRowCount());
  }

  /**
   * Checks if column count is successfully retrieved.
   *
   * @result Column count will be retrieved as zero without any errors.
   */
  @Test
  void getColumnCount() {
    addressBook.add(persons.get(0));
    assertEquals(7, addressBook.getColumnCount());
  }

  /**
   * Tests the stubbed getColumnCount method Checks if column count is successfully retrieved.
   */
  @Test
  void getColumnCountStub() {
    stubAddressBook stubTest = new stubAddressBook();
    assertEquals(0, stubTest.getColumnCount());
  }

  /**
   * Tests the stubbed getRowCountStub Checks if row count is successfully retrieved.
   */
  @Test
  void getRowCountStub() {
    stubAddressBook stubTest = new stubAddressBook();
    assertEquals(0, stubTest.getRowCount());
  }

  /**
   * Checks if values at indices are correctly retrieved.
   *
   * @result Values at indices retrieved without any errors.
   */
  @Test
  void getValueAt() {
    addressBook.add(persons.get(0));
    addressBook.add(persons.get(1));
    assertEquals("Reyes", addressBook.getValueAt(0, 0));
    assertEquals("Brian", addressBook.getValueAt(1, 1));
  }

  /**
   * Checks if column names are correctly retrieved.
   *
   * @result Column names retrieved without any errors.
   */
  @Test
  void getColumnName() {
    addressBook.add(persons.get(0));
    addressBook.add(persons.get(1));
    assertEquals("Last Name", addressBook.getColumnName(0));
    assertEquals("First Name", addressBook.getColumnName(1));
  }
}