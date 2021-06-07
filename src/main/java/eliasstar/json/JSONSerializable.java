package eliasstar.json;

/**
 * Functional interface for classes that can be serialized to JSON.
 *
 * @author Elias*
 * @since 1.0.0
 */
public interface JsonSerializable {
    public String toJSON();
}