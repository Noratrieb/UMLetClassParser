import java.util.ArrayList;

public class UMLClass {

    private final String name;
    private final String fullName;
    private final String packageString;

    private final ArrayList<UMLField> fields = new ArrayList<>();
    private final ArrayList<UMLMethod> methods = new ArrayList<>();

    private UMLManager manager;

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
                if (line.matches(Regex.METHOD_FIND_REGEX)) {  //MATCHES METHOD
                    methods.add(new UMLMethod(line, name));
                } else if (line.matches(Regex.FIELD_FIND_REGEX)) { //MATCHES FIELD
                    fields.add(new UMLField(line));
                }
            }
        }
    }

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

            s.append("\n");
        }

        if (methods.size() > 0) {
            for (UMLMethod method : methods) {
                s.append(method.toString());
            }

            s.append("}");
        }

        return s.toString();
    }

    public String getName() {
        return name;
    }
}
