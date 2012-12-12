package com.cortex.juta;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestosteroneTest {
    int id();
    String description();
}
