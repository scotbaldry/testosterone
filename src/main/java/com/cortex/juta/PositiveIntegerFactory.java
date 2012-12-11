package com.cortex.juta;


public class PositiveIntegerFactory {
    private static int m_index = 0;

    public int getValue() {
        int values[] = {1,2,3,4,5,6,7,8,9,10};

        if (m_index > values.length-1) {
            m_index = 0;
        }

        return(values[m_index++]);
    }
}
