import java.util.regex.Pattern;

/**
 * Contains all Regex
 */
public abstract class Regex {

    /**
     * Matches any method in the UML format, including a constructor,  encapsulation optional
     *
     * <p>Examples:</p>
     * <ul>
     *     <li>+ doStuff(foo: int, foo: int): int</li>
     *     <li>+ getFoo(): int</li>
     *     <li>work()</li>
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
    public static final Pattern METHOD_FIND_REGEX = Pattern.compile(" *(?<capsule>[+\\-~#])? *(?<name>\\w+) *\\( *(?<args>(?: *\\w+ *: *[\\w<>]+ *,? *)*) *\\) *(?:: *(?<return>[\\w<>]+))?");


    /**
     * Matches any method in the UML format, including a constructor, forces the encapsulation modifier
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
    public static final Pattern METHOD_FIND_REGEX_FORCE_ENCAPSULATION = Pattern.compile(" *(?<capsule>[+\\-~#]) *(?<name>\\w+) *\\( *(?<args>(?: *\\w+ *: *[\\w<>]+ *,? *)*) *\\) *(?:: *(?<return>[\\w<>]+))?");


    /**
     * Matches any Field in the UML format, including a constructor, encapsulation optional
     *y
     * <p>Examples:</p>
     * <ul>
     *     <li>- age: int</li>
     *     <li>- name: String</li>
     *     <li>age: int</li>
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
    public static final Pattern FIELD_FIND_REGEX = Pattern.compile(" *(?<capsule>[+\\-~#])? *(?<name>\\w+) *: *(?<type>[\\w<>]+)");


    /**
     * Matches any Field in the UML format, including a constructor, forces the encapsulation modifier
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
    public static final Pattern FIELD_FIND_REGEX_FORCE_ENCAPSULATION = Pattern.compile(" *(?<capsule>[+\\-~#]) *(?<name>\\w+) *: *(?<type>[\\w<>]+)");


    /**
     * Matches a single arg in a method
     *
     * <p> Groups:
     * <ul>
     *     <li>1 The name</li>
     *     <li>2 The datatype</li>
     *  </ul>
     */
    public static final Pattern ARG_SPLIT_REGEX = Pattern.compile(" *(\\w+) *: *([\\w<>]+)");

    /**
     * Get a method pattern
     * @param encapsulationOptional True: Do not force encapsulation, False: Force encapsulation
     * @return The pattern as a String
     */
    public static String getMethodPattern(boolean encapsulationOptional){
        return encapsulationOptional ? METHOD_FIND_REGEX.pattern() : METHOD_FIND_REGEX_FORCE_ENCAPSULATION.pattern();
    }

    /**
     * Get a field pattern
     * @param encapsulationOptional True: Do not force encapsulation, False: Force encapsulation
     * @return The pattern as a String
     */
    public static String getFieldPattern(boolean encapsulationOptional){
        return encapsulationOptional ? FIELD_FIND_REGEX.pattern() : FIELD_FIND_REGEX_FORCE_ENCAPSULATION.pattern();
    }

}
