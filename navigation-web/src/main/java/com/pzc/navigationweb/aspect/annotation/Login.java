package com.pzc.navigationweb.aspect.annotation;

import java.lang.annotation.*;

/**
 * @author ryf
 * @date 5/17/21 2:10 PM
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {

    String value();
}
