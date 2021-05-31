package eliasstar.json.exceptions;

public class EmptyJSONException extends JSONException {

    private static final long serialVersionUID = 1L;

    public EmptyJSONException() {}

    public EmptyJSONException(Throwable cause) {
        super(cause);
    }
}