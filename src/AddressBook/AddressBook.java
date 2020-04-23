package AddressBook;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


public class AddressBook extends AbstractTableModel {
    private List<Person> persons = new ArrayList<>();

    /**
     * Returns the persons array within the AddressBook.
     * @return
     */
    public Person[] getPersons() {
        return persons.toArray(new Person[persons.size()]);
    }

    /**
     * Adds a person to the array of Persons in the AddressBook.
     * @param p
     */
    public void add(Person p) {
        int newIndex = persons.size();
        persons.add(p);
        fireTableRowsInserted(newIndex, newIndex);
    }

    /**
     * Sets the person at the given index to the Person specified.
     *
     * @param index  Index to update.
     * @param person Person to replace the item with.
     */
    public void set(int index, Person person) {
        persons.set(index, person);
        fireTableRowsUpdated(index, index);
    }

    /**
     * Removes a person from the list at the given index.
     * @param index
     */
    public void remove(int index) {
        persons.remove(index);
        fireTableRowsDeleted(index, index);
    }

    /**
     * Retrieves the person at the given index in the list.
     * @param index
     * @return
     */
    public Person get(int index) {
        return persons.get(index);
    }

    /**
     * Clears this address book.
     */
    public void clear() {
        if (persons.size() == 0) {
            return;
        }
        fireTableRowsDeleted(0, persons.size() - 1);
        persons.clear();
    }

    /**
     * Returns the number of rows in the AddressBook
     * AKA the number of people within the addressBook.
     * @return
     */
    @Override
    public int getRowCount() {
        return persons.size();
    }

    /**
     * Returns the number of columns in the AddressBook
     * AKA the number of fields that the AddressBook has.
     * @return
     */
    public int getColumnCount() {
        return Person.fields.length;
    }

    /**
     * Returns the value at a given 2d position within
     * the AddressBook.
     * @param row
     * @param column
     * @return
     */
    @Override
    public Object getValueAt(int row, int column) {
        return persons.get(row).getField(column);
    }

    /**
     * Returns the name of a desired column related to a Person.
     * @param column
     * @return
     */
    @Override
    public String getColumnName(int column) {
        return Person.fields[column];
    }
}