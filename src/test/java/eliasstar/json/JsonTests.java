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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eliasstar.json.exceptions.JsonException;
import eliasstar.json.objects.JsonArray;
import eliasstar.json.objects.JsonObject;

public class JsonTests {

    private static String str = "string";

    private static Boolean boolTrue = true;
    private static Boolean boolFalse = false;

    private static Long longMin = Long.MIN_VALUE;
    private static Long longZero = 0L;
    private static Long longMax = Long.MAX_VALUE;

    private static Double doubleMin = Double.MIN_VALUE;
    private static Double doubleZero = 0D;
    private static Double doubleMax = Double.MAX_VALUE;

    private static JsonObject obj;
    private static JsonArray arr;

    @BeforeAll
    public static void initStructured() {
        obj = new JsonObject();
        obj.setMember("str", str);

        obj.setMember("boolTrue", boolTrue);
        obj.setMember("boolFalse", boolFalse);

        obj.setMember("longMin", BigDecimal.valueOf(longMin));
        obj.setMember("longZero", BigDecimal.valueOf(longZero));
        obj.setMember("longMax", BigDecimal.valueOf(longMax));

        obj.setMember("doubleMin", BigDecimal.valueOf(doubleMin));
        obj.setMember("doubleZero", BigDecimal.valueOf(doubleZero));
        obj.setMember("doubleMax", BigDecimal.valueOf(doubleMax));

        arr = new JsonArray();
        arr.addValue(str);

        arr.addValue(boolTrue);
        arr.addValue(boolFalse);

        arr.addValue(BigDecimal.valueOf(longMin));
        arr.addValue(BigDecimal.valueOf(longZero));
        arr.addValue(BigDecimal.valueOf(longMax));

        arr.addValue(BigDecimal.valueOf(doubleMin));
        arr.addValue(BigDecimal.valueOf(doubleZero));
        arr.addValue(BigDecimal.valueOf(doubleMax));
    }

    @Test
    public void testToJson() throws JsonException {
        assertEquals("\"string\"", Json.toJson(str));

        assertEquals("true", Json.toJson(boolTrue));
        assertEquals("false", Json.toJson(boolFalse));

        assertEquals("-9223372036854775808", Json.toJson(longMin));
        assertEquals("0", Json.toJson(longZero));
        assertEquals("9223372036854775807", Json.toJson(longMax));

        assertEquals("4.9E-324", Json.toJson(doubleMin));
        assertEquals("0", Json.toJson(doubleZero));
        assertEquals("1.7976931348623157E308", Json.toJson(doubleMax));

        JsonObject obj = new JsonObject();
        assertEquals("{}", Json.toJson(obj));
        obj.setMember("str", "");
        assertEquals("{\"str\":\"\"}", Json.toJson(obj));
        obj.setMember("i", BigDecimal.valueOf(0));
        assertEquals("{\"str\":\"\",\"i\":0}", Json.toJson(obj));

        JsonArray arr = new JsonArray();
        assertEquals("[]", Json.toJson(arr));
        arr.addValue("");
        assertEquals("[\"\"]", Json.toJson(arr));
        arr.addValue(BigDecimal.valueOf(0));
        assertEquals("[\"\",0]", Json.toJson(arr));
    }

    @Test
    public void testFromJson() throws JsonException {
        assertEquals(str, Json.fromJson("\"string\""));

        assertEquals(boolTrue, (Boolean) Json.fromJson("true"));
        assertEquals(boolFalse, (Boolean) Json.fromJson("false"));

        assertEquals(longMin, ((Number) Json.fromJson("-9223372036854775808")).longValue());
        assertEquals(longZero, ((Number) Json.fromJson("0")).longValue());
        assertEquals(longMax, ((Number) Json.fromJson("9223372036854775807")).longValue());

        assertEquals(doubleMin, ((Number) Json.fromJson("4.9E-324")).doubleValue());
        assertEquals(doubleZero, ((Number) Json.fromJson("0.0")).doubleValue());
        assertEquals(doubleMax, ((Number) Json.fromJson("1.7976931348623157E308")).doubleValue());

        JsonObject obj = new JsonObject();
        assertEquals(obj, (JsonObject) Json.fromJson("{}"));
        obj.setMember("str", "");
        assertEquals(obj, (JsonObject) Json.fromJson("{\"str\":\"\"}"));
        obj.setMember("i", BigDecimal.valueOf(0));
        assertEquals(obj, (JsonObject) Json.fromJson("{\"str\":\"\",\"i\":0}"));

        JsonArray arr = new JsonArray();
        assertEquals(arr, (JsonArray) Json.fromJson("[]"));
        arr.addValue("");
        assertEquals(arr, (JsonArray) Json.fromJson("[\"\"]"));
        arr.addValue(BigDecimal.valueOf(0));
        assertEquals(arr, (JsonArray) Json.fromJson("[\"\",0]"));
    }

    @Test
    public void testObjectToObject() throws JsonException {
        JsonObject expected = new JsonObject();
        expected.setMember("str", str);

        expected.setMember("boolTrue", boolTrue);
        expected.setMember("boolFalse", boolFalse);

        expected.setMember("longMin", BigDecimal.valueOf(longMin));
        expected.setMember("longZero", BigDecimal.valueOf(longZero));
        expected.setMember("longMax", BigDecimal.valueOf(longMax));

        expected.setMember("doubleMin", BigDecimal.valueOf(doubleMin));
        expected.setMember("doubleZero", BigDecimal.valueOf(doubleZero));
        expected.setMember("doubleMax", BigDecimal.valueOf(doubleMax));

        expected.setMember("obj", obj);
        expected.setMember("arr", arr);

        String out = Json.toJson(expected);
        Object actual = Json.fromJson(out);

        assertEquals(expected, actual);
    }

    @Test
    public void testArrayToArray() throws JsonException {
        JsonArray expected = new JsonArray();
        expected.addValue(str);

        expected.addValue(boolTrue);
        expected.addValue(boolFalse);

        expected.addValue(BigDecimal.valueOf(longMin));
        expected.addValue(BigDecimal.valueOf(longZero));
        expected.addValue(BigDecimal.valueOf(longMax));

        expected.addValue(BigDecimal.valueOf(doubleMin));
        expected.addValue(BigDecimal.valueOf(doubleZero));
        expected.addValue(BigDecimal.valueOf(doubleMax));

        expected.addValue(obj);
        expected.addValue(arr);

        String out = Json.toJson(expected);
        Object actual = Json.fromJson(out);

        assertEquals(expected, actual);
    }
}
