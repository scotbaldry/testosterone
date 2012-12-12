package com.cortex.juta;

import java.util.Iterator;

public class RandomStringFactory implements Factory<String> {
    private String m_values[] = {"foo", "bar", "baz", "burt"};
    private static int m_index = 0;

    @Override
    public String getValue() {
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

    @Override
    public Iterator<String> iterator() {
        return null;
    }
}
