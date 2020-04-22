package AddressBook;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import org.jetbrains.annotations.Nullable;


public class PersonDialog extends JDialog {

    public enum Result {
        OK,
        CANCEL,
    }

    private Result result;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField address;
    private JTextField city;
    private JTextField state;
    private JTextField zip;
    private JTextField phone;
    private JLabel errorMsg;

    public PersonDialog(Frame parent) {

        super(parent);

        JLabel l;
        AtomicReference<JPanel> p = new AtomicReference<>(new JPanel(new SpringLayout()));

        l = new JLabel("First name:", JLabel.TRAILING);
        p.get().add(l);
        firstName = new JTextField(20);
        l.setLabelFor(firstName);
        p.get().add(firstName);

        l = new JLabel("Last name:", JLabel.TRAILING);
        p.get().add(l);
        lastName = new JTextField(20);
        l.setLabelFor(lastName);
        p.get().add(lastName);

        l = new JLabel("Address:", JLabel.TRAILING);
        p.get().add(l);
        address = new JTextField(20);
        l.setLabelFor(address);
        p.get().add(address);

        l = new JLabel("City:", JLabel.TRAILING);
        p.get().add(l);
        city = new JTextField(20);
        l.setLabelFor(city);
        p.get().add(city);

        l = new JLabel("State:", JLabel.TRAILING);
        p.get().add(l);
        state = new JTextField(20);
        l.setLabelFor(state);
        p.get().add(state);

        l = new JLabel("ZIP code:", JLabel.TRAILING);
        p.get().add(l);
        zip = new JTextField(20);
        l.setLabelFor(zip);
        p.get().add(zip);

        l = new JLabel("Telephone:", JLabel.TRAILING);
        p.get().add(l);
        phone = new JTextField(20);
        l.setLabelFor(phone);
        p.get().add(phone);

        SpringUtilities.makeCompactGrid(p.get(), 7, 2, 6, 6, 6, 6);

        // Set up the buttons
        JPanel buttons = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.setMnemonic('O');
        okButton.addActionListener(e ->
        {
            if (isPersonValid()) {
                result = Result.OK;
                setVisible(false);
            }
        });
        buttons.add(okButton);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setMnemonic('C');
        cancelButton.addActionListener(e ->
        {
            result = Result.CANCEL;
            setVisible(false);
        });
        buttons.add(cancelButton);

        errorMsg = new JLabel("");
        errorMsg.setBorder(new EmptyBorder(5, 5, 5, 0));
        errorMsg.setPreferredSize(new Dimension(0, 20));

        p.get().add(errorMsg);
        // Set window properties
        getContentPane().add(p.get(), BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.PAGE_END);
        getContentPane().add(errorMsg, BorderLayout.PAGE_START);
        pack();
        setTitle("Person Information");
        setModalityType(ModalityType.DOCUMENT_MODAL);
        setLocation((parent.getWidth() - getWidth()) / 2, (parent.getHeight() - getHeight()) / 2);
    }


    public PersonDialog(Frame parent, @Nullable Person person) {
        this(parent);
        if (person == null) {
            return;
        }
        firstName.setText(person.getFirstName());
        lastName.setText(person.getLastName());
        address.setText(person.getAddress());
        city.setText(person.getCity());
        state.setText(person.getState());
        zip.setText(person.getZip());
        phone.setText(person.getPhone());
    }


    public Result showDialog() {
        // Default to CANCEL if the user closes the dialog window
        result = Result.CANCEL;
        setVisible(true);
        return result;
    }


    public Person getPerson() {

        if (firstName != null && lastName != null &&
            !firstName.getText().isEmpty() && !lastName.getText().isEmpty()) {
            return new Person(firstName.getText(),
                lastName.getText(),
                address.getText(),
                city.getText(),
                state.getText(),
                zip.getText(),
                phone.getText());
        } else {
            return null;
        }
    }

    public boolean isPersonValid() {

        Pattern numberPattern = Pattern.compile("^[0-9]*$");

        if (firstName == null || firstName.getText().isEmpty()) {
            errorMsg.setText("Please enter a first name.");
            return false;
        }
        if (lastName == null || lastName.getText().isEmpty()) {
            errorMsg.setText("Please enter a last name.");
            return false;
        }
        if (!address.getText().isEmpty() && (address.getText().length() < 4 || 20 < address.getText().length() )) {
            errorMsg.setText("Please enter a longer address.");
            return false;
        }
        if (!zip.getText().isEmpty()) {
            Matcher zipMatcher = numberPattern.matcher(zip.getText());
            if (!zipMatcher.matches()) {
                errorMsg.setText("Please enter a numerical zip code.");
                return false;
            }
        }
        if (!phone.getText().isEmpty()) {
            Matcher phoneMatcher = numberPattern.matcher(phone.getText());
            if (!phoneMatcher.matches()) {
                errorMsg.setText("Please enter a numerical phone number.");
                return false;
            }
        }
        return true;
    }
}