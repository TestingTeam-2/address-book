package AddressBook;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class PersonTest {

  @Test
  void getFirstName() {
    List<Person> persons = new ArrayList<>();
    persons.add(new Person("Brian", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555555"));
    persons.add(new Person("Briana", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555556"));
    String[] firstNames = new String[]{persons.get(0).getFirstName(),persons.get(1).getFirstName()};
    assertArrayEquals(new String[]{"Brian", "Briana"}, firstNames);
  }

  @Test
  void getLastName() {
  }

  @Test
  void getAddress() {
  }

  @Test
  void getCity() {
  }

  @Test
  void getState() {
  }

  @Test
  void getZip() {
  }

  @Test
  void getPhone() {
  }

  @Test
  void toStringTest() {
  }

  @Test
  void containsString() {
  }

  @Test
  void getField() {
  }
}