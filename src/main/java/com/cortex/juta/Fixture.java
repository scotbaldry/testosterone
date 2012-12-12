package com.cortex.juta;

import java.lang.reflect.Method;
import java.util.List;

public class Fixture {
    private Method target;
    private String returnInvariant;
    private List<? extends Factory> valueFactories;

    public Fixture(Method target) {
        this.target = target;
    }

    public String getReturnInvariant() {
        return returnInvariant;
    }

    public void setReturnInvariant(String returnInvariant) {
        this.returnInvariant = returnInvariant;
    }

    public List<? extends Factory> getValueFactories() {
        return valueFactories;
    }

    public void setValueFactories(List<? extends Factory> valueFactories) {
        this.valueFactories = valueFactories;
    }
}
