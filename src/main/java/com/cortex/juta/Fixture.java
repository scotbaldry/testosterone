package com.cortex.juta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Fixture {
    private Method _target;
    private String _returnInvariant;
    private List<Factory> _valueFactories = new ArrayList<>();

    public Fixture(Method target) {
        _target = target;
    }

    public Method getTarget() {
        return _target;
    }

    public String getReturnInvariant() {
        return _returnInvariant;
    }

    public void setReturnInvariant(String returnInvariant) {
        _returnInvariant = returnInvariant;
    }

    public List<Factory> getValueFactories() {
        return _valueFactories;
    }

    public void addValueFactory(Factory f) {
        _valueFactories.add(f);
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
                if (ptr < args.length) {
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
