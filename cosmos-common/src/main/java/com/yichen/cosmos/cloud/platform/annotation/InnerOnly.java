package com.yichen.cosmos.cloud.platform.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 以该注解标注Controller 方法上;
 * 表明该方法只支持系统内部访问，不支持用户外部访问
 *
 * @author thomas.su
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface InnerOnly {

}
