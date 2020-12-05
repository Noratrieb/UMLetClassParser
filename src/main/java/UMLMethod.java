import java.util.ArrayList;

/**
 * Stores all information about a method in a class and converts it into Java code using the {@link #toString()} method
 */
public class UMLMethod {

    private final String returnType;
    private final String name;
    private final String encapsulation;

    private final ArrayList<String> argsNames = new ArrayList<>();
    private final ArrayList<String> argsTypes = new ArrayList<>();

    private final boolean isConstructor;
    private final boolean isAbstract;

    private UMLManager manager;

    private String methodBody = "";

    /**
     * Create a new method/constructor from the UML representation of that method
     *
     * @param line      The line in the UML diagram
     * @param className The name of the class
     */
    public UMLMethod(String line, String className, UMLManager manager) {

        this.manager = manager;

        //check whether it's abstract
        if (line.matches("/.+/")) {
            isAbstract = true;
            line = line.replaceAll("/(.+)/", "$1");
        } else {
            isAbstract = false;
        }

        //First, format it nicely
        String formatted = line.replaceAll(Regex.METHOD_FIND_REGEX.pattern(), "$1;$4;$2;$3");
        String[] parts = formatted.split(";");
        if (!manager.isIgnoreEcapsulation()) {
            this.encapsulation = switch (parts[0]) {
                case "+" -> "public ";
                case "-" -> "private ";
                case "#" -> "protected ";
                case "~" -> "";
                default -> "[undefined] ";
            };
        } else {
            this.encapsulation = manager.getDefaultEncapsulation();
        }

        this.name = parts[2];

        isConstructor = className.equals(name);

        //parts[1] = returnType
        if (parts[1].equals("") && !isConstructor) {
            this.returnType = "void ";
        } else if (isConstructor) {
            this.returnType = "";
        } else {
            this.returnType = parts[1] + " ";
        }

        if (parts.length == 4) {
            String args = parts[3];
            String[] argsSplit = args.split(",");

            for (String s : argsSplit) {
                if (s.matches(Regex.ARG_SPLIT_REGEX.pattern())) {
                    String argFormatted = s.replaceAll(Regex.ARG_SPLIT_REGEX.pattern(), "$1;$2");
                    String[] formattedSplit = argFormatted.split(";");
                    argsNames.add(formattedSplit[0]);
                    argsTypes.add(formattedSplit[1]);
                }
            }
        }
    }

    public UMLMethod(String returnType, String name, String encapsulation) {
        this.returnType = returnType;
        this.name = name;
        this.encapsulation = encapsulation;
        isConstructor = false;
        isAbstract = false;
    }

    /**
     * Returns the method as the Java code representation
     *
     * @return The method as the Java code representation
     */
    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        returnString.append("\n   ").append(encapsulation).append(isAbstract ? "abstract " : "").append(returnType).append(name).append(" (");

        for (int i = 0; i < argsNames.size(); i++) {
            returnString.append(argsTypes.get(i)).append(" ").append(argsNames.get(i));
            if (i != argsNames.size() - 1) {
                returnString.append(", ");
            }
        }

        returnString.append(")");

        if (isAbstract) {
            returnString.append(";\n\n");
        } else {

            returnString.append(" {\n");

            if (isConstructor && manager.isAutoFillConstructor()) {
                for (String argsName : argsNames) {
                    addBodyLine("this." + argsName + " = " + argsName + ";");
                }
            }

            returnString.append(methodBody);
            returnString.append("\n   }\n");
        }

        return returnString.toString();
    }

    /**
     * Add an argument to the method
     *
     * @param name     The name of the variable
     * @param dataType The type
     */
    public void addArg(String name, String dataType) {
        argsNames.add(name);
        argsTypes.add(dataType);
    }

    /**
     * Add a line to the method body
     *
     * @param line The line (not containing any linebreaks)
     */
    public void addBodyLine(String line) {
        if (methodBody.equals("")) {
            methodBody += "      " + line;
        } else {
            methodBody += "\n      " + line;
        }
    }
}
