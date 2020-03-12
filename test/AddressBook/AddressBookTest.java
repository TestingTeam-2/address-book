package AddressBook;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressBookTest {

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

  @Test(timeout = 1)
  void removeTime(){
    Person person = new Person("Brian", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555555");
    AddressBook addresses = new AddressBook();

    addresses.add(person);
    addresses.remove(1);
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
    persons.add(new Person("Brian", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555555"));
    persons.add(new Person("Briana", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555556"));
    assertEquals(2, persons.size());
  }

  @Test
  void getColumnCount() {
  }

  @Test
  void getValueAt() {
  }

  @Test
  void getColumnName() {
  }
}