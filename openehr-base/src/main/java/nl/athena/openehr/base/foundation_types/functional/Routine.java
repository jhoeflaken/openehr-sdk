package nl.athena.openehr.base.foundation_types.functional;

@FunctionalInterface
public interface Routine<T extends Tuple> {

    void accept(final T args);

}
