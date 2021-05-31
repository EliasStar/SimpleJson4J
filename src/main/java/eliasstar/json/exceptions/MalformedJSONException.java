package eliasstar.json.exceptions;

public class MalformedJSONException extends JSONException {

    private static final long serialVersionUID = 1L;

    public MalformedJSONException() {}

    public MalformedJSONException(String message) {
        super(message);
    }

    public MalformedJSONException(Throwable cause) {
        super(cause);
    }

    public MalformedJSONException(String message, Throwable cause) {
        super(message, cause);
    }
}