package eliasstar.json.exceptions;

/**
 * Root exception with is extended by all json-specific exceptions.
 *
 * @author Elias*
 * @since 1.0.0
 */
public class JsonException extends Exception {

    private static final long serialVersionUID = 1L;

    public JsonException() {}

    public JsonException(String message) {
        super(message);
    }

    public JsonException(Throwable cause) {
        super(cause);
    }

    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }

}