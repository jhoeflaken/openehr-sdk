package nl.athena.openehr.base.foundation_types.functional;

@FunctionalInterface
public interface Procedure<T extends Tuple> extends Routine<T> {

    void accept(final T args);

}
