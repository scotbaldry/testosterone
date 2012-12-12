package com.cortex.juta;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SampleTest {
    public static void main(String args[]) {
        Sample s = new Sample();
        Class types[] = {int.class, int.class, String.class};

        Method method = null;
        try {
            method = s.getClass().getMethod("myMethod", types);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Class[] parameterTypes = method.getParameterTypes();

        int i=0;
        for(Annotation[] annotations : parameterAnnotations){
            Class parameterType = parameterTypes[i++];

            for(Annotation annotation : annotations){
                if(annotation instanceof TestFactory){
                    TestFactory myAnnotation = (TestFactory) annotation;
                    System.out.println("param: " + parameterType.getName());
                    System.out.println("value: " + myAnnotation.value());
                }
            }
        }

        try {
            Object o = method.invoke(s, 1, 1, "foo");
            System.out.println(o);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
