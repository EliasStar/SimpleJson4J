package eliasstar.json.exceptions;

public class FSMException extends JSONException {

    private static final long serialVersionUID = 1L;

    public FSMException() {
    }

    public FSMException(int state) {
        super("Remaining State: " + Integer.toString(state));
    }

    public FSMException(String token) {
        super("Remaining Token: " + token);
    }

    public FSMException(int state, String token) {
        super("Remaining State: " + Integer.toString(state) + "\nRemaining Token: " + token);
    }

    public FSMException(Throwable cause) {
        super(cause);
    }

    public FSMException(int state, Throwable cause) {
        super("Remaining State: " + Integer.toString(state), cause);
    }

    public FSMException(String token, Throwable cause) {
        super("Remaining Token: " + token, cause);
    }

    public FSMException(int state, String token, Throwable cause) {
        super("Remaining State: " + Integer.toString(state) + "\nRemaining Token: " + token, cause);
    }
}