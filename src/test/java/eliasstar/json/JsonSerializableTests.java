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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import eliasstar.json.exceptions.JsonException;
import eliasstar.json.objects.JsonArray;
import eliasstar.json.objects.JsonObject;

public class JsonSerializableTests {

    private static class TestArray implements JsonSerializable {

        private String str = "string";
        private int x = 123;
        private boolean isX = true;

        public String toJson() {
            JsonArray out = new JsonArray();

            out.addValue(str);
            out.addValue(BigDecimal.valueOf(x));
            out.addValue(isX);

            return out.toJson();
        }

    }

    private static class TestObject implements JsonSerializable {

        private Object[] arr = { "string", BigDecimal.valueOf(123), true };

        public String toJson() {
            JsonObject out = new JsonObject();

            for (int i = 0; i < arr.length; i++) {
                out.setMember(Integer.toString(i), arr[i]);
            }

            return out.toJson();
        }

    }

    private static TestArray arr = new TestArray();
    private static TestObject obj = new TestObject();

    @Test
    public void testCustomJsonArray() throws JsonException {
        assertEquals("[\"string\",123,true]", Json.toJson(arr));
    }

    @Test
    public void testCustomJsonObject() throws JsonException {
        assertEquals("{\"0\":\"string\",\"1\":123,\"2\":true}", Json.toJson(obj));
    }
}
