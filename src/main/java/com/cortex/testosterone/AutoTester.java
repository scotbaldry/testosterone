package com.cortex.testosterone;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class AutoTester {
    private Class _target;
    private List<Fixture> _fixtureList = new ArrayList<>();

    public AutoTester(Class target) {
        _target = target;
    }

    public void test() throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        _fixtureList = buildFixtures();

        for (Fixture f : _fixtureList) {
            System.out.println(f);
        }

        for (Fixture f : _fixtureList) {
            f.execute();
        }
    }

    /* Method to find all methods that have been annotated as TestosteroneTests and that should be automatically
     * tested.
     */
    private List<Method> findMethods() {
        List<Method> returnMethods = new ArrayList<>();
        Method methods[] = _target.getMethods();
        for (Method m : methods) {
            if (isAccessible(m) && m.isAnnotationPresent(TestosteroneTest.class)) {
                returnMethods.add(m);
            }
        }

        return returnMethods;
    }

    private boolean isAccessible(Method m) {
        if (Modifier.isPublic(m.getModifiers())) {
            return true;
        }
        else {
            return false;
        }
    }

    private List<Fixture> buildFixtures() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<Fixture> fixtureList = new ArrayList<>();
        List<Method> methods = findMethods();

        for (Method m : methods) {
            Fixture f = new Fixture(m);

            Annotation[][] parameterAnnotations = m.getParameterAnnotations();
            Annotation[] methodAnnotations = m.getAnnotations();
            Class[] parameterTypes = m.getParameterTypes();

            for (Annotation annotation : methodAnnotations) {
                if (annotation instanceof ReturnInvariant) {
                    f.setReturnInvariant(((ReturnInvariant) annotation).value());
                }
            }

            int i = 0;
            for (Annotation[] annotations : parameterAnnotations) {
                Class parameterType = parameterTypes[i++];

                for (Annotation annotation : annotations) {
                    if (annotation instanceof ValueFactory) {
                        ValueFactory myAnnotation = (ValueFactory) annotation;
                        Factory factory = createFactory(myAnnotation.value());
                        f.addValueFactory(i+1, factory);  // i+1 since addValueFactory call is indexed from 1 as opposed to 0
                    }
                }
            }

            fixtureList.add(f);
        }

        return fixtureList;
    }

    private Factory createFactory(String factoryName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = Class.forName(factoryName);
        Factory f = (Factory)c.newInstance();
        return f;
    }
}
