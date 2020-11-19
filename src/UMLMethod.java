import java.util.ArrayList;
import java.util.Arrays;

public class UMLMethod {

    private String returnType;
    private String name;
    private String encapsulation;

    private ArrayList<String> argsNames = new ArrayList<>();
    private ArrayList<String> argsTypes = new ArrayList<>();

    public UMLMethod(String encapsulation, String returnType, String name, ArrayList<String> argsNames, ArrayList<String> argsTypes) {
        this.returnType = returnType;
        this.name = name;
        this.argsNames = argsNames;
        this.argsTypes = argsTypes;
        this.encapsulation = encapsulation;
    }

    public UMLMethod(String line, String className) {

        //First, format it nicely

        /**
         * Formatted line:
         * EncapsulationIndicator;
         * retunType("" for void);
         * name;
         * args in the UML format
         */
        String formatted = line.replaceAll("([+\\-~#]) (.+)\\((.*: .*,?)?\\):? ?(.+)?", "$1;$4;$2;$3");
        String[] parts = formatted.split(";");
        this.encapsulation = switch (parts[0]) {
            case "+" -> "public ";
            case "-" -> "private ";
            case "#" -> "protected ";
            case "~" -> "";
            default -> "[undefined] ";
        };

        this.name = parts[2];


        if(parts[1].equals("") && !className.equals(name)){
            this.returnType = "void";
        } else {
            this.returnType = parts[1];
        }

        if(parts.length == 4) {
            String args = parts[3];
            String[] argsSplit = args.split(",");

            for (String s : argsSplit) {
                String argFormatted = s.replaceAll(" ?(.*): (.*)", "$1;$2");
                String[] formattedSplit = argFormatted.split(";");
                argsNames.add(formattedSplit[0]);
                argsTypes.add(formattedSplit[1]);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        returnString.append("\n   ").append(encapsulation).append(returnType).append(" ").append(name).append(" (");

        for (int i = 0; i < argsNames.size(); i++) {
            returnString.append(argsTypes.get(i)).append(" ").append(argsNames.get(i));
            if (i != argsNames.size() - 1) {
                returnString.append(", ");
            }
        }

        returnString.append(") {\n   \n   }\n");

        return returnString.toString();
    }
}
