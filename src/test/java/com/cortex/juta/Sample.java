package com.cortex.juta;

public class Sample {
    public Sample() {
    }

    @TestosteroneTest(id = 1234, description = "First Test")
    @ReturnInvariant(">0")
    public int myMethod(@TestFactory("com.cortex.juta.PositiveIntegerFactory") int x,
                        @TestFactory("com.cortex.juta.PositiveIntegerFactory") int y,
                        @TestFactory("com.cortex.juta.RandomStringFactory") String z) {
        return 0;
    }

    public int myMethod() {
        return 1;
    }
}

