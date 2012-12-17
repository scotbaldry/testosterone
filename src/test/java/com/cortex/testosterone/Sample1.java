package com.cortex.testosterone;

public class Sample1 {
    public Sample1() {
    }

    @TestosteroneTest(id = 1234, description = "First Test")
    @ReturnInvariant(">0")
    public int myMethod(@ValueFactory("com.cortex.testosterone.PositiveIntegerFactory") int x,
                        @ValueFactory("com.cortex.testosterone.PositiveIntegerFactory") int y,
                        @ValueFactory("com.cortex.testosterone.RandomStringFactory") String z) {
        return 0;
    }

    public int myMethod() {
        return 1;
    }
}

