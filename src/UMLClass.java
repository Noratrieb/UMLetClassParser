import java.util.ArrayList;

public class UMLClass {

    private String name;
    private String packageString;

    private ArrayList<UMLField> fields = new ArrayList<>();
    private ArrayList<UMLMethod> methods = new ArrayList<>();

    public UMLClass(String classDiagram, String packageString) {

        this.packageString = packageString;

        String[] lines = classDiagram.split("\n");

        String[] linesBeheaded = new String[lines.length-1];
        this.name = lines[0];

        System.arraycopy(lines, 1, linesBeheaded, 0, linesBeheaded.length);

        for (String line : linesBeheaded) {
            if(line != null) {
                if (line.matches("([+\\-~#]) ?(.+)\\((.*: .*,?)?\\):? ?(.+)?")) {  //MATCHES METHOD
                    methods.add(new UMLMethod(line, name));
                } else if (line.matches("([+\\-~#]) ?((?:[a-z]|[A-Z]|[0-1])+): (.*)")) { //MATCHES FIELD
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
