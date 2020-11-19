import javax.swing.*;
import java.awt.*;

public class UMLConverterMain {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        UMLClassView view = new UMLClassView();
        new UMLManager(view);
        frame.setContentPane(view.getPanel1());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        //frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }


}
