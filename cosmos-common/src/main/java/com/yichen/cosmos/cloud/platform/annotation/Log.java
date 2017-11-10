package com.yichen.cosmos.cloud.platform.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hzsj on 2017/6/20.
 */

@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Log {
    /**
     * 描述
     */
    String remark();

    /**
     * 类型
     */
    enum Type {
        PROJECT,
        PRODUCT,
        ORTHRE,
        VERSION,
        WORK_FLOW,
        WORK_FLOW_LINE,
        RULE,
        RULE_SET
    }

    String pName() default "";

    String pId() default "";

    Type type() default Type.ORTHRE;


}
