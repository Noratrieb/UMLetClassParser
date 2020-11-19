import javax.swing.*;
import java.awt.*;

public class UMLConverterMain {

    public static void main(String[] args) {


        JFrame frame = new JFrame();
        frame.setContentPane( new UMLClassView().getPanel1());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }


}
