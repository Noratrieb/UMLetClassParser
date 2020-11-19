import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UMLClassView {
    private JTextArea inputArea;
    private JPanel panel1;
    private JButton convertButton;
    private JTextArea outputArea;
    private JTextField pathField;
    private JButton convertFileButton;

    private UMLManager manager;


    public UMLClassView() {

        convertButton.addActionListener(e -> {
            String text = inputArea.getText();

            UMLClass umlClass = new UMLClass(text);

            outputArea.setText(umlClass.toString());
        });

        inputArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String text = inputArea.getText();
                UMLClass umlClass = new UMLClass(text);
                outputArea.setText(umlClass.toString());
            }
        });

        convertFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = pathField.getText();
                XMLParser parser = new XMLParser(path);
                manager.parseClasses(parser.getClassesText());
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setManager(UMLManager manager) {
        this.manager = manager;
    }
}
