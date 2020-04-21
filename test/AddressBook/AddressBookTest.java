package AddressBook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

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

  @BeforeEach
  public void setUp() {
    persons = new ArrayList<>();
    persons.add(
        new Person("Carlos", "Reyes", "1234 25th Way", "Naples", "FL", "30001", "2392392399"));
    persons.add(
        new Person("Brian", "Withrow", "1234 26th Way", "Chicago", "IL", "10001", "2012012001"));
    addressBook = new AddressBook();
  }

  @AfterEach
  public void tearDown() {
    persons = null;
    addressBook = null;
  }

  @Test
  void getPersons() {
    addressBook.add(persons.get(0));
    for (int i = 0; i < addressBook.getPersons().length; i++) {
        assertEquals(persons.get(0).getField(i), addressBook.getPersons()[0].getField(i));
    }
  }

  @Test
  void add() {
    assertEquals(0, addressBook.getPersons().length);
    addressBook.add(persons.get(0));
    assertEquals(1, addressBook.getPersons().length);
  }

  @Test
  void set() {
    addressBook.add(persons.get(1));
    addressBook.set(0, persons.get(0));
    assertEquals(persons.get(0).getFirstName(), addressBook.get(0).getFirstName());
  }

  @Test
  void remove() {
    addressBook.add(persons.get(0));
    assertEquals(1, addressBook.getPersons().length);
    addressBook.remove(0);
    assertEquals(0, addressBook.getPersons().length);
  }

  @Test
  @Timeout(1)
  void removeTime() {
    addressBook.add(persons.get(0));
    assertEquals(1, addressBook.getPersons().length);
    addressBook.remove(0);
    assertEquals(0, addressBook.getPersons().length);
  }


  @Test
  void get() {
  }

  @Test
  void clear() {
    addressBook.add(persons.get(0));
    assertEquals(1, addressBook.getPersons().length);
    addressBook.add(persons.get(1));
    assertEquals(2, addressBook.getPersons().length);
    addressBook.clear();
    assertEquals(0, addressBook.getPersons().length);
  }

  @Test
  void emptyClear() {
    assertEquals(0, addressBook.getPersons().length);
    addressBook.clear();
    assertEquals(0, addressBook.getPersons().length);
  }

  @Test
  void getRowCount() {
    addressBook.add(persons.get(0));
    assertEquals(1, addressBook.getPersons().length);
    assertEquals(1, addressBook.getRowCount());
  }

  @Test
  void getColumnCount() {
    addressBook.add(persons.get(0));
    assertEquals(7, addressBook.getColumnCount());
  }

  @Test
  void getColumnCountStub() {
    stubAddressBook stubTest = new stubAddressBook();
    assertEquals(0, stubTest.getColumnCount());
  }

  @Test
  void getRowCountStub() {
    stubAddressBook stubTest = new stubAddressBook();
    assertEquals(0, stubTest.getRowCount());
  }

  @Test
  void getValueAt() {
    addressBook.add(persons.get(0));
    addressBook.add(persons.get(1));
    assertEquals("Reyes", addressBook.getValueAt(0,0));
    assertEquals("Brian", addressBook.getValueAt(1,1));
  }

  @Test
  void getColumnName() {
    addressBook.add(persons.get(0));
    addressBook.add(persons.get(1));
    assertEquals("Last Name", addressBook.getColumnName(0));
    assertEquals("First Name", addressBook.getColumnName(1));
  }
}