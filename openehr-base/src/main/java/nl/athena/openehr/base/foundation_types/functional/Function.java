package nl.athena.openehr.base.foundation_types.functional;

public interface Function<T extends Tuple, R> extends Routine<T> {

    R apply(final T tuple);

}
