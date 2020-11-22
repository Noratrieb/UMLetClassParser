/**
 * Stores all information about a field in a class and converts it into Java code using the {@link #toString()} method
 */
public class UMLField {

    private final String dataType;
    private final String name;
    private final String encapsulation;

    private boolean valid;

    /**
     * New Field from UML line
     *
     * @param line Format: "- name: String"
     */
    public UMLField(String line) {

        String formatted = line.replaceAll(Regex.FIELD_FIND_REGEX.pattern(), "$1;$3;$2");
        String[] formattedSplit = formatted.split(";");

        this.encapsulation = switch (formattedSplit[0]) {
            case "+" -> "public ";
            case "-" -> "private ";
            case "#" -> "protected ";
            case "~" -> "";
            default -> "[undefined] ";
        };

        this.name = formattedSplit[2];
        this.dataType = formattedSplit[1];
    }

    /**
     * Returns the field as the Java code representation
     * @return The field as the Java code representation
     */
    @Override
    public String toString() {
        return "   " + encapsulation + dataType + " " + name + ";\n";

    }

    /**
     * Generate a Setter Method for this field
     *
     * @return The Setter Method as a String
     */
    public String setter() {
        String nameCapital = name.toUpperCase();
        String nameCC = "set" + nameCapital.charAt(0) + name.substring(1);
        UMLMethod setter = new UMLMethod("void ", nameCC, "public ");
        setter.addArg(name, dataType);
        setter.addBodyLine("this." + name + " = " + name + ";");

        return setter.toString();
    }

    /**
     * Generate a Getter Method for this field
     *
     * @return The Getter Method as a String
     */
    public String getter() {

        String nameCapital = name.toUpperCase();
        String nameCC = "get" + nameCapital.charAt(0) + name.substring(1);
        UMLMethod setter = new UMLMethod(dataType + " ", nameCC, "public ");
        setter.addBodyLine("return " + name + ";");

        return setter.toString();
    }
}
