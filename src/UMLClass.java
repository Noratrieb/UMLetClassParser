import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class UMLClass {

    private final String name;
    private final String fullName;
    private final String packageString;

    private final ArrayList<UMLField> fields = new ArrayList<>();
    private final ArrayList<UMLMethod> methods = new ArrayList<>();

    private UMLManager manager;

    /**
     * Contains all information about a Java class loaded from a UMLet XML file
     * Use the {@link #toString()} method to get the class as compilable, valid Java Code
     * @param classDiagram
     * @param packageString
     * @param manager
     */
    public UMLClass(String classDiagram, String packageString, UMLManager manager) {

        this.manager = manager;
        this.packageString = packageString;

        String[] lines = classDiagram.split("\n");

        String[] linesBeheaded = new String[lines.length - 1];
        this.fullName = lines[0];
        this.name = lines[0].split(" ")[0];

        System.arraycopy(lines, 1, linesBeheaded, 0, linesBeheaded.length);

        for (String line : linesBeheaded) {
            if (line != null) {
                if (line.matches(Regex.getMethodPattern(manager.isIgnoreEcapsulation()))) {  //MATCHES METHOD
                    methods.add(new UMLMethod(line, name, manager));
                } else if (line.matches(Regex.getFieldPattern(manager.isIgnoreEcapsulation()))) { //MATCHES FIELD
                    fields.add(new UMLField(line, manager));
                }
            }
        }
    }

    /**
     * Returns the class as compilable Java code
     * @return The class as compilable Java code
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        if(manager.isShowWatermark()) {
            s.append("//TODO AUTO-GENERATED METHODS\n\n");
        }

        if (!packageString.equals("")) {
            s.append("package ").append(packageString).append(";\n\n");
        }
        s.append("public class ").append(fullName).append(" {\n\n");

        if (fields.size() > 0) {
            for (UMLField field : fields) {
                s.append(field.toString());
            }
        }

        if (methods.size() > 0) {
            for (UMLMethod method : methods) {
                s.append(method.toString());
            }
        }

        if(manager.isGetSetAuto()){
            for(UMLField field : fields){
                s.append(field.getter());
                s.append(field.setter());
            }
        }

        s.append("}");

        return s.toString();
    }

    public String getName() {
        return name;
    }
}
