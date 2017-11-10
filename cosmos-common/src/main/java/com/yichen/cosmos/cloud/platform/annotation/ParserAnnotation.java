package com.yichen.cosmos.cloud.platform.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by thomas.su on 2017/10/11 14:45.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParserAnnotation {
    public TYPE TypeName() default TYPE.DEFAULTTYPE;

    public enum TYPE {HANDLER, DATATYPE, DEFAULTTYPE}

    ;
}
