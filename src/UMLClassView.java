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
    private JTextArea outputArea;
    private JTextField pathField;
    private JButton convertFileButton;
    private JTextField packagePathField;
    private JCheckBox watermarkBox;
    private JCheckBox generateGetSetBox;
    private JCheckBox autoFillConstructorBox;
    private JCheckBox ignoreEncapsulationBox;
    private JTextField defaultEncapsulationField;

    private UMLManager manager;

    /**
     * Creates the GUI for the application
     */
    public UMLClassView() {

        inputArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                refreshTextArea();
            }
            @Override
            public void keyReleased(KeyEvent e) {
                refreshTextArea();
            }
        });

        convertFileButton.addActionListener(e -> {
            String path = pathField.getText();
            String packagePath = packagePathField.getText();
            XMLParser parser = new XMLParser(path);
            manager.parseClasses(parser.getClassesText(), packagePath);
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


        watermarkBox.addActionListener(e -> {
            manager.setShowWatermark(watermarkBox.isSelected());
            refreshTextArea();
        });

        generateGetSetBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.setGetSetAuto(generateGetSetBox.isSelected());
                refreshTextArea();
            }
        });
        autoFillConstructorBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.setAutoGenerateConstructor(autoFillConstructorBox.isSelected());
                refreshTextArea();
            }
        });
        ignoreEncapsulationBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.setIgnoreEcapsulation(ignoreEncapsulationBox.isSelected());
                defaultEncapsulationField.setEnabled(ignoreEncapsulationBox.isSelected());
                refreshTextArea();
            }
        });

        defaultEncapsulationField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                UMLManager.setDefaultEncapsulation(defaultEncapsulationField.getText() + " ");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                UMLManager.setDefaultEncapsulation(defaultEncapsulationField.getText() + " ");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                UMLManager.setDefaultEncapsulation(defaultEncapsulationField.getText() + " ");
            }
        });
    }

    private void refreshTextArea(){
        String text = inputArea.getText();
        UMLClass umlClass = new UMLClass(text, "", manager);
        outputArea.setText(umlClass.toString());
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setManager(UMLManager manager) {
        this.manager = manager;
    }

    public boolean isWatermarkSelected(){
        return watermarkBox.isSelected();
    }

    public boolean isGetSetAutoSelected() {
        return generateGetSetBox.isSelected();
    }

    public boolean isAutoConstructorSelected() {
        return autoFillConstructorBox.isSelected();
    }
}
