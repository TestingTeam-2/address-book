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

  @AfterEach
  public void tearDown() {
    fs = null;
    file = null;
    persons = null;
    writeAddressBook = null;
    readAddressBook = null;
  }

  @Test
  void readFile() throws SQLException, FileNotFoundException {
    fs.readFile(readAddressBook, file);
    for (int i = 0; i < readAddressBook.getPersons().length; i++) {
      for (int j = 0; j < Person.fields.length; j++) {
        assertEquals(persons.get(i).getField(j),readAddressBook.get(i).getField(j));
      }
    }
  }

  @Test
  void readFileNotFound() throws SQLException, FileNotFoundException {
    File fakefile = new File("test/AddressBook/fake.db");
    assertThrows(FileNotFoundException.class, () -> fs.readFile(readAddressBook,fakefile));
  }

  @Test
  void readFileCannotOpen() throws SQLException, FileNotFoundException {
    File lackOfPermissionFile = new File("test/AddressBook/permissiontest.db");
    lackOfPermissionFile.setReadable(false);
    assertThrows(FileNotFoundException.class, () -> fs.readFile(readAddressBook,lackOfPermissionFile));
    lackOfPermissionFile.setReadable(true);
  }

  @Test
  void saveFile() throws SQLException, FileNotFoundException {
    fs.saveFile(writeAddressBook,file);
    AddressBook readAddressBook = new AddressBook();
    fs.readFile(readAddressBook, file);
    for (int i = 0; i < readAddressBook.getPersons().length; i++) {
      for (int j = 0; j < Person.fields.length; j++) {
        assertEquals(persons.get(i).getField(j),readAddressBook.get(i).getField(j));
      }
    }
  }
}