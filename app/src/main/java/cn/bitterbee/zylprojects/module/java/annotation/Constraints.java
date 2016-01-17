package cn.bitterbee.zylprojects.module.java.annotation;

import java.lang.annotation.*;

/**
 * Created by zyl06 on 1/16/16.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraints {
    boolean primaryKey() default false;
    boolean allowNull() default true;
    boolean unique() default false;
} ///:~
