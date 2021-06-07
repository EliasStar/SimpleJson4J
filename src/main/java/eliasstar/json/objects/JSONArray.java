package eliasstar.json.objects;

import java.util.ArrayList;
import java.util.List;

import eliasstar.json.Json;
import eliasstar.json.JsonSerializable;
import eliasstar.json.exceptions.JsonException;

/**
 * Represents a JSON array.
 *
 * @author Elias*
 * @since 1.0.0
 */
public class JsonArray extends Object implements JsonSerializable {

    private final ArrayList<Object> values;

    public JsonArray() {
        this.values = new ArrayList<>(0);
    }

    public JsonArray(List<Object> values) {
        this.values = new ArrayList<>(values);
    }

    public JsonArray addValue(Object value) {
        values.add(value);
        return this;
    }

    public JsonArray addValue(Object value, int index) {
        values.add(index, value);
        return this;
    }

    public Object getValue(int index) {
        return values.get(index);
    }

    public JsonArray removeValue(Object value) {
        while (values.contains(value)) {
            values.remove(value);
        }
        return this;
    }

    public void setAllValues(List<Object> values) {
        clear();
        this.values.addAll(values);
    }

    public List<Object> getAllValues() {
        return values;
    }

    public void clear() {
        values.clear();
    }

    @Override
    public String toJSON() {
        String json = "[";

        try {
            for (int i = 0; i < values.size(); i++) {
                json += Json.toJson(values.get(i));

                if (i < values.size() - 1)
                    json += ",";
            }
        } catch (JsonException e) {
            e.printStackTrace();
        }

        return json + "]";
    }
}
