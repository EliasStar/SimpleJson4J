/*
 * Copyright (C) 2021 Elias*
 *
 * This file is part of SimpleJson4J.
 *
 * SimpleJson4J is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or any later version.
 *
 * SimpleJson4J is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * SimpleJson4J. If not, see <https://www.gnu.org/licenses/>.
 */

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
