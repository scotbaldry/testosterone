package com.cortex.juta;

public class TestAutoTester {
    public static void main(String[] args) {
        AutoTester autoTester = new AutoTester(Sample.class);
        try {
            autoTester.test();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
