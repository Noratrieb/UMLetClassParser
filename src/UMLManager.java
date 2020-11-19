import java.util.ArrayList;

public class UMLManager {

    UMLClassView view;

    public UMLManager(UMLClassView view){
        this.view = view;
        view.setManager(this);
    }

    public void parseClasses(ArrayList<String> classesText){
        ArrayList<UMLClass> classes = new ArrayList<>();

        for (String text : classesText){
            classes.add(new UMLClass(text));
        }

        classes.forEach(e -> System.out.println(e.toString()));
    }
}
