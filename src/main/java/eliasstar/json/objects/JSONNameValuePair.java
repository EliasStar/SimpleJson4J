package eliasstar.json.objects;

import eliasstar.json.Json;
import eliasstar.json.exceptions.JsonException;

/**
 * Represents a JSON field.
 *
 * @author Elias*
 * @since 1.0.0
 */
public class JsonNameValuePair<V> extends Object {

    private String name;
    private V value;

    public JsonNameValuePair(String name) {
        this.name = name;
        this.value = null;
    }

    public JsonNameValuePair(String name, V value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        try {
            return '"' + name + "\":" + Json.toJson(value);
        } catch (JsonException e) {
            e.printStackTrace();
            return "\"Error\":\"" + e.getMessage() + '"';
        }
    }

}