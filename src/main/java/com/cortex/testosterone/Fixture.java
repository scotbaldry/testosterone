package com.cortex.testosterone;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Fixture represents all the data and values required to test a specific method in a given Class.
 */
public class Fixture {
    private Class _class;
    private Method _target;

    private boolean _ctorSpecified = false;
    Class[] _types;
    Object[] _values;

    private String _returnInvariant;
    private Map<Integer, Factory> _factories = new HashMap<>();

    public Fixture(Method target) {
        _class = target.getDeclaringClass();
        _target = target;
    }

    public void setConstructor(Class[] types, Object[] values) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        _ctorSpecified = true;
        _types = types;
        _values = values;
        //Constructor constructor = getTargetClass().getDeclaredConstructor(types);
        //_instance = constructor.newInstance(values);
    }

    public boolean requiresArgsConstructor() {
        return _ctorSpecified;
    }

    public Method getTarget() {
        return _target;
    }

    public Class getTargetClass() {
        return _class;
    }

    public String getReturnInvariant() {
        return _returnInvariant;
    }

    public void setReturnInvariant(String returnInvariant) {
        _returnInvariant = returnInvariant;
    }

    public List<Factory> getValueFactories() {
        return new ArrayList<>(_factories.values());
    }

    public void setValueFactories(List<Factory> factories) {
        int i = 1;
        for(Factory f : factories) {
            _factories.put(i, f);
        }
    }

    //TODO: provide a var args version as well where position is implied
//    public void setValueFactories(Factory f...) {
//
//    }

    public void addValueFactory(int position, Factory f) {
        _factories.put(position, f);
    }

    public void execute() throws InvocationTargetException, IllegalAccessException, InstantiationException {
        List<Factory> factories = getValueFactories();
        Object[] args = new Object[factories.size()];

        for (int x = 0; x < 10; x++) {
            int i = 0;
            for (Factory f : factories) {
                args[i] = f.getValue();
                i++;
            }

            StringBuffer sb = new StringBuffer();
            sb.append("Invoking " + getTarget().getName() + " with args (");

            int ptr = 0;
            for (Object o : args) {
                sb.append(o.toString());
                if (ptr < args.length - 1) {
                    sb.append(", ");
                }
                else {
                    sb.append(")");
                }
            }
            System.out.println(sb.toString());
            Object o = getTarget().getDeclaringClass().newInstance();
            Object result = getTarget().invoke(o, args);
            System.out.println("Got result: " + result);
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getTarget().getDeclaringClass().toString() + "::" + getTarget().toString());
        sb.append("\n");
        sb.append("Return Invariant: " + getReturnInvariant());
        sb.append("\n");

        List<Factory> factories = getValueFactories();
        int i = 1;
        for (Factory f : factories) {
            sb.append("Value Factory [" + i + "] : " + f.toString());
            sb.append("\n");
        }

        return sb.toString();
    }
}
