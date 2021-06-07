package eliasstar.json.exceptions;

/**
 * The statemachine used to parse the JSON did not finish as expected, which
 * indicates an error in the input JSON.
 *
 * @author Elias*
 * @since 1.0.0
 */
public class StateMachineException extends JsonException {

    private static final long serialVersionUID = 1L;

    public StateMachineException() {}

    public StateMachineException(int state) {
        super("Remaining State: " + Integer.toString(state));
    }

    public StateMachineException(String token) {
        super("Remaining Token: " + token);
    }

    public StateMachineException(int state, String token) {
        super("Remaining State: " + Integer.toString(state) + "\nRemaining Token: " + token);
    }

    public StateMachineException(Throwable cause) {
        super(cause);
    }

    public StateMachineException(int state, Throwable cause) {
        super("Remaining State: " + Integer.toString(state), cause);
    }

    public StateMachineException(String token, Throwable cause) {
        super("Remaining Token: " + token, cause);
    }

    public StateMachineException(int state, String token, Throwable cause) {
        super("Remaining State: " + Integer.toString(state) + "\nRemaining Token: " + token, cause);
    }
}