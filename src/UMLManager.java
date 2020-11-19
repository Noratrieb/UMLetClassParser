import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UMLManager {

    UMLClassView view;

    public UMLManager(UMLClassView view) {
        this.view = view;
        view.setManager(this);
    }

    public void parseClasses(ArrayList<String> classesText, String packagePath) {

        String packageString = packagePath.replaceAll(".*src\\\\(.*)", "$1");
        packageString = packageString.replaceAll("\\\\", ".");

        ArrayList<UMLClass> classes = new ArrayList<>();

        for (String text : classesText) {
            classes.add(new UMLClass(text, packageString));
        }

        classes.forEach(e -> System.out.println(e.toString()));

        for (UMLClass c : classes) {
            try {
                String path = packagePath + "/" + c.getName() + ".java";
                FileWriter fw = new FileWriter(path);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(c.toString());
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
