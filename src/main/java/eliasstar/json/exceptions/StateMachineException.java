/*
 * Copyright (C) 2021 Elias*
 *
 * This file is part of SimpleJson4J.
 *
 * SimpleJson4J is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or any later version.
 *
 * SimpleJson4J is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with SimpleJson4J. If not, see <https://www.gnu.org/licenses/>.
 */

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