public class Regex {

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
    public static final String METHOD_FIND_REGEX = " *(?<capsule>[+\\-~#]) *(?<name>\\w+) *\\( *(?<args>(?: *\\w+ *: *\\w+ *,? *)*) *\\) *(?:: *(?<return>\\w+))?";

    /**
     * Matches any Field in the UML format, including a constructor
     *
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
    public static final String FIELD_FIND_REGEX = " *(?<capsule>[+\\-~#]) *(?<name>\\w+) *: *(?<type>\\w+)";

    /**
     * Matches a single arg in a method
     *
     * <p> Groups:
     * <ul>
     *     <li>1 The name</li>
     *     <li>2 The datatype</li>
     *  </ul>
     */
    public static final String ARG_SPLIT_REGEX = " *(\\w+) *: *(\\w+)";
}
