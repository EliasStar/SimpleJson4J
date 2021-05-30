package eliasstar.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.stream.Collectors;

import eliasstar.json.exceptions.EmptyJSONException;
import eliasstar.json.exceptions.FSMException;
import eliasstar.json.exceptions.InvalidJSONTypeException;
import eliasstar.json.exceptions.JSONException;
import eliasstar.json.exceptions.JSONNumberFormatException;
import eliasstar.json.exceptions.MalformedJSONException;
import eliasstar.json.objects.JSONArray;
import eliasstar.json.objects.JSONObject;

public class JSON {

    public static void writeFile(String path, Object object) throws IOException, JSONException {
        if (!path.substring(path.length() - 4).equalsIgnoreCase("json")) {
            throw new JSONException("Not a path to a JSON file: " + path);
        }

        new File(path.substring(0, path.lastIndexOf("/") != -1 ? path.lastIndexOf("/") : path.lastIndexOf("\\")))
                .mkdirs();

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
        out.write(toJSON(object));
        out.close();
    }

    public static Object readFile(String path) throws IOException, JSONException {
        if (!path.substring(path.length() - 4).equalsIgnoreCase("json")) {
            throw new JSONException("Not a path to a JSON file: " + path);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        String json = in.lines().collect(Collectors.joining());
        in.close();

        return fromJSON(json);
    }

    public static String toJSON(Object object) throws JSONException {
        String json;

        if (object == null) {
            json = "null";

        } else if (object instanceof JSONSerializable) {
            json = ((JSONSerializable) object).toJSON();

        } else if (object instanceof Boolean || object instanceof Byte || object instanceof Short
                || object instanceof Integer || object instanceof Long || object instanceof Float
                || object instanceof Double) {
            json = object.toString();

        } else if (object instanceof String || object instanceof Character) {
            json = '"' + object.toString() + '"';

        } else {
            throw new JSONException(
                    "Encountered instance of class which is not handled: " + object.getClass().toString());
        }

        return json;
    }

    public static Object fromJSON(String json) throws FSMException, EmptyJSONException, MalformedJSONException,
            InvalidJSONTypeException, JSONNumberFormatException {
        Object object;
        boolean isJSONObject = false;

        if (json.isEmpty()) {
            throw new EmptyJSONException();
        }

        while (json.startsWith(" ")) {
            json = json.substring(1, json.length());
        }

        switch (json.toCharArray()[0]) {
            case '{':
                if (!json.endsWith("}") || json.endsWith("\\}")) {
                    throw new MalformedJSONException("Object must end with '}'.");
                }

                isJSONObject = true;

            case '[':
                if (!isJSONObject) {
                    if (!json.endsWith("]") || json.endsWith("\\]")) {
                        throw new MalformedJSONException("Array must end with ']'.");
                    }

                    object = new JSONArray();
                } else {
                    object = new JSONObject();
                }

                int state = 0;
                String token = "";
                String name = "";
                int openParentheses = 0;
                boolean ignoreParentheses = false;

                json = json.substring(1, json.length() - 1);

                if (json.isEmpty()) {
                    return object;
                }

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

                            ignoreParentheses = (chars[i] == '"' && chars[i - 1] != '\\') ? !ignoreParentheses
                                    : ignoreParentheses;

                            if (!ignoreParentheses && chars[i] == '{')
                                openParentheses++;
                            else if (!ignoreParentheses && chars[i] == '}')
                                openParentheses--;

                            if (chars[i] == '}' && openParentheses == 0 && !ignoreParentheses)
                                state = 5;

                            break;

                        case 2:
                            token += chars[i];

                            ignoreParentheses = (chars[i] == '"' && chars[i - 1] != '\\') ? !ignoreParentheses
                                    : ignoreParentheses;

                            if (!ignoreParentheses && chars[i] == '[')
                                openParentheses++;
                            else if (!ignoreParentheses && chars[i] == ']')
                                openParentheses--;

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
                                while (token.endsWith(" ") || token.endsWith("\n") || token.endsWith("\r")
                                        || token.endsWith("\b") || token.endsWith("\t") || token.endsWith("\f")) {
                                    token = token.substring(0, token.length() - 1);
                                }
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
                                        ((JSONArray) object).addValue(fromJSON(token));
                                        token = "";
                                        state = 0;
                                        break;

                                    default:
                                        throw new MalformedJSONException("Values must be separated with ','.");
                                }
                                break;
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
                                        Object potStr = fromJSON(token);
                                        if (potStr instanceof String) {
                                            name = (String) potStr;
                                        } else {
                                            throw new InvalidJSONTypeException("Name must be of type String.");
                                        }
                                        token = "";
                                        state = 0;
                                        break;

                                    case ',':
                                        if (name != null)
                                            ((JSONObject) object).setMember(name, fromJSON(token));
                                        else
                                            throw new InvalidJSONTypeException("Name cannot be null.");
                                        name = "";
                                        token = "";
                                        state = 0;
                                        break;

                                    default:
                                        throw new MalformedJSONException(
                                                "Name and value must be separated with ':' and pairs of those must be separated with ','.");
                                }
                                break;
                            }
                    }
                }

                if (state != 0 || !token.isEmpty()) {
                    throw new FSMException(state, token);
                }

                if (ignoreParentheses) {
                    throw new MalformedJSONException("Quotation marks not matching.");
                }

                if (openParentheses != 0) {
                    throw new MalformedJSONException("Parentheses not matching.");
                }

                return object;

            case '"':
                if (!json.endsWith("\"") || json.endsWith("\\\"")) {
                    throw new MalformedJSONException("String must end with '\"'.");
                }

                return json.substring(1, json.length() - 1);

            case 't':
                if (!json.equals("true")) {
                    throw new MalformedJSONException("Strings must be inside quotation marks.");
                }

                return true;

            case 'f':
                if (!json.equals("false")) {
                    throw new MalformedJSONException("Strings must be inside quotation marks.");
                }

                return false;

            case 'n':
                if (!json.equals("null")) {
                    throw new MalformedJSONException("Strings must be inside quotation marks.");
                }

                return null;

            default:
                if (json.matches("-?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?")) {
                    try {
                        return NumberFormat.getInstance().parse(json);
                    } catch (ParseException ex) {
                        throw new JSONNumberFormatException("Exception while parsing number: " + json, ex);
                    }
                }

                throw new InvalidJSONTypeException(
                        "A value can only be of type string, number, object, array, \"true\", \"false\" or \"null\".");
        }
    }
}