import java.util.regex.Pattern;

/**
 * Contains all Regex
 */
public abstract class Regex {


    /**
     * Matches any method in the UML format, including a constructor
     *
     * <p>Examples:</p>
     * <ul>
     *     <li>+ doStuff(foo: int, foo: int): int</li>
     *     <li>+ getFoo(): int</li>
     *     <li>- work()</li>
     * </ul>
     *
     * <p> Groups:
     * <ul>
     *     <li>1 The encapsulate modifier (+-~#)</li>
     *     <li>2 The name</li>
     *     <li>3 All Arguments</li>
     *     <li>4 The return type, "" if no return type is specified (void)</li>
     *  </ul>
     *
     */
    public static final Pattern METHOD_FIND_REGEX = Pattern.compile(" *(?<capsule>[+\\-~#]) *(?<name>\\w+) *\\( *(?<args>(?: *\\w+ *: *[\\w<>]+ *,? *)*) *\\) *(?:: *(?<return>[\\w<>]+))?");

    /**
     * Matches any Field in the UML format, including a constructor
     *y
     * <p>Examples:</p>
     * <ul>
     *     <li>- age: int</li>
     *     <li>- name: String</li>
     *     <li># date: LocalDate</li>
     * </ul>
     *
     * <p> Groups:
     * <ul>
     *     <li>1 The encapsulate modifier (+-~#)</li>
     *     <li>2 The name</li>
     *     <li>3 The datatype</li>
     *  </ul>
     *
     */
    public static final Pattern FIELD_FIND_REGEX = Pattern.compile(" *(?<capsule>[+\\-~#]) *(?<name>\\w+) *: *(?<type>[\\w<>]+)");

    /**
     * Matches a single arg in a method
     *
     * <p> Groups:
     * <ul>
     *     <li>1 The name</li>
     *     <li>2 The datatype</li>
     *  </ul>
     */
    public static final Pattern ARG_SPLIT_REGEX = Pattern.compile(" *(\\w+) *: *(\\w+)");

    public static final Pattern FIELD_FIND_REGEX_NO_ENCAPSULATION = Pattern.compile(" *(?<capsule>[+\\-~#])? *(?<name>\\w+) *: *(?<type>[\\w<>]+)");

    public static final Pattern METHOD_FIND_REGEX_NO_ENCAPSULATION = Pattern.compile(" *(?<capsule>[+\\-~#])? *(?<name>\\w+) *\\( *(?<args>(?: *\\w+ *: *[\\w<>]+ *,? *)*) *\\) *(?:: *(?<return>[\\w<>]+))?");


    public static String getMethodPattern(boolean encapsulation){
        return encapsulation ? METHOD_FIND_REGEX_NO_ENCAPSULATION.pattern() : METHOD_FIND_REGEX.pattern();
    }

    public static String getFieldPattern(boolean encapsulation){
        return encapsulation ? FIELD_FIND_REGEX_NO_ENCAPSULATION.pattern() : FIELD_FIND_REGEX.pattern();
    }

}
