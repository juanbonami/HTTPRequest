// Java program to create a blank text
// field of definite number of columns.
import java.awt.event.*;
import java.sql.SQLOutput;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import static com.sun.deploy.util.DeployUIManager.setLookAndFeel;

public class MovieGui extends JFrame implements ActionListener {

    Api Apivar = new Api();

    // JTextField
    static JTextField t;

    static JTextArea ta;

    static JScrollPane sp;

    // JFrame
    static JFrame f;

    // JButton
    static JButton b;

    // label to display text
    static JLabel l;


    String textFromTextField;

    // default constructor
    MovieGui() {

    }

    // main method
    public static void main(String[] args)  {

        // create a new frame to store text field and button
        f = new JFrame("textfield");

        // create a label to display text
        l = new JLabel("nothing entered");

        // create a new button
        b = new JButton("submit");

        // create a object of the text class
        MovieGui te = new MovieGui();

        // addActionListener to button
        b.addActionListener(te);

        // create a object of JTextField with 16 columns
        t = new JTextField(16);

        ta = new JTextArea(10, 35);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setEditable(false);


        sp = new JScrollPane(ta,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        // create a panel to add buttons and textfield
        JPanel p = new JPanel();

        // add buttons and textfield to panel
        p.add(t);
        p.add(b);
        p.add(l);
        p.add(sp);

        // add panel to frame
        f.add(p);

        // set the size of frame
        f.setSize(500, 600);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        f.show();
    }


    // if the button is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("submit")) {
            // set the text of the label to the text of the field
            l.setText(t.getText());
            ta.setText(Apivar.details);

            textFromTextField = t.getText();
            try {
                Apivar.executeAPI(textFromTextField);

            } catch (Exception e1) {

            }
            // set the text of field to blank
            t.setText(" ");
        }
    }

}