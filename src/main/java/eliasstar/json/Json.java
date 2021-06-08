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

package eliasstar.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.stream.Collectors;

import eliasstar.json.exceptions.EmptyJsonException;
import eliasstar.json.exceptions.InvalidJsonTypeException;
import eliasstar.json.exceptions.JsonException;
import eliasstar.json.exceptions.JsonNumberFormatException;
import eliasstar.json.exceptions.MalformedJsonException;
import eliasstar.json.exceptions.StateMachineException;
import eliasstar.json.objects.JsonArray;
import eliasstar.json.objects.JsonObject;

/**
 * Provides a simple toJson and fromJson method.
 *
 * @author Elias*
 * @since 1.0.0
 */
public class Json {

    public static void writeFile(String path, Object object) throws IOException, JsonException {
        if (!path.substring(path.length() - 4).equalsIgnoreCase("json"))
            throw new JsonException("Not a path to a JSON file: " + path);

        new File(path.substring(0, path.lastIndexOf("/") != -1 ? path.lastIndexOf("/") : path.lastIndexOf("\\"))).mkdirs();

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
        out.write(toJson(object));
        out.close();
    }

    public static Object readFile(String path) throws IOException, JsonException {
        if (!path.substring(path.length() - 4).equalsIgnoreCase("json"))
            throw new JsonException("Not a path to a JSON file: " + path);

        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        String json = in.lines().collect(Collectors.joining());
        in.close();

        return fromJson(json);
    }

    /**
     * Converts an object to a JSON string.
     *
     * @param object The object in question
     * @return A JSON encoded string
     */
    public static String toJson(Object object) throws JsonException {
        if (object == null) {
            return "null";

        } else if (object instanceof JsonSerializable) {
            return ((JsonSerializable) object).toJson();

        } else if (object instanceof Boolean) {
            return object.toString();

        } else if (object instanceof BigDecimal) {
            return ((BigDecimal) object).toString();

        } else if (object instanceof Number) {
            Number n = (Number) object;
            double d = n.doubleValue();

            if (Double.isNaN(d) || Double.isInfinite(d)) {
                return "null";
            }

            if (d >= Long.MIN_VALUE && d <= Long.MAX_VALUE && Math.floor(d) == d) {
                return Long.toString(n.longValue());
            }

            return Double.toString(d);

        } else if (object instanceof String || object instanceof Character) {
            return '"' + object.toString() + '"';

        } else {
            throw new JsonException("Encountered instance of class which is not handled: " + object.getClass().toString());
        }
    }

    /**
     * Parses a JSON string into a Java object tree
     *
     * @param json The JSON string to be parsed
     * @return An object representing the json tree
     */
    public static Object fromJson(String json) throws StateMachineException, EmptyJsonException, MalformedJsonException, InvalidJsonTypeException, JsonNumberFormatException {
        Object object;
        boolean isJSONObject = false;

        if (json.isEmpty())
            throw new EmptyJsonException();

        while (json.startsWith(" "))
            json = json.substring(1, json.length());

        switch (json.toCharArray()[0]) {
        case '{':
            if (!json.endsWith("}") || json.endsWith("\\}"))
                throw new MalformedJsonException("Object must end with '}'.");

            isJSONObject = true;

        case '[':
            if (!isJSONObject) {
                if (!json.endsWith("]") || json.endsWith("\\]"))
                    throw new MalformedJsonException("Array must end with ']'.");

                object = new JsonArray();
            } else {
                object = new JsonObject();
            }

            int state = 0;
            String token = "";
            String name = "";
            int openParentheses = 0;
            boolean ignoreParentheses = false;

            json = json.substring(1, json.length() - 1);

            if (json.isEmpty())
                return object;

            json += ',';

            char[] chars = json.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                switch (state) {
                case 0:
                    switch (chars[i]) {
                    case '\n':
                    case '\r':
                    case '\b':
                    case '\t':
                    case '\f':
                    case ' ':
                        break;

                    case '{':
                        token += chars[i];

                        if (!ignoreParentheses && chars[i] == '{')
                            openParentheses++;

                        state = 1;
                        break;

                    case '[':
                        token += chars[i];

                        if (!ignoreParentheses && chars[i] == '[')
                            openParentheses++;

                        state = 2;
                        break;

                    case '"':
                        token += chars[i];
                        state = 3;
                        break;

                    default:
                        token += chars[i];
                        state = 4;
                    }

                    break;

                case 1:
                    token += chars[i];

                    ignoreParentheses = (chars[i] == '"' && chars[i - 1] != '\\') ? !ignoreParentheses : ignoreParentheses;

                    if (!ignoreParentheses && chars[i] == '{') {
                        openParentheses++;
                    } else if (!ignoreParentheses && chars[i] == '}') {
                        openParentheses--;
                    }

                    if (chars[i] == '}' && openParentheses == 0 && !ignoreParentheses)
                        state = 5;

                    break;

                case 2:
                    token += chars[i];

                    ignoreParentheses = (chars[i] == '"' && chars[i - 1] != '\\') ? !ignoreParentheses : ignoreParentheses;

                    if (!ignoreParentheses && chars[i] == '[') {
                        openParentheses++;
                    } else if (!ignoreParentheses && chars[i] == ']') {
                        openParentheses--;
                    }

                    if (chars[i] == ']' && openParentheses == 0 && !ignoreParentheses)
                        state = 5;

                    break;

                case 3:
                    token += chars[i];

                    if (chars[i] == '"' && chars[i - 1] != '\\')
                        state = 5;

                    break;

                case 4:
                    if (chars[i] == ',') {
                        while (token.endsWith(" ") || token.endsWith("\n") || token.endsWith("\r") || token.endsWith("\b") || token.endsWith("\t") || token.endsWith("\f"))
                            token = token.substring(0, token.length() - 1);

                        state = 5;
                        i--;
                    } else {
                        token += chars[i];
                    }

                    break;

                case 5:
                    if (!isJSONObject) {
                        switch (chars[i]) {
                        case '\n':
                        case '\r':
                        case '\b':
                        case '\t':
                        case '\f':
                        case ' ':
                            break;

                        case ',':
                            ((JsonArray) object).addValue(fromJson(token));

                            token = "";
                            state = 0;

                            break;

                        default:
                            throw new MalformedJsonException("Values must be separated with ','.");
                        }
                    } else {
                        switch (chars[i]) {
                        case '\n':
                        case '\r':
                        case '\b':
                        case '\t':
                        case '\f':
                        case ' ':
                            break;

                        case ':':
                            Object potStr = fromJson(token);

                            if (potStr instanceof String) {
                                name = (String) potStr;
                            } else {
                                throw new InvalidJsonTypeException("Name must be of type String.");
                            }

                            token = "";
                            state = 0;

                            break;

                        case ',':
                            if (name != null) {
                                ((JsonObject) object).setMember(name, fromJson(token));
                            } else {
                                throw new InvalidJsonTypeException("Name cannot be null.");
                            }

                            name = "";
                            token = "";
                            state = 0;

                            break;

                        default:
                            throw new MalformedJsonException(
                                    "Name and value must be separated with ':' and pairs of those must be separated with ','.");
                        }
                    }

                    break;
                }
            }

            if (state != 0 || !token.isEmpty())
                throw new StateMachineException(state, token);

            if (ignoreParentheses)
                throw new MalformedJsonException("Quotation marks not matching.");

            if (openParentheses != 0)
                throw new MalformedJsonException("Parentheses not matching.");

            return object;

        case '"':
            if (!json.endsWith("\"") || json.endsWith("\\\""))
                throw new MalformedJsonException("String must end with '\"'.");

            return json.substring(1, json.length() - 1);

        case 't':
            if (!json.equals("true"))
                throw new MalformedJsonException("Strings must be inside quotation marks.");

            return true;

        case 'f':
            if (!json.equals("false"))
                throw new MalformedJsonException("Strings must be inside quotation marks.");

            return false;

        case 'n':
            if (!json.equals("null"))
                throw new MalformedJsonException("Strings must be inside quotation marks.");

            return null;

        default:
            if (json.matches("-?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?")) {
                try {
                    return (Number) new BigDecimal(json);
                } catch (NumberFormatException ex) {
                    throw new JsonNumberFormatException("Exception while parsing number: " + json, ex);
                }
            }

            throw new InvalidJsonTypeException("A value can only be of type string, number, object, array, \"true\", \"false\" or \"null\".");
        }
    }
}
