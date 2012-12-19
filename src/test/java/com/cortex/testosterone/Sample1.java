package com.cortex.testosterone;

import com.cortex.testosterone.annotations.ClassInvariant;
import com.cortex.testosterone.annotations.ReturnInvariant;
import com.cortex.testosterone.annotations.TestConstructor;
import com.cortex.testosterone.annotations.TestosteroneTest;
import com.cortex.testosterone.annotations.ValueFactory;

public class Sample1 {
    private String _name;
    private boolean _used = false;

    public Sample1() {
    }

    public Sample1(@ValueFactory("com.cortex.testosterone.RandomStringFactory") String name) {
        _name = name;
        System.out.println("Invoked Sample(String) ctor");
    }

    @TestosteroneTest(id = 1234, description = "First Test")
    @TestConstructor(types = {"String.class"}, values = {"foo"})
    @ReturnInvariant("{result} == >0")
    @ClassInvariant("this.isUsed() == true")
    public int myMethod(@ValueFactory("com.cortex.testosterone.PositiveIntegerFactory") int x,
                        @ValueFactory("com.cortex.testosterone.PositiveIntegerFactory") int y,
                        @ValueFactory("com.cortex.testosterone.RandomStringFactory") String z) {
        System.out.println("Executed myMethod(int, int, String");
        _used = true;
        return 0;
    }

    public int myMethod() {
        _used = true;
        return 1;
    }

    public boolean isUsed() {
        return _used;
    }
}

