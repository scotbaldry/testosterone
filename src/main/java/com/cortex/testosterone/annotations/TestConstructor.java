package com.cortex.testosterone.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestConstructor {
    String[] types();
    String[] values();
}
