package com.tipsandtricks.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dstoianov on 2014-10-22.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bug {
    String[] id() default "";

    String url() default "http://redmine.com/redmine/issues/";
}