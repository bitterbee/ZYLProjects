package cn.bitterbee.zylprojects.module.java.annotation;

import java.lang.annotation.*;

/**
 * Created by zyl06 on 1/16/16.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
    int value() default 0;
    String name() default "";
    Constraints constraints() default @Constraints;
} /// : ~