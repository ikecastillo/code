package ro.agrade.jira.qanda;

/**
 * Exception thrown when the expert group is duplicate
 *
 * @author Radu Dumitriu (rdumitriu@gmail.com)
 * @since 1.0
 */
public class DuplicateExpertGroupException extends Exception {
    private String name;

    public DuplicateExpertGroupException(String msg, String name) {
        super(msg);
    }

    public String getDuplicateName() {
        return name;
    }
}
