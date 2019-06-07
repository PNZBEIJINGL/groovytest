
package com.groovytest.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * @author LIUY
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface DataProvider {

    /**
     * 脚本中进行调用时的方法名称，使用该注解的类应该实现call方法
     *
     * @return 方法名称
     */
    String name() default "";

}
