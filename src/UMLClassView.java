import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UMLClassView {
    private JTextArea textArea1;
    private JPanel panel1;
    private JButton convertButton;
    private JTextArea textArea2;


    public UMLClassView() {

        convertButton.addActionListener(e -> {
            String text = textArea1.getText();

            UMLClass umlClass = new UMLClass(text);

            textArea2.setText(umlClass.toString());
        });

        textArea1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String text = textArea1.getText();

                UMLClass umlClass = new UMLClass(text);

                textArea2.setText(umlClass.toString());
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
