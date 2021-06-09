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

package eliasstar.json.objects;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import eliasstar.json.JsonSerializable;

/**
 * Represents a JSON object.
 *
 * @author Elias*
 * @since 1.0.0
 */
public class JsonObject extends Object implements JsonSerializable {

    private final ArrayList<JsonNameValuePair<Object>> members;

    public JsonObject() {
        this.members = new ArrayList<>();
    }

    public JsonObject(List<JsonNameValuePair<Object>> members) {
        this.members = new ArrayList<>(members);
    }

    public JsonObject(Map<String, Object> members) {
        this.members = new ArrayList<>();
        members.forEach((n, v) -> this.members.add(new JsonNameValuePair<>(n, v)));
    }

    public JsonObject setMember(String name, Object value) {
        int index = -1;
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getName().equals(name)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            members.set(index, new JsonNameValuePair<>(name, value));
        } else {
            members.add(new JsonNameValuePair<>(name, value));
        }

        return this;
    }

    public JsonObject setMember(String name, Object value, int index) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getName().equals(name) && index != i) {
                index = i;
                break;
            }
        }

        members.set(index, new JsonNameValuePair<>(name, value));

        return this;
    }

    public Object getMember(String name) {
        for (JsonNameValuePair<Object> member : members) {
            if (member.getName().equals(name))
                return member.getValue();
        }

        return null;
    }

    public JsonObject removeMember(String name) {
        members.forEach(m -> {
            if (m.getName().equals(name))
                members.remove(m);
        });

        return this;
    }

    public boolean existsMember(String name) {
        for (JsonNameValuePair<Object> member : members) {
            if (member.getName().equals(name))
                return true;
        }

        return false;
    }

    public void setAllMembers(List<JsonNameValuePair<Object>> members) {
        clear();
        this.members.addAll(members);
    }

    public List<JsonNameValuePair<Object>> getAllMembers() {
        return members;
    }

    public void setMembers(Map<String, Object> members) {
        clear();
        members.forEach((n, v) -> this.members.add(new JsonNameValuePair<>(n, v)));
    }

    public Map<String, Object> getMembers() {
        LinkedHashMap<String, Object> members = new LinkedHashMap<>();
        this.members.forEach(m -> {
            members.put(m.getName(), m.getValue());
        });

        return members;
    }

    public void clear() {
        members.clear();
    }

    @Override
    public String toJson() {
        String json = "{";

        for (int i = 0; i < members.size(); i++) {
            json += members.get(i).toString();
            if (i < members.size() - 1) {
                json += ",";
            }
        }

        return json + "}";
    }

    @Override
    public int hashCode() {
        return members.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof JsonObject) {
            var other = (JsonObject) obj;

            return this == other || members.equals(other.members);
        }

        return false;
    }

    @Override
    public String toString() {
        return "JsonObject@" + Integer.toHexString(hashCode()) + " " + toJson();
    }

}
