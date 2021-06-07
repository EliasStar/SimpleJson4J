package eliasstar.json.exceptions;

/**
 * Indicates that a number in the input JSON was not formatted properly.
 *
 * @author Elias*
 * @since 1.0.0
 */
public class JsonNumberFormatException extends MalformedJsonException {

    private static final long serialVersionUID = 1L;

    public JsonNumberFormatException() {}

    public JsonNumberFormatException(String message) {
        super(message);
    }

    public JsonNumberFormatException(Throwable cause) {
        super(cause);
    }

    public JsonNumberFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}