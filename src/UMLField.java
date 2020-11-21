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
}
