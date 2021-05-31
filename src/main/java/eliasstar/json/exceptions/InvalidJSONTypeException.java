package eliasstar.json.exceptions;

public class InvalidJSONTypeException extends MalformedJSONException {

    private static final long serialVersionUID = 1L;

    public InvalidJSONTypeException() {}

    public InvalidJSONTypeException(String message) {
        super(message);
    }

    public InvalidJSONTypeException(Throwable cause) {
        super(cause);
    }

    public InvalidJSONTypeException(String message, Throwable cause) {
        super(message, cause);
    }

}