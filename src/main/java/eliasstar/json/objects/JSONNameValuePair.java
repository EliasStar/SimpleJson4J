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

import java.util.Objects;

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
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof JsonNameValuePair) {
            var other = (JsonNameValuePair<?>) obj;

            return this == other || name.equals(other.name) && value.equals(other.value);
        }

        return false;
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