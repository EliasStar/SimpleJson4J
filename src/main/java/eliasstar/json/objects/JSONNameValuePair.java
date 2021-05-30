package eliasstar.json.objects;

import eliasstar.json.JSON;
import eliasstar.json.exceptions.JSONException;

public class JSONNameValuePair<V> extends Object {

    private String name;
    private V value;

    public JSONNameValuePair(String name) {
        this.name = name;
        this.value = null;
    }

    public JSONNameValuePair(String name, V value) {
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
            return '"' + name + "\":" + JSON.toJSON(value);
        } catch (JSONException e) {
            e.printStackTrace();
            return "\"Error\":\"" + e.getMessage() + '"';
        }
    }

}