package com.cortex.testosterone;


import java.util.Iterator;

public class PositiveIntegerFactory implements Factory<Integer> {
    private int m_values[] = {1,2,3,4,5,6,7,8,9,10};
    private static int m_index = 0;

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public Integer getValue() {
        if (m_index > m_values.length -1) {
            m_index = 0;
        }

        return (m_values[m_index++]);
    }

    @Override
    public boolean isBoundedSet() {
        return true;
    }

    @Override
    public int size() {
        return m_values.length;
    }
}
