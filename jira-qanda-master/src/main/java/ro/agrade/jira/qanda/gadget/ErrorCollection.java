package ro.agrade.jira.qanda.gadget;

import java.util.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is required by the gadget configuration
 *
 * @author Radu Dumitriu (rdumitriu@gmail.com)
 * @since 1.0
 */
@XmlRootElement
public class ErrorCollection {
    /**
     * Generic error messages
     */
    @XmlElement
    public Collection<String> errorMessages = new ArrayList<String>();

    /**
     * Errors specific to a certain field.
     */
    @XmlElement
    public Collection<ValidationError> errors = new ArrayList<ValidationError>();

}
