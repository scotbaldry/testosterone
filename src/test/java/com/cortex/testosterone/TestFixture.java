package com.cortex.testosterone;

import junit.framework.TestCase;

import java.lang.reflect.InvocationTargetException;

public class TestFixture extends TestCase {
    public void testManualSetupNoArgsCtor() {
        try {
            Fixture f = new Fixture(Sample1.class.getMethod("myMethod", int.class, int.class, String.class));
            f.addValueFactory(1, new PositiveIntegerFactory());
            f.addValueFactory(2, new PositiveIntegerFactory());
            f.addValueFactory(3, new RandomStringFactory());
            f.setReturnInvariant(">0");
            f.execute();
        }
        catch (Exception e) {
            fail();
        }
    }

    public void testManualSetupArgsCtor() {
        try {
            Fixture f = new Fixture(Sample1.class.getMethod("myMethod", int.class, int.class, String.class));
            Class[] classes = {String.class};
            Object[] values = {"foo"};
            f.setConstructor(classes, values);
            f.addValueFactory(1, new PositiveIntegerFactory());
            f.addValueFactory(2, new PositiveIntegerFactory());
            f.addValueFactory(3, new RandomStringFactory());
            f.setReturnInvariant(">0");
            f.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
