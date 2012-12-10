package com.cortex.juta;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Sample {
    public Sample() {
    }

//    @TestFactory(parameter = "x", factory = "PositiveIntFactory")
//    @TestFactory(parameter = "y", factory = "PositiveIntFactory")
//    @TestFactory(parameter = "z", factory = "RandomStringFactory")
    @ReturnInvariant(">0")
    public int myMethod(@TestFactory("PositiveIntFactory") int x,
                        @TestFactory("PositiveIntFactory") int y,
                        @TestFactory("RandomStringFactory") String z) {
        return 0;
    }

    public static void main(String args[]) {
        Sample s = new Sample();
        Class types[] = {int.class, int.class, String.class};

        Method method = null; // obtain method object
        try {
            method = s.getClass().getMethod("myMethod", types);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Annotation annotation = method.getAnnotation(TestFactory.class);

        if(annotation instanceof TestFactory){
            TestFactory myAnnotation = (TestFactory) annotation;
            System.out.println("value: " + myAnnotation.value());
        }

    }
}

