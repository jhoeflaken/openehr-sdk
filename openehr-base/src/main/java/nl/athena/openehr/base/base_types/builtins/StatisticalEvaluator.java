package nl.athena.openehr.base.base_types.builtins;

import jakarta.validation.constraints.NotNull;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Statistical functions.
 */
public interface StatisticalEvaluator {

    /**
     * Returns the sum of the given numbers.
     *
     * @param numbers The numbers to sum.
     *                Must not be null.
     *
     * @return The sum of the given numbers.
     */
    Double sum(@NotNull final Collection<Number> numbers);

    /**
     * Returns the average of the given numbers. Same as {@link #mean(Collection)}.
     *
     * @param numbers The numbers to average.
     *                Must not be null.
     *
     * @return The average of the given numbers.
     */
    Double avg(@NotNull final Collection<Number> numbers);

    /**
     * Returns the mean of the given numbers. Same as {@link #avg(Collection)}.
     *
     * @param numbers The numbers to calculate the mean of.
     *                Must not be null.
     *
     * @return The mean of the given numbers.
     */
    Double mean(@NotNull final Collection<Number> numbers);

    /**
     * Returns the median of the given numbers.
     *
     * @param numbers The numbers to calculate the median of.
     *                Must not be null.
     *
     * @return The median of the given numbers.
     */
    Double median(@NotNull final Collection<Number> numbers);

    /**
     * Returns the mode (most frequent) of the given numbers.
     *
     * @param numbers The numbers to calculate the mode of.
     *                Must not be null.
     *
     * @return The mode of the given numbers.
     */
    Double mode(@NotNull final Collection<Number> numbers);

    /**
     * Returns the maximum of the given numbers.
     *
     * @param numbers The numbers to calculate the maximum of.
     *                Must not be null.
     *
     * @return The maximum of the given numbers.
     */
    Double max(@NotNull final Collection<Number> numbers);

    /**
     * Returns the minimum of the given numbers.
     *
     * @param numbers The numbers to calculate the minimum of.
     *                Must not be null.
     *
     * @return The minimum of the given numbers.
     */
    Double min(@NotNull final Collection<Number> numbers);

    /**
     * Returns the count of the given numbers.
     *
     * @param numbers The numbers to count.
     *                Must not be null.
     *
     * @return The count of the given numbers.
     */
    Integer count(@NotNull final Collection<Number> numbers);

    /**
     * Returns the standard deviation of the given numbers.
     *
     * @param numbers The numbers to calculate the standard deviation of.
     *                Must not be null.
     *
     * @return The standard deviation of the given numbers.
     */
    Double std_dev(@NotNull final Collection<Number> numbers);

}
