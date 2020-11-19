import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

public class UMLClassView {
    private JTextArea inputArea;
    private JPanel panel1;
    private JButton convertButton;
    private JTextArea outputArea;
    private JTextField pathField;
    private JButton convertFileButton;
    private JTextField packagePathField;

    private UMLManager manager;


    public UMLClassView() {

        convertButton.addActionListener(e -> {
            String text = inputArea.getText();

            UMLClass umlClass = new UMLClass(text, "");

            outputArea.setText(umlClass.toString());
        });

        inputArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String text = inputArea.getText();
                UMLClass umlClass = new UMLClass(text, "");
                outputArea.setText(umlClass.toString());
            }
            @Override
            public void keyReleased(KeyEvent e) {
                String text = inputArea.getText();
                UMLClass umlClass = new UMLClass(text, "");
                outputArea.setText(umlClass.toString());
            }
        });

        convertFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = pathField.getText();
                String packagePath = packagePathField.getText();
                XMLParser parser = new XMLParser(path);
                manager.parseClasses(parser.getClassesText(), packagePath);
            }
        });

        pathField.setDropTarget(new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent dtde) {
                try {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>)
                            dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    for (File file : droppedFiles) {
                        String path = file.getAbsolutePath();
                        pathField.setText(path);
                        String packagePath = packagePathField.getText();
                        XMLParser parser = new XMLParser(path);
                        manager.parseClasses(parser.getClassesText(), packagePath);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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
