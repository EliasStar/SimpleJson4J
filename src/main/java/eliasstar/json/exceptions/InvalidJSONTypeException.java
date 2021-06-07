package eliasstar.json.exceptions;

/**
 * Indicates a type error in the input JSON such as field names not being
 * strings.
 *
 * @author Elias*
 * @since 1.0.0
 */
public class InvalidJsonTypeException extends MalformedJsonException {

    private static final long serialVersionUID = 1L;

    public InvalidJsonTypeException() {}

    public InvalidJsonTypeException(String message) {
        super(message);
    }

    public InvalidJsonTypeException(Throwable cause) {
        super(cause);
    }

    public InvalidJsonTypeException(String message, Throwable cause) {
        super(message, cause);
    }

}