package AddressBook;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

  @Test
  void getFirstName() {
    List<Person> persons = new ArrayList<>();
    persons.add(new Person("Brian", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555555"));
    persons.add(new Person("Briana", "Winslow", "54321 12TH AVE SE", "Atlanta", "GA", "30002", "239555557"));
    String[] firstNames = new String[]{persons.get(0).getFirstName(),persons.get(1).getFirstName()};
    assertArrayEquals(new String[]{"Brian", "Briana"}, firstNames);
  }

  @Test
  void getLastName() {
    List<Person> persons = new ArrayList<>();
    persons.add(new Person("Brian", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555555"));
    persons.add(new Person("Briana", "Winslow", "54321 12TH AVE SE", "Atlanta", "GA", "30002", "239555557"));
    String[] lastNames = new String[]{persons.get(0).getLastName(),persons.get(1).getLastName()};
    assertArrayEquals(new String[]{"Withrow", "Winslow"}, lastNames);
  }

  @Test
  void getAddress() {
    List<Person> persons = new ArrayList<>();
    persons.add(new Person("Brian", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555555"));
    persons.add(new Person("Briana", "Winslow", "54321 12TH AVE SE", "Atlanta", "GA", "30002", "239555557"));
    String[] addresses = new String[]{persons.get(0).getAddress(),persons.get(1).getAddress()};
    assertArrayEquals(new String[]{"12345 12TH AVE SE", "54321 12TH AVE SE"}, addresses);
  }

  @Test
  void getCity() {
    List<Person> persons = new ArrayList<>();
    persons.add(new Person("Brian", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555555"));
    persons.add(new Person("Briana", "Winslow", "54321 12TH AVE SE", "Atlanta", "GA", "30002", "239555557"));
    String[] cities = new String[]{persons.get(0).getCity(),persons.get(1).getCity()};
    assertArrayEquals(new String[]{"Naples", "Atlanta"}, cities);
  }

  @Test
  void getState() {
    List<Person> persons = new ArrayList<>();
    persons.add(new Person("Brian", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555555"));
    persons.add(new Person("Briana", "Winslow", "54321 12TH AVE SE", "Atlanta", "GA", "30002", "239555557"));
    String[] states = new String[]{persons.get(0).getState(),persons.get(1).getState()};
    assertArrayEquals(new String[]{"FL", "GA"}, states);
  }

  @Test
  void getZip() {
    List<Person> persons = new ArrayList<>();
    persons.add(new Person("Brian", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555555"));
    persons.add(new Person("Briana", "Winslow", "54321 12TH AVE SE", "Atlanta", "GA", "30002", "239555557"));
    String[] zips = new String[]{persons.get(0).getZip(),persons.get(1).getZip()};
    assertArrayEquals(new String[]{"30001", "30002"}, zips);
  }

  @Test
  void getPhone() {
    List<Person> persons = new ArrayList<>();
    persons.add(new Person("Brian", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555555"));
    persons.add(new Person("Briana", "Winslow", "54321 12TH AVE SE", "Atlanta", "GA", "30002", "239555557"));
    String[] phones = new String[]{persons.get(0).getPhone(),persons.get(1).getPhone()};
    assertArrayEquals(new String[]{"239555555", "239555557"}, phones);
  }


  @Test
  void toStringTest() {
    List<Person> persons = new ArrayList<>();
    persons.add(new Person("Brian", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555555"));
    persons.add(new Person("Briana", "Winslow", "54321 12TH AVE SE", "Atlanta", "GA", "30002", "239555557"));
    String[] names = new String[]{persons.get(0).getLastName() + ", " + persons.get(0).getFirstName(),persons.get(1).getLastName() + ", " + persons.get(1).getFirstName()};

    assertEquals(persons.get(0).toString(), names[0].toString());
    assertEquals(persons.get(1).toString(), names[1].toString());
  }

  @Test
  void containsString() {
    String[] searches = new String[]{"Brian", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555555"};
    Person persons = new Person("Brian", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555555");

    for(String s : searches){
      assertEquals(true, persons.containsString(s));
    }
  }

  @Test
  void getField() {
    String[] searches = new String[]{"Withrow", "Brian", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555555"};
    Person persons = new Person("Brian", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555555");

    for(int i = 0; i < searches.length; i++){
      assertEquals(searches[i], persons.getField(i));
    }
    Exception exception = assertThrows(IllegalArgumentException.class,() -> persons.getField(7));
    assertEquals("Field number out of bounds", exception.getMessage());
  }
}