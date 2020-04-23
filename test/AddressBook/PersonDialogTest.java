package AddressBook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Frame;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class PersonDialogTest {

  @Mock
  PersonDialog personDialog;
  @Mock
  Frame parent;
  @Mock
  Person p;


  /**
   * Sets up reused variable used in testing
   */
  @BeforeEach
  void setUp() {
    parent = new Frame();
  }

  /**
   * nullifies down the used variables in testing methods
   */
  @AfterEach
  void tearDown() {
    parent = null;
  }

  /**
   * Valid person is tested with getPerson() method
   *
   * @result method will true
   */
  @Test
  void GetPerson() {
    Person p = new Person("Brian",
        "Withrow", "12345 12TH AVE SE",
        "Naples", "FL", "30001", "2395555555");
    personDialog = new PersonDialog(parent, p);
    Person p2 = personDialog.getPerson();
    for (int i = 0; i < 7; i++) {
      assertEquals(p.getField(i), p2.getField(i));
    }
  }

  /**
   * Valid person is tested with isPersonValid() method
   *
   * @result method will true
   */
  @Test
  void IsPersonValid() {
    Person p = new Person("Brian",
        "Withrow", "12345 12TH AVE SE",
        "Naples", "FL", "30001", "2395555555");
    personDialog = new PersonDialog(parent, p);
    assertTrue(personDialog.isPersonValid());
  }

  /**
   * Person with incorrect zip format is tested against with isPersonValid() method
   *
   * @result method will return false
   */
  @Test
  void InvalidZipIsPersonValid() {
    p = new Person("Brian",
        "Withrow", "12345 12TH AVE SE",
        "Naples", "FL", "zippp", "2395555555");
    personDialog = new PersonDialog(parent, p);
    assertFalse(personDialog.isPersonValid());
  }

  /**
   * Person with incorrect phone format is tested against with isPersonValid() method
   *
   * @result method will return false
   */
  @Test
  void InvalidPhoneIsPersonValid() {
    Person p = new Person("Brian",
        "Withrow", "12345 12TH AVE SE",
        "Naples", "FL", "30001", "phoneeeeee");
    personDialog = new PersonDialog(parent, p);
    assertFalse(personDialog.isPersonValid());
  }

  /**
   * Address lengths for person are tested
   *
   * @result method will return false for address < 4, true for 4 < address < 20 and false for 20 <
   * address
   */
  @Test
  void AddressCheckIsPersonValid() {
    Person pLengthTooShort = new Person("Brian",
        "Withrow", "123",
        "Naples", "FL", "30001", "2395555555");
    Person pLengthCorrect1 = new Person("Brian",
        "Withrow", "1234",
        "Naples", "FL", "30001", "2395555555");
    Person pLengthCorrect2 = new Person("Brian",
        "Withrow", "12346789123467891234",
        "Naples", "FL", "30001", "2395555555");
    Person pLengthTooLong = new Person("Brian",
        "Withrow", "123467891234678912345",
        "Naples", "FL", "30001", "2395555555");
    personDialog = new PersonDialog(parent, pLengthTooShort);
    assertFalse(personDialog.isPersonValid());

    personDialog = new PersonDialog(parent, pLengthCorrect1);
    assertTrue(personDialog.isPersonValid());

    personDialog = new PersonDialog(parent, pLengthCorrect2);
    assertTrue(personDialog.isPersonValid());

    personDialog = new PersonDialog(parent, pLengthTooLong);
    assertFalse(personDialog.isPersonValid());
  }
}