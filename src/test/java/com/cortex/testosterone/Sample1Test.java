package com.cortex.testosterone;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Sample1Test {
    public static void main(String args[]) {
        Sample1 s = new Sample1();
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
                if(annotation instanceof ValueFactory){
                    ValueFactory myAnnotation = (ValueFactory) annotation;
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
