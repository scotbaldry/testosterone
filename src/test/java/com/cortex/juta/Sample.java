package com.cortex.juta;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Sample {
    public Sample() {
    }

    @TestosteroneTest(id = 1234, description = "First Test")
    @ReturnInvariant(">0")
    public int myMethod(@TestFactory("PositiveIntFactory") int x,
                        @TestFactory("PositiveIntFactory") int y,
                        @TestFactory("RandomStringFactory") String z) {
        return 0;
    }

    public int myMethod() {
        return 1;
    }
}

