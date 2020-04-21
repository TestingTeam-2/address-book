package AddressBook;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class PersonTest {

  @Mock
  List<Person> persons;

  @Mock
  String[] searches;

  @Mock
  Person person;

  @BeforeEach
  public void setUp() {
    persons = new ArrayList<>();
    persons.add(
        new Person("Brian", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555555"));
    persons.add(new Person("Briana", "Winslow", "54321 12TH AVE SE", "Atlanta", "GA", "30002",
        "239555557"));
    searches = new String[]{"Withrow", "Brian", "12345 12TH AVE SE", "Naples", "FL", "30001", "239555555"};
    person = new Person("Brian", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001",
        "239555555");
  }

  @AfterEach
  public void tearDown() {
    persons = null;
    searches = null;
    person = null;
  }

  /**
   * Checks if person's first name is correctly retrieved from address book.
   *
   * @result Person's first name will be retrieved from address book without any errors.
   */
  @Test
  void getFirstName() {
    String[] firstNames = new String[]{persons.get(0).getFirstName(),
        persons.get(1).getFirstName()};
    assertArrayEquals(new String[]{"Brian", "Briana"}, firstNames);
  }

  /**
   * Checks if person's last name is correctly retrieved from address book.
   *
   * @result Person's last name will be retrieved from address book without any errors.
   */
  @Test
  void getLastName() {
    String[] lastNames = new String[]{persons.get(0).getLastName(), persons.get(1).getLastName()};
    assertArrayEquals(new String[]{"Withrow", "Winslow"}, lastNames);
  }

  /**
   * Checks if person's address is correctly retrieved from address book.
   *
   * @result Person's address will be retrieved from address book without any errors.
   */
  @Test
  void getAddress() {
    String[] addresses = new String[]{persons.get(0).getAddress(), persons.get(1).getAddress()};
    assertArrayEquals(new String[]{"12345 12TH AVE SE", "54321 12TH AVE SE"}, addresses);
  }

  /**
   * Checks if person's city is correctly retrieved from address book.
   *
   * @result Person's city will be retrieved from address book without any errors.
   */
  @Test
  void getCity() {
    String[] cities = new String[]{persons.get(0).getCity(), persons.get(1).getCity()};
    assertArrayEquals(new String[]{"Naples", "Atlanta"}, cities);
  }

  /**
   * Checks if person's state is correctly retrieved from address book.
   *
   * @result Person's state will be retrieved from address book without any errors.
   */
  @Test
  void getState() {
    String[] states = new String[]{persons.get(0).getState(), persons.get(1).getState()};
    assertArrayEquals(new String[]{"FL", "GA"}, states);
  }

  /**
   * Checks if person's zip code is correctly retrieved from address book.
   *
   * @result Person's zip code will be retrieved from address book without any errors.
   */
  @Test
  void getZip() {
    String[] zips = new String[]{persons.get(0).getZip(), persons.get(1).getZip()};
    assertArrayEquals(new String[]{"30001", "30002"}, zips);
  }

  /**
   * Checks if person's phone number is correctly retrieved from address book.
   *
   * @result Person's phone number will be retrieved from address book without any errors.
   */
  @Test
  void getPhone() {
    String[] phones = new String[]{persons.get(0).getPhone(), persons.get(1).getPhone()};
    assertArrayEquals(new String[]{"239555555", "239555557"}, phones);
  }

  /**
   * Checks if person object correctly converted to string.
   *
   * @result Person object will be converted to a string without any errors.
   */
  @Test
  void toStringTest() {
    assertEquals(persons.get(0).getLastName() + ", " + persons.get(0).getFirstName(), persons.get(0).toString());
  }

  /**
   * Checks if string is contained within a field of person object.
   *
   * @result Returns true or false depending on without any errors.
   */
  @Test
  void containsString() {
    for (String s : searches) {
      assertThat(true, is(person.containsString(s)));
    }
    assertThat(false, is(person.containsString("fake string")));
  }

  /**
   * Checks if person's field is correctly retrieved from address book based on index.
   *
   * @result Person's field will be retrieved from address book without any errors.
   */
  @Test
  void getField() {
    for (int i = 0; i < searches.length; i++) {
      assertEquals(searches[i], person.getField(i));
    }
  }

  @Test
  void initializeEmptyFirstName() {
    assertThrows(IllegalArgumentException.class, () -> new Person("", "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001",
        "239555555"));
  }

  @Test
  void initializeNullFirstName() {
    assertThrows(IllegalArgumentException.class, () -> new Person(null, "Withrow", "12345 12TH AVE SE", "Naples", "FL", "30001",
        "239555555"));
  }

  @Test
  void initializeEmptyLasttName() {
    assertThrows(IllegalArgumentException.class, () -> new Person("Brian", "", "12345 12TH AVE SE", "Naples", "FL", "30001",
        "239555555"));
  }

  @Test
  void initializeNullLasttName() {
    assertThrows(IllegalArgumentException.class, () -> new Person("Brian", null, "12345 12TH AVE SE", "Naples", "FL", "30001",
        "239555555"));
  }

  @Test
  void tryToGetIncorrectField() {
    assertThrows(IllegalArgumentException.class, () -> persons.get(0).getField(7));
  }
}