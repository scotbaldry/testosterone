package com.cortex.juta;

public class Sample {
    public Sample() {
    }

    @TestFactory(parameter = "x", factory = "PositiveIntFactory")
    @ReturnInvariant(">0")
    public int myMethod(int x, int y, String z) {
        return 0;
    }

    public static void main(String args[]) {
    }
}

