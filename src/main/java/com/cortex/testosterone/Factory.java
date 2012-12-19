package com.cortex.testosterone;

public interface Factory<T> extends Iterable<T> {

    /**
     * Get next value from the Factory.
     *
     * @return next value of specified type T
     */
    T getValue();

    /**
     * Return the class type that this Factory handles. Useful in helping Testosterone determine if it
     * knows how to construct objects of certain types.
     *
     * @return the Class of object that this factory handles
     */
    Class<T> getClassType();

    /**
     * Predicate to determine if this Factory generates / produces a pre-determined set of values.
     * Used in conjunction with size() to determine how many test interations are necessary to exhaust the
     * Factory.
     *
     * @return true if a bounded set, false otherwise
     */
    boolean isBoundedSet();

    /**
     * Used in conjunction with isBoundedSet() to determine the size of the set of values that this Factory will
     * return. Returns 0 if the number of values this Factory can return are unbounded.
     *
     * @return the size of the set of values this factory will return, 0 if this factory is not bounded
     */
    int size();
}
