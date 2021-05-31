package eliasstar.json.exceptions;

public class JSONNumberFormatException extends MalformedJSONException {

    private static final long serialVersionUID = 1L;

    public JSONNumberFormatException() {}

    public JSONNumberFormatException(String message) {
        super(message);
    }

    public JSONNumberFormatException(Throwable cause) {
        super(cause);
    }

    public JSONNumberFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}