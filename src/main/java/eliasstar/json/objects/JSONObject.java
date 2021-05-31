package eliasstar.json.objects;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import eliasstar.json.JSONSerializable;

public class JSONObject extends Object implements JSONSerializable {

    private final ArrayList<JSONNameValuePair<Object>> members;

    public JSONObject() {
        this.members = new ArrayList<>();
    }

    public JSONObject(List<JSONNameValuePair<Object>> members) {
        this.members = new ArrayList<>(members);
    }

    public JSONObject(Map<String, Object> members) {
        this.members = new ArrayList<>();
        members.forEach((n, v) -> this.members.add(new JSONNameValuePair<>(n, v)));
    }

    public JSONObject setMember(String name, Object value) {
        int index = -1;
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getName().equals(name)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            members.set(index, new JSONNameValuePair<>(name, value));
        } else {
            members.add(new JSONNameValuePair<>(name, value));
        }

        return this;
    }

    public JSONObject setMember(String name, Object value, int index) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getName().equals(name) && index != i) {
                index = i;
                break;
            }
        }

        members.set(index, new JSONNameValuePair<>(name, value));

        return this;
    }

    public Object getMember(String name) {
        for (JSONNameValuePair<Object> member : members) {
            if (member.getName().equals(name))
                return member.getValue();
        }

        return null;
    }

    public JSONObject removeMember(String name) {
        members.forEach(m -> {
            if (m.getName().equals(name))
                members.remove(m);
        });

        return this;
    }

    public boolean existsMember(String name) {
        for (JSONNameValuePair<Object> member : members) {
            if (member.getName().equals(name))
                return true;
        }

        return false;
    }

    public void setAllMembers(List<JSONNameValuePair<Object>> members) {
        clear();
        this.members.addAll(members);
    }

    public List<JSONNameValuePair<Object>> getAllMembers() {
        return members;
    }

    public void setMembers(Map<String, Object> members) {
        clear();
        members.forEach((n, v) -> this.members.add(new JSONNameValuePair<>(n, v)));
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
    public String toJSON() {
        String json = "{";

        for (int i = 0; i < members.size(); i++) {
            json += members.get(i).toString();
            if (i < members.size() - 1) {
                json += ",";
            }
        }

        return json + "}";
    }
}
