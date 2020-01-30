package AddressBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;


public class FileSystem {
    
    public void readFile(AddressBook addressBook, File file) throws SQLException, FileNotFoundException {
        if (!file.exists() || !file.canRead()) {
            throw new FileNotFoundException();
        }
       
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
        ResultSet rs = connection.createStatement().executeQuery("SELECT lastName, firstName, address, city, state, zip, phone FROM persons");
        // Clear the current AddressBook contents
        addressBook.clear();
        // Iterate through all the records, adding them to the AddressBook
        while (rs.next()) {
            Person p = new Person(
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getString("state"),
                    rs.getString("zip"),
                    rs.getString("phone"));
            addressBook.add(p);
        }
      
        connection.close();
    }


    public void saveFile(AddressBook addressBook, File file) throws SQLException {
        // Create the table structure
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS persons");
        statement.execute("CREATE TABLE persons (firstName TEXT, lastName TEXT, address TEXT, city TEXT, state TEXT, zip TEXT, phone TEXT)");
        // Insert the data into the database
        PreparedStatement insert = connection.prepareStatement("INSERT INTO persons (lastName, firstName, address, city, state, zip, phone) VALUES (?, ?, ?, ?, ?, ?, ?)");
        for (Person p : addressBook.getPersons()) {
            for (int i = 0; i < Person.fields.length; i++) {
                insert.setString(i + 1, p.getField(i));
            }
            insert.executeUpdate();
        }
        
        connection.close();
    }
}