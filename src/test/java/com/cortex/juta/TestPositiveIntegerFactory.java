package com.cortex.juta;

import junit.framework.TestCase;

public class TestPositiveIntegerFactory extends TestCase {
    public void testSimpleUse() {
        PositiveIntegerFactory positiveIntegerFactory = new PositiveIntegerFactory();
        int i = positiveIntegerFactory.getValue();
        assertTrue("Check that positive integer is returned", i > 0);
    }

    public void testFullSet() {
        PositiveIntegerFactory positiveIntegerFactory = new PositiveIntegerFactory();
        int positiveInteger;

        for (int i = 0; i < positiveIntegerFactory.size(); i++) {
            positiveInteger = positiveIntegerFactory.getValue();
            assertTrue("Check that positive integer is returned", positiveInteger > 0);
        }
    }
}
