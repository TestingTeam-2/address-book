package AddressBook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
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

  @Test
  void getPersons() {
  }

  @Test
  void add() {
  }

  @Test
  void set() {
  }

  @Test
  void remove() {
  }

  @Test
  @Timeout(1)
  void removeTime() {
    Person person = new Person("Brian", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001",
        "239555555");
    AddressBook addresses = new AddressBook();

    addresses.add(person);
    addresses.remove(0);
  }


  @Test
  void get() {
  }

  @Test
  void clear() {
  }

  @Test
  void getRowCount() {
    List<Person> persons = new ArrayList<>();
    persons.add(
        new Person("Brian", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555555"));
    persons.add(
        new Person("Briana", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555556"));
    assertEquals(2, persons.size());
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
  }

  @Test
  void getColumnName() {
  }
}