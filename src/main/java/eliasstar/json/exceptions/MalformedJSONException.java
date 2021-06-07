package eliasstar.json.exceptions;

/**
 * Indicates an error in the input JSON.
 *
 * @author Elias*
 * @since 1.0.0
 */
public class MalformedJsonException extends JsonException {

    private static final long serialVersionUID = 1L;

    public MalformedJsonException() {}

    public MalformedJsonException(String message) {
        super(message);
    }

    public MalformedJsonException(Throwable cause) {
        super(cause);
    }

    public MalformedJsonException(String message, Throwable cause) {
        super(message, cause);
    }
}