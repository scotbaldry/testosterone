package com.cortex.juta;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AutoTester {
    private Class _target;

    public AutoTester(Class target) {
        _target = target;
    }

    public void test() {
        Method[] methods = findMethods();
        printMethods(methods);
    }

    /* Method to find all methods that have been annotated as TestosteroneTests and that should be automatically
     * tested.
     */
    private Method[] findMethods() {
        List<Method> returnMethods = new ArrayList<>();
        Method methods[] = _target.getMethods();
        for (Method m : methods) {
            if (m.isAccessible() && m.isAnnotationPresent(TestosteroneTest.class)) {
                returnMethods.add(m);
            }
        }

        return (Method[]) returnMethods.toArray();
    }

    private void buildFixtures() {
        Method[] methods = findMethods();

        for (Method m : methods) {

        }
    }


    // Given a method, read it's annotations and begin testing it.
    private void testMethod(Method m) {
        Annotation[][] parameterAnnotations = m.getParameterAnnotations();
        Annotation[] methodAnnotations = m.getAnnotations();
        Class[] parameterTypes = m.getParameterTypes();

        int i = 0;
        for (Annotation[] annotations : parameterAnnotations) {
            Class parameterType = parameterTypes[i++];

            for (Annotation annotation : annotations) {
                if (annotation instanceof TestFactory) {
                    TestFactory myAnnotation = (TestFactory) annotation;
                    System.out.println("param: " + parameterType.getName());
                    System.out.println("value: " + myAnnotation.value());
                }
            }
        }
    }

    private Factory createFactory(String factoryName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = Class.forName(factoryName);
        Factory f = (Factory)c.newInstance();
        return f;
    }

    private void printMethods(Method[] methods) {
        for (Method m : methods) {
            System.out.println(m.getName());
        }
    }
}
