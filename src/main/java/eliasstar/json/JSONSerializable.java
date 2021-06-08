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

/**
 * Functional interface for classes that can be serialized to JSON.
 *
 * @author Elias*
 * @since 1.0.0
 */
public interface JsonSerializable {
    public String toJson();
}