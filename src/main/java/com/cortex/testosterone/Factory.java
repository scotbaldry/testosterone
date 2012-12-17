package com.cortex.testosterone;

public interface Factory<T> extends Iterable<T> {
    T getValue();
    boolean isBoundedSet();
    int size();
}
