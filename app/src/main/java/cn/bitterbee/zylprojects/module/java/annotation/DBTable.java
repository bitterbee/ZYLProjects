package cn.bitterbee.zylprojects.module.java.annotation;

import java.lang.annotation.*;

/**
 * Created by zyl06 on 1/16/16.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
    public String name() default "";
}  /// : ~
