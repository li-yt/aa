package com.fh.util.excel;

import java.lang.annotation.*;

/**
 * @author Lenovo
 */
@Documented
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelAnnotation {

    String value() default "";

    String title() default "";

    String header() default "";

}
