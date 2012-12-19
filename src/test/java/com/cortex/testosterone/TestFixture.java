package com.cortex.testosterone;

import junit.framework.TestCase;

public class TestFixture extends TestCase {
    public void testManualSetupNoArgsCtor() {
        try {
            Fixture f = new Fixture(Sample1.class.getMethod("myMethod", int.class, int.class, String.class));
            f.addValueFactory(1, new PositiveIntegerFactory());
            f.addValueFactory(2, new PositiveIntegerFactory());
            f.addValueFactory(3, new RandomStringFactory());
            f.setReturnInvariant(">0");
            f.execute();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    public void testManualSetupInvalidMethodArgs() {
        try {
            Fixture f = new Fixture(Sample1.class.getMethod("myMethod", int.class, String.class));
            f.addValueFactory(1, new PositiveIntegerFactory());
            f.addValueFactory(2, new RandomStringFactory());
            f.setReturnInvariant(">0");
            f.execute();
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchMethodException);
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
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    public void testManualSetupInvalidCtor() {
        try {
            Fixture f = new Fixture(Sample1.class.getMethod("myMethod", int.class, int.class, String.class));
            Class[] classes = {String.class, int.class};
            Object[] values = {"foo", 1};
            f.setConstructor(classes, values);
            f.addValueFactory(1, new PositiveIntegerFactory());
            f.addValueFactory(2, new PositiveIntegerFactory());
            f.addValueFactory(3, new RandomStringFactory());
            f.setReturnInvariant(">0");
            f.execute();
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchMethodException);
        }
    }
}
