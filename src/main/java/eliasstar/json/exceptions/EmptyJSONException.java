package eliasstar.json.exceptions;

/**
 * Indicates an empty input JSON.
 *
 * @author Elias*
 * @since 1.0.0
 */
public class EmptyJsonException extends JsonException {

    private static final long serialVersionUID = 1L;

    public EmptyJsonException() {}

    public EmptyJsonException(Throwable cause) {
        super(cause);
    }
}