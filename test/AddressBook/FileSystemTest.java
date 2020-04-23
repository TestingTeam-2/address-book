package AddressBook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileSystemTest {

  private List<Person> persons;
  private AddressBook writeAddressBook;
  private AddressBook readAddressBook;
  private FileSystem fs;
  private File file;

  /**
   * Sets up the reused variables used in testing
   */
  @BeforeEach
  public void setUp() {
    fs = new FileSystem();
    file = new File("test/AddressBook/test.db");
    persons = new ArrayList<>();
    persons.add(
        new Person("Carlos", "Reyes", "1234 25th Way", "Naples", "FL", "30001", "2392392399"));
    persons.add(
        new Person("Brian", "Withrow", "1234 26th Way", "Chicago", "IL", "10001", "2012012001"));
    writeAddressBook = new AddressBook();
    writeAddressBook.add(persons.get(0));
    writeAddressBook.add(persons.get(1));
    readAddressBook = new AddressBook();
  }

  /**
   * nullifies down the used variables in testing methods
   */
  @AfterEach
  public void tearDown() {
    fs = null;
    file = null;
    persons = null;
    writeAddressBook = null;
    readAddressBook = null;
  }

  /**
   * Tests to see if Person data can be extracted and read form the supplied file
   */
  @Test
  void readFile() throws SQLException, FileNotFoundException {
    fs.readFile(readAddressBook, file);
    for (int i = 0; i < readAddressBook.getPersons().length; i++) {
      for (int j = 0; j < Person.fields.length; j++) {
        assertEquals(persons.get(i).getField(j), readAddressBook.get(i).getField(j));
      }
    }
  }

  /**
   * Ensures that files that are not found using the readFile() results in the throwing of a
   * FileNotFoundException
   */
  @Test
  void readFileNotFound() throws SQLException, FileNotFoundException {
    File fakefile = new File("test/AddressBook/fake.db");
    assertThrows(FileNotFoundException.class, () -> fs.readFile(readAddressBook, fakefile));
  }

  /**
   * Ensures that files that exist but do not grant this executing process permission throw a
   * FileNotFoundException within readFile()
   */
  @Test
  void readFileCannotOpen() throws SQLException, FileNotFoundException {
    File lackOfPermissionFile = new File("test/AddressBook/permissiontest.db");
    lackOfPermissionFile.setReadable(false);
    assertThrows(FileNotFoundException.class,
        () -> fs.readFile(readAddressBook, lackOfPermissionFile));
    lackOfPermissionFile.setReadable(true);
  }

  /**
   * Ensures that Person data is able to be saved in a file. This is confirmed by executing a
   * readFile() operation afterwards on the gathered data
   */
  @Test
  void saveFile() throws SQLException, FileNotFoundException {
    fs.saveFile(writeAddressBook, file);
    AddressBook readAddressBook = new AddressBook();
    fs.readFile(readAddressBook, file);
    for (int i = 0; i < readAddressBook.getPersons().length; i++) {
      for (int j = 0; j < Person.fields.length; j++) {
        assertEquals(persons.get(i).getField(j), readAddressBook.get(i).getField(j));
      }
    }
  }
}