import java.util.ArrayList;

public class UMLMethod {

    private final String returnType;
    private final String name;
    private final String encapsulation;

    private final ArrayList<String> argsNames = new ArrayList<>();
    private final ArrayList<String> argsTypes = new ArrayList<>();

    public UMLMethod(String line, String className) {

        //First, format it nicely

        String formatted = line.replaceAll(Regex.METHOD_FIND_REGEX, "$1;$4;$2;$3");
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
            this.returnType = "void ";
        } else {
            this.returnType = parts[1] + " ";
        }

        if(parts.length == 4) {
            String args = parts[3];
            String[] argsSplit = args.split(",");

            for (String s : argsSplit) {
                if(s.matches(Regex.ARG_SPLIT_REGEX)) {
                    String argFormatted = s.replaceAll(Regex.ARG_SPLIT_REGEX, "$1;$2");
                    String[] formattedSplit = argFormatted.split(";");
                    argsNames.add(formattedSplit[0]);
                    argsTypes.add(formattedSplit[1]);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        returnString.append("\n   ").append(encapsulation).append(returnType).append(name).append(" (");

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
