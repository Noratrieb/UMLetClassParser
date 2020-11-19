public class UMLField {

    private String dataType;
    private String name;
    private String encapsulation;

    private boolean valid;

    public UMLField(String dataType, String name, String encapsulation) {
        this.dataType = dataType;
        this.name = name;
        this.encapsulation = encapsulation;
    }

    /**
     * New Field from UML line
     *
     * @param line Format: "- name: String"
     */
    public UMLField(String line) {

        String formatted = line.replaceAll(Regex.FIELD_FIND_REGEX, "$1;$3;$2");
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


    @Override
    public String toString() {
        return "   " + encapsulation + dataType + " " + name + ";\n";

    }
}
