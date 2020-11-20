import java.util.ArrayList;

public class UMLClass {

    private final String name;
    private final String packageString;

    private final ArrayList<UMLField> fields = new ArrayList<>();
    private final ArrayList<UMLMethod> methods = new ArrayList<>();

    public UMLClass(String classDiagram, String packageString) {

        this.packageString = packageString;

        String[] lines = classDiagram.split("\n");

        String[] linesBeheaded = new String[lines.length-1];
        this.name = lines[0].split(" ")[0];

        System.arraycopy(lines, 1, linesBeheaded, 0, linesBeheaded.length);

        for (String line : linesBeheaded) {
            if(line != null) {
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
        if(!packageString.equals("")){
            s.append("package ").append(packageString).append(";\n\n");
        }
        s.append("public class ").append(name).append(" {\n\n");

        for (UMLField field : fields){
            s.append(field.toString());
        }

        s.append("\n");

        for (UMLMethod method : methods){
            s.append(method.toString());
        }

        s.append("\n}");

        return s.toString();
    }

    public String getName() {
        return name;
    }
}
