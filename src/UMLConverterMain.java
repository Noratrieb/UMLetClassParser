import javax.swing.*;

/**
 * Stars the UMLetClassParser
 */
public class UMLConverterMain {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        UMLClassView view = new UMLClassView();
        new UMLManager(view);
        frame.setContentPane(view.getPanel1());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setVisible(true);

    }


}
