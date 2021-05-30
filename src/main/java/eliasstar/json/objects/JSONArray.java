package eliasstar.json.objects;

import java.util.ArrayList;
import java.util.List;

import eliasstar.json.JSON;
import eliasstar.json.JSONSerializable;
import eliasstar.json.exceptions.JSONException;

public class JSONArray extends Object implements JSONSerializable {

    private final ArrayList<Object> values;

    public JSONArray() {
        this.values = new ArrayList<>(0);
    }

    public JSONArray(List<Object> values) {
        this.values = new ArrayList<>(values);
    }

    public JSONArray addValue(Object value) {
        values.add(value);
        return this;
    }

    public JSONArray addValue(Object value, int index) {
        values.add(index, value);
        return this;
    }

    public Object getValue(int index) {
        return values.get(index);
    }

    public JSONArray removeValue(Object value) {
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
                json += JSON.toJSON(values.get(i));
                if (i < values.size() - 1) {
                    json += ",";
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        json += "]";

        return json;
    }
}
