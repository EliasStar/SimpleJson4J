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
    public String toJson() {
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

    @Override
    public int hashCode() {
        return values.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof JsonArray) {
            var other = (JsonArray) obj;

            return this == other || values.equals(other.values);
        }

        return false;
    }

    @Override
    public String toString() {
        return "JsonArray@" + Integer.toHexString(hashCode()) + " " + toJson();
    }

}
